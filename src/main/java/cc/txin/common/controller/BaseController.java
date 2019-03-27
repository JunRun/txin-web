package cc.txin.common.controller;

import cc.txin.common.model.AjaxResult;
import cc.txin.common.util.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 控制器基类
 *
 * @author: 印修河
 * @date: 2017/12/6 18:41
 */
public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 自定义异常处理类
     * @param e
     * @return
     */
    @ExceptionHandler(MyException.class)
    @ResponseBody
    public AjaxResult myExceptionHandler(RuntimeException e) {
        logger.error(e.getMessage(), e);
        return new AjaxResult(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    /**
     * controller全局异常处理类
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public AjaxResult runtimeExceptionHandler(RuntimeException e) {
        logger.error(e.getMessage(), e);
        return new AjaxResult(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
