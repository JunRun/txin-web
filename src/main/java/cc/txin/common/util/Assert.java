package cc.txin.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 自定义断言类
 *
 * @author 印修河
 * @date 2017 /12/22 9:10
 */
public class Assert {

    /**
     * 断言为空
     *
     * @param obj     the obj
     * @param message the message
     */
    public static void isNull(Object obj, String message){
        if(obj != null){
            //如果为字符串,并且不为空
            if(obj instanceof String && StringUtils.isBlank((String)obj)){
                return ;
            }
            throw  new MyException(message);
        }
    }

    /**
     * 断言不为空
     *
     * @param obj     the obj
     * @param message the message
     */
    public static void notNull(Object obj, String message){
        if(obj == null){
            throw  new MyException(message);
        }
        //如果为字符串,并且不为空
        if(obj instanceof String && StringUtils.isBlank((String)obj)){
            throw  new MyException(message);
        }
    }

    /**
     * Is true.
     *
     * @param b       the b
     * @param message the message
     */
    public static void isTrue(boolean b, String message){
        if(!b){
            throw new MyException(message);
        }
    }

    /**
     * Is false.
     *
     * @param b       the b
     * @param message the message
     */
    public static void isFalse(boolean b, String message){
        if(b){
            throw new MyException(message);
        }
    }

}
