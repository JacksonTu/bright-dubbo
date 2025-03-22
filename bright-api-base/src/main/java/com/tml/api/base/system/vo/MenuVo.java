package com.tml.api.base.system.vo;

import com.tml.api.base.system.entity.SysResource;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 菜单VO
 *
 * @author JacksonTu
 * @date 2019/11/6 14:38
 */
@Schema(description = "菜单VO")
@Data
public class MenuVo implements Serializable {

    @Schema(description = "菜单列表")
    List<SysResource> menuList;

    @Schema(description = "权限列表")
    Set<String> permissions;
}
