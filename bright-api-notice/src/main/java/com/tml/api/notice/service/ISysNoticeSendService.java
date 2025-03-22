package com.tml.api.notice.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.tml.api.notice.entity.SysNotice;
import com.tml.api.notice.entity.SysNoticeSend;
import com.tml.common.core.vo.PageVo;

import java.util.List;

/**
 * 用户通告阅读标记表 Service接口
 *
 * @author JacksonTu
 * @date 2020-10-12 15:03:04
 */
public interface ISysNoticeSendService extends IService<SysNoticeSend> {
    /**
     * 查询（分页）
     *
     * @param noticeSendDto
     * @return
     */
    PageVo<SysNoticeSend> pageSysNoticeSend(com.tml.api.notice.dto.NoticeSendDTO noticeSendDto);

    /**
     * 查询（所有）
     *
     * @param noticeSendDto
     * @return List<SysNoticeSend>
     */
    List<SysNoticeSend> listSysNoticeSend(com.tml.api.notice.dto.NoticeSendDTO noticeSendDto);

    /**
     * 新增
     *
     * @param sysNoticeSend sysNoticeSend
     */
    void saveSysNoticeSend(SysNoticeSend sysNoticeSend);

    /**
     * 修改
     *
     * @param sysNoticeSend sysNoticeSend
     */
    void updateSysNoticeSend(SysNoticeSend sysNoticeSend);

    /**
     * 删除
     *
     * @param ids
     */
    void deleteSysNoticeSend(String[] ids);

    /**
     * 查询所有通告ID
     *
     * @param userId
     * @return
     */
    List<Long> listByUserId(Long userId);


    SysNoticeSend findByNoticeIdAndUserId(Long noticeId, Long userId);

    /**
     * 我的消息查询（分页）
     *
     * @param noticeSendDto
     * @return
     */
    PageVo<SysNotice> pageMyNoticeSend(com.tml.api.notice.dto.NoticeSendDTO noticeSendDto);

    /**
     * 修改
     *
     * @param sysNoticeSend sysNoticeSend
     */
    void updateByUserIdAndNoticeId(SysNoticeSend sysNoticeSend);

    /**
     * 根据noticeId集合删除
     *
     * @param noticeIds
     */
    void deleteByNoticeId(String[] noticeIds);


}
