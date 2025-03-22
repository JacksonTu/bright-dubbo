package com.tml.server.notice.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tml.api.notice.entity.SysNotice;
import org.apache.ibatis.annotations.Param;

/**
 * 系统通告表 Mapper
 *
 * @author JacksonTu
 * @date 2020-10-12 15:02:54
 */
public interface SysNoticeMapper extends BaseMapper<SysNotice> {

    /**
     * 未读消息
     *
     * @param page
     * @param userId
     * @param msgCategory
     * @return
     */
    IPage<SysNotice> pageUnreadMsg(IPage<SysNotice> page, @Param("userId") Long userId, @Param("msgCategory") String msgCategory);

}
