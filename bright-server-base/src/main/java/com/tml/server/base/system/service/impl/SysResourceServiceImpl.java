package com.tml.server.base.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tml.api.base.system.entity.SysResource;
import com.tml.api.base.system.service.ISysResourceService;
import com.tml.common.core.constant.CommonConstant;
import com.tml.server.base.system.mapper.SysResourceMapper;
import com.tml.server.base.system.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 资源表
 *
 * @author JacksonTu
 * @date 2018-12-11 11:35:15
 */
@Slf4j
@DubboService
@Transactional(rollbackFor = Exception.class)
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements ISysResourceService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysResource> selectResourceList(Map<String, Object> params) {
        return this.baseMapper.selectResourceList(params);
    }

    @Override
    public List<SysResource> selectUserResourceListByUserId(Long userId) {
        //系统管理员，拥有最高权限
        if (userId == CommonConstant.SUPER_ADMIN) {
            return selectMenuList(null);
        }
        //用户菜单列表
        List<Long> menuIdList = sysUserMapper.selectResourceIdListByUserId(userId);
        return selectMenuList(menuIdList);
    }


    @Override
    public List<SysResource> selectListByParentId(Long parentId, List<Long> menuIdList) {
        List<SysResource> menuList = selectListByParentId(parentId);
        if (menuIdList == null) {
            return menuList;
        }

        List<SysResource> userMenuList = new ArrayList<>();
        for (SysResource menu : menuList) {
            if (menuIdList.contains(menu.getId())) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<SysResource> selectListByParentId(Long parentId) {
        QueryWrapper<SysResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId)
                .eq("status", 0)
                .orderByAsc("seq");
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<SysResource> selectNotButtonList() {
        QueryWrapper<SysResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("resource_type", 2)
                .eq("status", 0)
                .orderByAsc("seq");
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 获取所有菜单列表
     */
    private List<SysResource> selectMenuList(List<Long> menuIdList) {
        //查询根菜单列表
        List<SysResource> menuList = selectListByParentId(0L, menuIdList);
        //递归获取子菜单
        selectMenuTreeList(menuList, menuIdList);
        return menuList;
    }

    /**
     * 递归
     */
    private List<SysResource> selectMenuTreeList(List<SysResource> menuList, List<Long> menuIdList) {
        List<SysResource> subMenuList = new ArrayList<>();
        for (SysResource entity : menuList) {
            //目录
            if (entity.getResourceType() == CommonConstant.CATALOG) {
                entity.setList(selectMenuTreeList(selectListByParentId(entity.getId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }
        return subMenuList;
    }
}
