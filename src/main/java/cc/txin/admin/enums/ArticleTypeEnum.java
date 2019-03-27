package cc.txin.admin.enums;

/**
 *
 * @author: 印修河
 * @date: 2018/10/14 14:52
 */
public enum ArticleTypeEnum {

    /** 公司新闻 */
    COMPANY_NEWS("COMPANY_NEWS", "公司新闻"),
    /** 行业新闻 */
    INDUSTRY_NEWS("INDUSTRY_NEWS", "行业新闻"),
    /** 解决方案 */
    SOLUTION("SOLUTION", "解决方案"),
    /** 售后问题 */
    AFTER_SALES_PROBLEM("AFTER_SALES_PROBLEM", "售后问题"),
    /** 使用问题 */
    USE_PROBLEM("USE_PROBLEM", "使用问题"),
    /** 购买问题 */
    BUY_PROBLEM("BUY_PROBLEM", "购买问题");

    private String code;
    private String name;

    ArticleTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
