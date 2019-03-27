package cc.txin.common.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 类描述：用户密码加密工具类 <br/>
 * 创建人：印修河 <br/>
 * 创建时间：2017-4-3 下午11:21:57 <br/>
 */
public class PasswordUtil {

    // 加密盐成分，防破解
    private static final String SALT = "rui3zhi6mei9yu";

    public static String encript(String pwd) {
        return DigestUtils.md5Hex((SALT + pwd).getBytes());
    }
}
