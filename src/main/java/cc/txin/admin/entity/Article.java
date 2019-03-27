package cc.txin.admin.entity;

import cc.txin.common.entity.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 文章实体类
 * @author: 印修河
 * @date: 2018/10/12 20:27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Article extends BaseEntity {

    private Long id;
    /** 文章标题 */
    private String title;
    /** 文章来源 */
    private String source;
    /** 文章作者 */
    private String author;
    /** 文章类型 */
    private String types;
    /** 图片 */
    private String image;
    /** 简介 */
    private String info;
    /** 新闻内容 */
    private String content;
    /** 0=已删除，1=待发布，2=已发布 */
    private Integer status;
    /** 文章排序 */
    private Integer seq;

}
