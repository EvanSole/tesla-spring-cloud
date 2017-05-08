package com.tesla.cloud.core.exception.handle;

import com.alibaba.fastjson.JSON;
import com.tesla.cloud.core.exception.TeslaException;
import com.tesla.cloud.core.exception.handle.respoes.Response;
import com.tesla.cloud.core.exception.handle.util.ExceptionParseUtil;
import com.tesla.cloud.core.exception.handle.util.HandlerUtil;
import com.tesla.cloud.core.exception.handle.respoes.ResponseEnum;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import java.sql.SQLException;

public class ExceptionHandler implements Handler {

    private final Logger _logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @Override
    public HandleResponse handle(HandleRequest handleRequest) {
        Exception e = handleRequest.getException();
        String trace = RandomStringUtils.randomNumeric(36);
        Object result = null;
        String method = HandlerUtil.getMethodString(handleRequest.getProceedingJoinPoint());
        String params = HandlerUtil.getParamsString(handleRequest.getProceedingJoinPoint());
        String clazzName = e.getClass().getName();
        String message = e.getMessage();
        String exceptionString = ExceptionParseUtil.paraseErrorExceptionStack2String(e);
        if (e instanceof NullPointerException) {
            result = new Response(ResponseEnum.PARAM_ERROR.getCode(), ResponseEnum.PARAM_ERROR.getDescription());
            _logger.error("method: {}||| execute error!||| params: {}||| result: {}||| exception: {}||| message: {}||| stackTrace: {} ||| stringTrace: {} ", new Object[] { method, params, JSON.toJSONString(result), clazzName, message, exceptionString, trace });
        }else if(e instanceof DataAccessException || e instanceof SQLException){
            result = new Response(ResponseEnum.SQL_ERROR.getCode(), e.getMessage());
            _logger.error("method: {}||| execute error!||| params: {}||| result: {}||| exception: {}||| message: {}||| stackTrace: {} ||| stringTrace: {} ", new Object[] { method, params, JSON.toJSONString(result), clazzName, message, exceptionString, trace });
        }else if (e instanceof IllegalArgumentException) {
            result = new Response(ResponseEnum.PARAM_ERROR.getCode(), e.getMessage());
            _logger.error("method: {}||| execute error!||| params: {}||| result: {}||| exception: {}||| message: {}||| stackTrace: {} ||| stringTrace: {} ", new Object[] { method, params, JSON.toJSONString(result), clazzName, message, exceptionString, trace });
        }else if (e instanceof TeslaException) {
            String errorMsg = "";
            Integer code = ((TeslaException)e).getCode();
            if (code == null) {
                errorMsg = e.getMessage();
                result = new Response(ResponseEnum.PARAM_ERROR.getCode(), errorMsg);
            } else {
                errorMsg = ((TeslaException)e).getErrorMessage();
                if (StringUtils.isNotEmpty(errorMsg)) {
                    result = new Response(code, errorMsg);
                } else {
                    errorMsg = e.getMessage();
                    result = new Response(code, errorMsg);
                }

            }
            _logger.warn("method: {}||| execute error!||| params: {}||| result: {}||| exception: {}||| code: {} |||message: {}||| stackTrace: {} ||| stringTrace: {} ", new Object[] { method, params, JSON.toJSONString(result), clazzName, code, errorMsg, exceptionString, trace });
        }
        else {
            result = new Response(ResponseEnum.FAIL.getCode(), e.getMessage());
            _logger.error("method: {}||| execute error!||| params: {}||| result: {}||| exception: {}||| message: {}||| stackTrace: {} ||| stringTrace: {} ", new Object[] { method, params, JSON.toJSONString(result), clazzName, message, exceptionString, trace });
        }

        HandleResponse response = new HandleResponse();
        response.setResult(result);
        return response;
    }
}
