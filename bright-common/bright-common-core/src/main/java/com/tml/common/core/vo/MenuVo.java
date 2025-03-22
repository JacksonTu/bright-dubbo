package com.tml.common.core.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tml.common.core.constant.CommonConstant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


/**
 *  菜单的节点
 * @author JacksonTu
 * @date 2018/12/13 18:37
 */
public class MenuVo implements Comparable, Serializable {

    /**
     * 节点id
     */
    private Long id;

    /**
     * 父节点
     */
    private Long parentId;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 按钮级别
     */
    @JsonInclude(Include.NON_NULL)
    private Integer levels;

    /**
     * 是否菜单（0-是，1-否）
     */
    @JsonInclude(Include.NON_NULL)
    private Integer isMenu;

    /**
     * 状态（0-正常，1-关闭）
     */
    @JsonInclude(Include.NON_NULL)
    private Integer status;

    /**
     * 按钮的排序
     */
    @JsonInclude(Include.NON_NULL)
    private Integer num;

    /**
     * 节点的url
     */
    private String url;

    /**
     * 节点图标
     */
    private String icon;

    /**
     * 子节点的集合
     */
    @JsonInclude(Include.NON_NULL)
    private List<MenuVo> children;

    /**
     * 查询子节点时候的临时集合
     */
    private List<MenuVo> linkedList = new ArrayList<MenuVo>();

    public MenuVo() {
        super();
    }

    public MenuVo(Long id, Long parentId) {
        super();
        this.id = id;
        this.parentId = parentId;
    }

    public static MenuVo createRoot() {
        return new MenuVo(0L, -1L);
    }

    /**
     * 清除掉按钮级别的资源
     *
     * @param nodes
     * @return
     */
    public static List<MenuVo> clearBtn(List<MenuVo> nodes) {
        ArrayList<MenuVo> noBtns = new ArrayList<MenuVo>();
        for (MenuVo node : nodes) {
            if (node.getIsMenu() == CommonConstant.YES) {
                noBtns.add(node);
            }
        }
        return noBtns;
    }

    /**
     * 清除所有二级菜单
     *
     * @param nodes
     * @return
     */
    public static List<MenuVo> clearLevelTwo(List<MenuVo> nodes) {
        ArrayList<MenuVo> results = new ArrayList<MenuVo>();
        for (MenuVo node : nodes) {
            Integer levels = node.getLevels();
            if (levels.equals(1)) {
                results.add(node);
            }
        }
        return results;
    }

    /**
     * 构建菜单列表
     *
     * @date 2017年2月19日 下午11:18:19
     */
    public static List<MenuVo> buildTitle(List<MenuVo> nodes) {

        List<MenuVo> clearBtn = clearBtn(nodes);

        new MenuVo().buildNodeTree(clearBtn);

        List<MenuVo> menuVos = clearLevelTwo(clearBtn);
        //对菜单排序
        Collections.sort(menuVos);
        //对菜单的子菜单进行排序
        for (MenuVo menuVo : menuVos) {
            if (menuVo.getChildren() != null && menuVo.getChildren().size() > 0) {
                Collections.sort(menuVo.getChildren());
            }
        }

        return menuVos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevels() {
        return levels;
    }

    public void setLevels(Integer levels) {
        this.levels = levels;
    }

    public Integer getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(Integer isMenu) {
        this.isMenu = isMenu;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<MenuVo> getChildren() {
        return children;
    }

    public void setChildren(List<MenuVo> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "MenuNode{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", levels=" + levels +
                ", isMenu=" + isMenu +
                ", status=" + status +
                ", num=" + num +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", children=" + children +
                ", linkedList=" + linkedList +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        MenuVo menuVo = (MenuVo) o;
        Integer num = menuVo.getNum();
        if (num == null) {
            num = 0;
        }
        return this.num.compareTo(num);
    }

    /**
     * 构建整个菜单树
     *
     * @param nodeList
     */
    public void buildNodeTree(List<MenuVo> nodeList) {
        for (MenuVo treeNode : nodeList) {
            List<MenuVo> linkedList = treeNode.findChildNodes(nodeList, treeNode.getId());
            if (linkedList.size() > 0) {
                treeNode.setChildren(linkedList);
            }
        }
    }

    /**
     * 查询子节点的集合
     *
     * @param nodeList
     * @param pid
     * @return
     */
    public List<MenuVo> findChildNodes(List<MenuVo> nodeList, Long pid) {
        if (nodeList == null && pid == null)
            return null;
        for (Iterator<MenuVo> iterator = nodeList.iterator(); iterator.hasNext(); ) {
            MenuVo node = (MenuVo) iterator.next();
            // 根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (node.getParentId() != 0 && pid.equals(node.getParentId())) {
                recursionFn(nodeList, node, pid);
            }
        }
        return linkedList;
    }

    /**
     * 遍历一个节点的子节点
     *
     * @param nodeList
     * @param node
     * @param pId
     */
    public void recursionFn(List<MenuVo> nodeList, MenuVo node, Long pId) {
        List<MenuVo> childList = getChildList(nodeList, node);// 得到子节点列表
        if (childList.size() > 0) {// 判断是否有子节点
            if (node.getParentId().equals(pId)) {
                linkedList.add(node);
            }
            Iterator<MenuVo> it = childList.iterator();
            while (it.hasNext()) {
                MenuVo n = (MenuVo) it.next();
                recursionFn(nodeList, n, pId);
            }
        } else {
            if (node.getParentId().equals(pId)) {
                linkedList.add(node);
            }
        }
    }

    /**
     * 得到子节点列表
     *
     * @param list
     * @param node
     * @return
     */
    private List<MenuVo> getChildList(List<MenuVo> list, MenuVo node) {
        List<MenuVo> nodeList = new ArrayList<MenuVo>();
        Iterator<MenuVo> it = list.iterator();
        while (it.hasNext()) {
            MenuVo n = (MenuVo) it.next();
            if (n.getParentId().equals(node.getId())) {
                nodeList.add(n);
            }
        }
        return nodeList;
    }
}
