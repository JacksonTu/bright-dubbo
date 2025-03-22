package com.tml.common.core.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 *  分页参数
 * @author JacksonTu
 * @date 2019/11/6 9:44
 */
@Schema(description = "分页参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class PageVo<T> implements Serializable {

    /**
     * 总行数
     */
    @Schema(description = "总行数")
    private int totalCount;

    //
    /**
     * 列表数据
     */
    @Schema(description = "数据列表")
    private List<T> list = Collections.emptyList();

    public PageVo() {

    }

    /**
     * 分页
     */
    public PageVo(IPage page) {
        this.list = page.getRecords();
        this.totalCount = (int) page.getTotal();
    }
}
