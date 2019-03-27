package cc.txin.admin.entity;


import cc.txin.common.entity.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 图片轮播器
 *  * @author: lcl
 *  * @date: 2018/10/12 20:27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Banner extends BaseEntity {
    /** id*/
    private Long id;
    /**
     * 题目
     */
    private String  title;
    /**
     * 图片路径
     */
    private String  imageUrl;
    /**
     * 图片点击后访问的url
     */
    private String url;
    /**
     * 是否展示
     */
    private Boolean exhibition;
    /**
     * 图片排序
     */
    private  Integer seq;




}
