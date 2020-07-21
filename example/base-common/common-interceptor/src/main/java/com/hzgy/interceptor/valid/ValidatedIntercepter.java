package com.hzgy.interceptor.valid;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hzgy.core.common.ReturnCode;
import com.hzgy.core.common.constant.MDCConstants;
import com.hzgy.core.entity.ResultData;
import com.hzgy.core.exception.CodeException;
import com.hzgy.core.exception.DAOException;
import com.hzgy.core.util.LoggerUtils;
import com.hzgy.interceptor.annotation.IgnoreValid;
import com.hzgy.interceptor.annotation.Ignore;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

@Component
public class ValidatedIntercepter extends BaseInterceptor implements MethodInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(ValidatedIntercepter.class);

    @Override
    @SuppressWarnings("unchecked")
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object resultObject;
        Method method = invocation.getMethod();
        //获得切入目标对象
        Object target = invocation.getThis();
        //调用类名称
        String className = target.getClass().getSimpleName();
        if (method == null ) {
            return invocation.proceed();
        }
        //调用方法名称
        String methodName = method.getName();
        if (methodName.equals("toString")) {
            return invocation.proceed();
        }
        //执行方法是否有Converter注解 如果有直接执行
        Ignore ignore = method.getAnnotation(Ignore.class);
        if (ignore != null){
            return invocation.proceed();
        }

        //请求参数数组
        Object params[] = invocation.getArguments();
        //预处理request 和response对象
        preProcess();
        //打印请求参数
        ValidatedUtil.printRequestParams(className, methodName, params);
        //忽略验证注解确认是否进行请求参数验证，不为空说明忽略验证
        IgnoreValid ignoreValid = method.getAnnotation(IgnoreValid.class);
        if (ignoreValid != null) {
            logger.info(String.format("%s%s,ignore valid params.", className, methodName));
            resultObject = invocation.proceed();
            if (resultObject instanceof String) {
                ValidatedUtil.responseOut(super.getResponse(), String.valueOf(resultObject));
            }
            this.removeMDC();
            return resultObject;
        }
        //实体对象参数校验
        ResultData<Object> resultData = ValidatedUtil.validObject(className,methodName,params, super.resourceBundleMessageSource, super.getRequest());
        if (resultData != null) {
            ValidatedUtil.responseOut(super.getResponse(),resultData);
            this.removeMDC();
            return resultData;
        }
        //验证普通参数
        resultData = ValidatedUtil.validParams(target, method, params, super.resourceBundleMessageSource, super.getRequest());
        if (resultData != null) {
            ValidatedUtil.responseOut(super.getResponse(),resultData);
            this.removeMDC();
            return resultData;
        }
        //调用自定义实现基类抽象验证方法验证逻辑
        Object object = ValidatedUtil.invokDefinedValidMethod(method, params);
        resultData = ValidatedUtil.validObject(className,methodName,object,super.resourceBundleMessageSource, super.getRequest());
        if (resultData != null) {
            ValidatedUtil.responseOut(super.getResponse(),resultData);
            this.removeMDC();
            return resultData;
        }
        //调用自定义注解验证方法业务逻辑
        Object obj = ValidatedUtil.invokAnnotationValidMethod(method, params);
        resultData = ValidatedUtil.validObject(className,methodName,obj,super.resourceBundleMessageSource, super.getRequest());
        if (resultData != null) {
            ValidatedUtil.responseOut(super.getResponse(),resultData);
            this.removeMDC();
            return resultData;
        }
        try {
            //正常执行调用方法
            Object returnObj = invocation.proceed();
            //国际化提示信息
            String message = ValidatedUtil.getBundleMessage(className,methodName,String.valueOf(ReturnCode.SUCCESS.getCode()),super.resourceBundleMessageSource,null, super.getRequest());
            if (returnObj instanceof ResultData){
                resultData = (ResultData<Object>) returnObj;
                Integer rcode = resultData.getCode();
                if (rcode != null && rcode.equals(ReturnCode.SUCCESS.getCode())){
                    resultData.setMessage(message == null?ReturnCode.SUCCESS.getName():message);
                }
            }
            else{
                resultData = new ResultData();
                resultData.setMessage(message == null?ReturnCode.SUCCESS.getName():message);
                resultData.setData(returnObj);
                resultData.setCode(ReturnCode.SUCCESS.getCode());
            }
            //输入出返回参数
            resultData.setInvokeId(MDC.get(MDCConstants.INVOKE_ID));
            ValidatedUtil.printResponseParams(className, methodName, resultData);
            ValidatedUtil.responseOut(super.getResponse(),resultData);
            this.removeMDC();
            return returnObj;
        }
        catch(CodeException codeExc){
            if (resultData == null){
                resultData = new ResultData();
            }
            String message;
            Integer code = codeExc.getCode();
            boolean isDefautl = codeExc.getDefault();
            if (isDefautl){
                message = codeExc.getDefaultMsg();
            }
            else {
                String rmsg[] = codeExc.getReplaceMsg();
                message = ValidatedUtil.getBundleMessage(className,methodName, String.valueOf(code), super.resourceBundleMessageSource, rmsg, super.getRequest());
            }
            resultData.setCode(code);
            resultData.setMessage(message);
            resultData.setData(codeExc.getData());
            resultData.setInvokeId(MDC.get(MDCConstants.INVOKE_ID));
            ValidatedUtil.responseOut(super.getResponse(),resultData);
            LoggerUtils.info(logger,"response data:{}", JSON.toJSONString(resultData.toString(), SerializerFeature.WriteMapNullValue));
            this.removeMDC();
            return null;
        }
        catch(DAOException daoe){
            if (resultData == null){
                resultData = new ResultData();
            }
            logger.info("database execute error:",daoe);
            //运行异常统一使用系统配置的通用信息提示
            String message = ReturnCode.SYSTEM_ERROR.getName();
            resultData.setCode(ReturnCode.SYSTEM_ERROR.getCode());
            resultData.setMessage(message  + ":[errorCode="+ReturnCode.SYSTEM_ERROR.getCode()+"]==>" + daoe.getMessage());
            resultData.setInvokeId(MDC.get(MDCConstants.INVOKE_ID));
            ValidatedUtil.responseOut(super.getResponse(),resultData);
            LoggerUtils.info(logger,"response data:{}", JSON.toJSONString(resultData.toString(), SerializerFeature.WriteMapNullValue));
            this.removeMDC();
            return null;
        }
        catch(Exception daoe){
            if (resultData == null){
                resultData = new ResultData();
            }
            logger.info("system execute error:",daoe);
            //运行异常统一使用系统配置的通用信息提示
            String message = ReturnCode.SYSTEM_ERROR.getName();
            Integer code = ReturnCode.SYSTEM_ERROR.getCode();
            message = message  + ":[errorCode=" + code + "]==>" + daoe.getMessage();
            String emsg = daoe.getMessage();
            if (emsg.toLowerCase().contains("duplicate")){
                code = ReturnCode.DUPLICATE.getCode();
                message = ReturnCode.DUPLICATE.getName();
            }
            resultData.setCode(code);
            resultData.setMessage(message);
            resultData.setInvokeId(MDC.get(MDCConstants.INVOKE_ID));
            ValidatedUtil.responseOut(super.getResponse(),resultData);
            LoggerUtils.info(logger,"response data:{}", JSON.toJSONString(resultData.toString(), SerializerFeature.WriteMapNullValue));
            this.removeMDC();
            return null;
        }
    }

    private void removeMDC(){
        MDC.remove(MDCConstants.INVOKE_ID);
        MDC.remove(MDCConstants.USER_ACCOUNT);
        MDC.remove(MDCConstants.PACKAGE_NAME);
    }
}