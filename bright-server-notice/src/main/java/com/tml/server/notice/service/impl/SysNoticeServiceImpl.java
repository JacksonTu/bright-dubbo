package com.tml.server.notice.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tml.api.base.enterprise.entity.Enterprise;
import com.tml.api.base.enterprise.service.IEnterpriseService;
import com.tml.api.base.system.entity.SysUser;
import com.tml.api.base.system.service.ISysUserEnterpriseService;
import com.tml.api.base.system.service.ISysUserService;
import com.tml.api.notice.entity.SysNotice;
import com.tml.api.notice.service.ISysNoticeService;
import com.tml.common.core.vo.PageVo;
import com.tml.common.core.vo.TreeVo;
import com.tml.server.notice.mapper.SysNoticeMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 系统通告表 Service实现
 *
 * @author JacksonTu
 * @date 2020-10-12 15:02:54
 */
@DubboService
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNotice> implements ISysNoticeService {

    @DubboReference
    private ISysUserService userService;
    @DubboReference
    private ISysUserEnterpriseService userEnterpriseService;
    @DubboReference
    private IEnterpriseService enterpriseService;

    @Override
    public PageVo<SysNotice> pageSysNotice(com.tml.api.notice.dto.NoticeDTO noticeDto) {
        Page page = new Page();
        // 设置当前页码
        page.setCurrent(noticeDto.getPage());
        // 设置页大小
        page.setSize(noticeDto.getLimit());

        LambdaQueryWrapper<SysNotice> wrapper = new LambdaQueryWrapper<>();
        wrapper.likeRight(StringUtils.isNoneBlank(noticeDto.getTitle()), SysNotice::getTitle, noticeDto.getTitle())
                .eq(StringUtils.isNoneBlank(noticeDto.getCreateUser()) && !noticeDto.getCreateUser().equals("admin"), SysNotice::getCreateUser, noticeDto.getCreateUser())
                .orderByDesc(true, SysNotice::getCreateTime);
        return new PageVo<SysNotice>(this.baseMapper.selectPage(page, wrapper));
    }

    @Override
    public List<SysNotice> listSysNotice(com.tml.api.notice.dto.NoticeDTO noticeDto) {
        LambdaQueryWrapper<SysNotice> wrapper = new LambdaQueryWrapper<>();
        wrapper.likeRight(StringUtils.isNoneBlank(noticeDto.getTitle()), SysNotice::getTitle, noticeDto.getTitle())
                .eq(StringUtils.isNoneBlank(noticeDto.getCreateUser()) && !noticeDto.getCreateUser().equals("admin"), SysNotice::getCreateUser, noticeDto.getCreateUser())
                .orderByDesc(true, SysNotice::getCreateTime);
        // TODO 设置查询条件
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSysNotice(SysNotice sysNotice) {
        this.save(sysNotice);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSysNotice(SysNotice sysNotice) {
        this.saveOrUpdate(sysNotice);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSysNotice(String[] ids) {
        List<String> list = Arrays.asList(ids);
        this.removeByIds(list);
    }

    @Override
    public List<SysNotice> listByCondition(String msgType, String sendStatus, Date endTime, List<Long> noticeIds) {
        LambdaQueryWrapper<SysNotice> wrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        wrapper.eq(StringUtils.isNoneBlank(msgType), SysNotice::getMsgType, msgType)
                .eq(StringUtils.isNoneBlank(sendStatus), SysNotice::getSendStatus, sendStatus)
                .ge(endTime != null, SysNotice::getEndTime, DateUtil.format(endTime, DatePattern.NORM_DATETIME_PATTERN));
        if (noticeIds != null && noticeIds.size() > 0) {
            wrapper.notIn(true, SysNotice::getId, noticeIds);
        }
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public PageVo<SysNotice> pageUnreadMsg(com.tml.api.notice.dto.NoticeDTO noticeDto) {
        Page page = new Page();
        // 设置当前页码
        page.setCurrent(noticeDto.getPage());
        // 设置页大小
        page.setSize(noticeDto.getLimit());

        return new PageVo<>(this.baseMapper.pageUnreadMsg(page, noticeDto.getUserId(), noticeDto.getMsgCategory()));
    }

    @Override
    public List<TreeVo> treeUser(String enterpriseId) {
        List<TreeVo> list = Lists.newArrayList();
        Enterprise enterprise = enterpriseService.getById(enterpriseId);
        if (enterprise != null) {
            TreeVo selectTreeNode = new TreeVo();
            selectTreeNode.setId(enterpriseId);
            selectTreeNode.setLabel(enterprise.getEnterpriseName());
            LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(StringUtils.isNoneBlank(enterpriseId), SysUser::getEnterpriseId, enterpriseId);
            List<SysUser> users = userService.list(wrapper);
            if (users != null) {
                List<TreeVo> childrenList = Lists.newArrayList();
                users.forEach(user -> {
                    TreeVo children = new TreeVo();
                    children.setId(user.getId().toString());
                    children.setLabel(user.getName());
                    childrenList.add(children);
                });
                selectTreeNode.setChildren(childrenList);
                list.add(selectTreeNode);
            }
        }
        return list;
    }
}