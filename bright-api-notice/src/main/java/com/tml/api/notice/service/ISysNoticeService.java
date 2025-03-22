package com.tml.api.notice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tml.api.notice.entity.SysNotice;
import com.tml.common.core.vo.PageVo;
import com.tml.common.core.vo.TreeVo;

import java.util.Date;
import java.util.List;

/**
 * 系统通告表 Service接口
 *
 * @author JacksonTu
 * @date 2020-10-12 15:02:54
 */
public interface ISysNoticeService extends IService<SysNotice> {
    /**
     * 查询（分页）
     *
     * @param noticeDto
     * @return
     */
    PageVo<SysNotice> pageSysNotice(com.tml.api.notice.dto.NoticeDTO noticeDto);

    /**
     * 查询（所有）
     *
     * @param noticeDto
     * @return List<SysNotice>
     */
    List<SysNotice> listSysNotice(com.tml.api.notice.dto.NoticeDTO noticeDto);

    /**
     * 新增
     *
     * @param sysNotice sysNotice
     */
    void saveSysNotice(SysNotice sysNotice);

    /**
     * 修改
     *
     * @param sysNotice sysNotice
     */
    void updateSysNotice(SysNotice sysNotice);

    /**
     * 删除
     *
     * @param ids
     */
    void deleteSysNotice(String[] ids);

    List<SysNotice> listByCondition(String msgType, String sendStatus, Date endTime, List<Long> noticeIds);

    /**
     * 未读消息
     *
     * @param noticeDto
     * @return
     */
    PageVo<SysNotice> pageUnreadMsg(com.tml.api.notice.dto.NoticeDTO noticeDto);

    /**
     * 用户树
     *
     * @param enterpriseId
     * @return
     */
    List<TreeVo> treeUser(String enterpriseId);

}
