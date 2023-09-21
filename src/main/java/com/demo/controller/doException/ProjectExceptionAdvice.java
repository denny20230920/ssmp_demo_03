package com.demo.controller.doException;

import com.demo.controller.result.Code;
import com.demo.controller.result.JsonResult;
import com.demo.exception.BusinessException;
import com.demo.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ProjectExceptionAdvice {

    @ExceptionHandler(BusinessException.class)
    public JsonResult<String> doBusinessException(BusinessException ex){
        log.error("业务层异常信息:"+ex);
        return new JsonResult<>(ex.getCode(),null,ex.getMessage());
    }

    @ExceptionHandler(SystemException.class)
    public JsonResult<String> doSystemException(SystemException ex){
        log.error("系统异常信息:"+ex);
        return new JsonResult<>(ex.getCode(),null,ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public JsonResult<String> doException(Exception ex){
        log.error("系统未知异常:"+ex);
        return new JsonResult<>(Code.SYSTEM_UNKNOW_ERR,null,"系统繁忙，请稍后重试！");
    }


}
