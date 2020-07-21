package com.hzgy.interceptor.valid;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hzgy.core.common.DateFormat;
import com.hzgy.core.common.ReturnCode;
import com.hzgy.core.common.constant.MDCConstants;
import com.hzgy.core.entity.BaseDto;
import com.hzgy.core.entity.BaseInput;
import com.hzgy.core.entity.ResultData;
import com.hzgy.core.util.DateUtil;
import com.hzgy.core.util.LoggerUtils;
import com.hzgy.core.util.StringUtil;
import com.hzgy.interceptor.annotation.Converter;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.env.Environment;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static com.hzgy.core.common.constant.Constants.*;

@SuppressWarnings("unchecked")
public class ValidatedUtil {

    private static final Logger logger = LoggerFactory.getLogger(ValidatedUtil.class);
    private static final ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final ExecutableValidator validator = factory.getValidator().forExecutables();

    @Resource
    private Environment environment;

    /**
     * 获取类的验证类对象设置的分组验证信息
     *
     * @param method 控制器验证方法
     * @return 返回分组校验类数组
     */
    public static Class<?>[] getMethodValidGroups(Method method) {
        if (method == null) {
            return null;
        }
        Annotation[][] annotations = method.getParameterAnnotations();
        for (Annotation[] annot : annotations) {
            for (Object obj : annot) {
                if (obj instanceof Validated) {
                    return ((Validated)obj).value();
                }
            }
        }
        return null;
    }

    /**
     * 获取参数注解名称
     * @param method 调用方法名称
     * @param index 参数个数（第几个）
     * @return 注解名称
     */
    public static String getParamAnnotation(Method method,int index) {
        if (method == null) {
            return null;
        }
        Annotation[][] annotations = method.getParameterAnnotations();
        for (int i=0;i<annotations.length;i++){
            if (i == index){
                Annotation[] annot = annotations[i];
                if (annot.length > 0){
                  return  annot[0].annotationType().getSimpleName();
                }
            }
        }
        return null;
    }

    /**
     * 获取验证类方法对象
     *
     * @param params 参数列表
     * @return 返回方法对象
     */
    public static Method getValidClass(Object[] params) {
        List<Object> paramList = ValidatedUtil.getValidParams(params);
        for (Object obj : paramList) {
            Method[] methods = obj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                String mname = method.getName();
                if (BaseInput.VALID_METHOD_NAME.equals(mname)) {
                    return method;
                }
            }
        }
        return null;
    }

    /**
     * 获取控制器请求参数对象dto
     * @param params 方法参数
     * @return 返回dto对象
     */
    public static Object getInputParam(Object[] params) {
        for (Object obj : params) {
            //过滤掉特殊对象，保留基本参数对象
            if (obj instanceof HttpServletRequest
                    || obj instanceof HttpServletResponse
                    || obj instanceof BeanPropertyBindingResult) {
                continue;
            }
            if (obj instanceof BaseDto){
                return obj;
            }
        }
        return null;
    }

    /**
     * 调用数据转换方法
     * @param targetObj 控制器类对象
     * @param data 入参或者返回数据
     * @return 转换后的结果对象
     */
    public static Object invokDefinedConverterMethod(String type,Object targetObj,String targetMethod,Object data) {
        Method[] methods = targetObj.getClass().getDeclaredMethods();
        for (Method method : methods) {
            Converter converter = method.getDeclaredAnnotation(Converter.class);
            if (converter != null) {
                //转换方法类型，input，output 两种
                String ctype = converter.type();
                //只有类型相等，才调用自定义转换方法
                if (type.equals(ctype)) {
                    //数据转换方法名称
                    String cname = converter.methodName();
                    //如果转换方法名称也调用方法名称一致
                    //则执行转换方法，否则不还行
                    if (cname.equals(targetMethod)) {
                        try {
                            Object resultObj = invokeMethod(targetObj, method, new Object[]{data});
                            if (IS_DEBUG) {
                                logger.info(String.format("converter result[method:%s-type:%s]:%s", targetMethod, type, JSON.toJSONString(resultObj, SerializerFeature.WriteMapNullValue)));
                            }
                            return resultObj;
                        }
                        catch(Exception e){
                            if (IS_DEBUG) {
                                logger.info(String.format("converter invoke method[%s] error:%s", method.getName(), e.getMessage()));
                            }
                            return null;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * 代用验证类自定义验证方法
     *
     * @param controllMethod 控制器方法
     * @param params 控制器参数数组
     * @return 返回resultData json 对象
     */
    public static Object invokDefinedValidMethod(Method controllMethod, Object[] params) {
        //分组校验的类数组
        Class<?>[] groups = ValidatedUtil.getMethodValidGroups(controllMethod);
        List<Object> paramList = ValidatedUtil.getValidParams(params);
        for (Object obj : paramList) {
            Method[] methods = obj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                String mname = method.getName();
                if (BaseInput.VALID_METHOD_NAME.equals(mname)) {
                    //调用方法
                    return invokeMethod(obj, method, new Object[]{groups, params});
                }
            }
        }
        return null;
    }

    /**
     * 调用方法
     *
     * @param targetObj 调用对象
     * @param method    方法对象
     * @param params    方法参数数组
     * @return 返回Object
     */
    private static Object invokeMethod(Object targetObj, Method method, Object[] params) {
        try {
            return method.invoke(targetObj, params);
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.info(String.format("invoke method error:%s", e.getMessage()));
        }
        return null;
    }

    /**
     * 调用验证类内自定义注解的方法
     *
     * @param controllMethod 控制器方法
     * @param params         控制器参数数组
     * @return 返回调用结果
     */
    public static Object invokAnnotationValidMethod(Method controllMethod, Object[] params) {
        //分组校验的类数组
        Class<?>[] groups = ValidatedUtil.getMethodValidGroups(controllMethod);
        //获取控制器方法可用参数（验证参数）
        List<Object> paramList = ValidatedUtil.getValidParams(params);
        for (Object obj : paramList) {
            Method[] methods = obj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (groups != null && groups.length > 0) {
                    //循环获取带有指定分组注解的方法，执行验证
                    for (Class cls : groups) {
                        if (method.getDeclaredAnnotation(cls) != null) {
                            //调用方法
                            return invokeMethod(obj, method, new Object[]{params});
                        }
                    }
                }
            }
        }
        return null;
    }


    /**
     * 获取目标验证参数对象（过滤掉分验证参数对象）
     *
     * @param params 控制器参数数组
     * @return 返回过滤后的参数（主要是指验证参数）
     */
    public static List<Object> getValidParams(Object[] params) {
        List<Object> paramList = new ArrayList<>();
        for (Object obj : params) {
            //过滤掉特殊对象，保留基本参数对象
            if (obj instanceof HttpServletRequest
                    || obj instanceof HttpServletResponse
                    || obj instanceof BeanPropertyBindingResult) {
                continue;
            }
            paramList.add(obj);
        }
        return paramList;
    }

    /**
     * 打印请求参数
     *
     * @param className  类名称
     * @param methodName 调用方法名称
     * @param args       请求参数
     */
    public static void printRequestParams(String className, String methodName, Object[] args) {
        String invokeId  = DateUtil.format(new Date(),DateFormat.DATE_SECOND.getValue()) + "invokeId" + (int) (Math.random() * 1000000);
        MDC.put(MDCConstants.INVOKE_ID,invokeId);
        if (args == null || args.length == 0) {
            logger.info("no data");
            return;
        }
        List<Object> paramList = ValidatedUtil.getValidParams(args);
        for (Object obj : paramList) {
            if (obj instanceof MultipartFile){
                continue;
            }
            if(obj instanceof BaseInput){
                try{
                    BaseInput baseInput = (BaseInput) obj;
                    MDC.put(MDCConstants.USER_ACCOUNT,baseInput.getLoginUserAccount());
                    MDC.put(MDCConstants.PACKAGE_NAME,baseInput.getClass().getName().split("\\.")[4]);
                }catch (Exception e){}

            }
            LoggerUtils.info(logger,"--- request controller:{}.{}()",className,methodName);
            if (IS_DEBUG) {
                LoggerUtils.info(logger,"request params:{}",JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue));
            }
        }
    }

    /**
     * 打印返回数据
     *
     * @param className  类名称
     * @param methodName 调用方法名称
     * @param data       返回参数
     */
    public static void printResponseParams(String className, String methodName, Object data) {
        logger.info(String.format("response data:%s.%s()", className, methodName));
        if (data == null) {
            logger.info("no data");
            return;
        }
        if (IS_DEBUG) {
            LoggerUtils.info(logger,"response data:{}",JSON.toJSONString(data.toString(), SerializerFeature.WriteMapNullValue));
        }
    }

    /**
     * 验证输入对象字段
     * @param methodName    方法名称
     * @param args          参数
     * @param messageSource 国际化文件对象
     * @param request       request请求对象
     * @return 返回resultData json 字符串
     */
    public static ResultData validObject(String className,String methodName,Object[] args, ResourceBundleMessageSource messageSource, HttpServletRequest request) {
        //寻找带BindingResult参数的方法，然后判断是否有error，如果有则是校验不通过
        for (Object object : args) {
            if (object instanceof BeanPropertyBindingResult) {
                //有校验
                BeanPropertyBindingResult result = (BeanPropertyBindingResult) object;
                return handleBindingResult(className,methodName,messageSource,result,request);
            }
        }
        return null;
    }

    /**
     * 验证自定义返回对象字段
     * @param methodName  方法名称
     * @param object  定义方法返回结果
     * @param messageSource 国际化文件对象
     * @param request request请求对象
     * @return 返回resultData对象
     */
    public static ResultData validObject(String className,String methodName,Object object, ResourceBundleMessageSource messageSource, HttpServletRequest request) {
        if (object instanceof  BeanPropertyBindingResult) {
            BeanPropertyBindingResult result = (BeanPropertyBindingResult) object;
            return handleBindingResult(className,methodName,messageSource, result, request);
        }
        return null;
    }

    /**
     * 获取资源文件信息
     * @param methodName 方法名称
     * @param code 业务代码或验证时BindingResult中的code
     * @param messageSource 国际化资源文件绑定对象
     * @param request request 对象
     * @return 提示消息字符串
     */
    public static String getBundleMessage(String className,String methodName,String code, ResourceBundleMessageSource messageSource,String replaceMsg[],HttpServletRequest request){
        //国际化文件key=控制器类名+方法名+验证code(注解名+实体名称+字段名称)
        String methodKey = methodName + "." + code;
        String message = null;
        //获取替换字段的值数组
        String replaceValue [] = null;
        if (replaceMsg != null && replaceMsg.length > 0){
            replaceValue = replaceMsg;
        }
        else{
            replaceValue = ValidatedUtil.replaceArgs(request);
        }
        //
        logger.info(String.format("request lang: %s", request.getLocale().toString()));
        //唯一key
        String uniqueKey = className + "." + methodKey;
        logger.info(String.format("message key code:%s", uniqueKey));
        try {
            message = messageSource.getMessage(uniqueKey, replaceValue, Locale.CHINA);
        } catch (Exception e) {
            logger.info(String.format("message source not contain key:%s", uniqueKey));
            try {
                message = messageSource.getMessage(methodKey, replaceValue, Locale.CHINA);
            }catch (Exception ex) {
                logger.info(String.format("message source not contain key:%s", methodKey));
                String classKey = className + "." + code;
                try {
                    message = messageSource.getMessage(classKey, replaceValue, Locale.CHINA);
                }catch (Exception exx) {
                    message = methodKey;
                }
            }
        }
        logger.info("get message [{}]",message);
        return message;
    }

    /**
     * 处理国际化文件中动态提花替换的字段
     * @param request request请求对象
     * @return 替换字段的值数组
     */
    private static String [] replaceArgs(HttpServletRequest request){
        List<String> list = new ArrayList<>();
        String replaceParams = request.getParameter(REPLACE_PARAMS);
        if (!StringUtil.isEmpty(replaceParams)){
            String param [] = replaceParams.split(",");
            for(String replaceParam:param){
               String replaceValue = request.getParameter(replaceParam);
               if (!StringUtil.isEmpty(replaceValue)){
                   list.add(replaceValue);
               }
            }
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * 错误验证绑定处理
     * @param methodName    方法名称
     * @param messageSource 国际化文件对象
     * @param result  BindingResult对象
     * @param request request请求对象
     * @return 返回resultData对象
     */
    public static ResultData handleBindingResult(String className,String methodName,ResourceBundleMessageSource messageSource,BeanPropertyBindingResult result, HttpServletRequest request){
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            if (list != null && list.size() > 0){
                ObjectError error = list.get(0);
                ResultData resultData = new ResultData();
                resultData.setCode(ReturnCode.PARAM_ERROR.getCode());
                //错误提示信息
                String message;
                String codes[] = error.getCodes();
                if (codes != null && codes.length > 0) {
                    String code = codes[0];
                    int annotationIndex = code.indexOf(".");
                    int paramIndex = code.lastIndexOf(".");
                    String codeTemp;
                    if (annotationIndex > 0){
                        String annotation = code.substring(0,annotationIndex);
                        String param = code.substring(paramIndex+1,code.length());
                        codeTemp = annotation + "." + param;
                    }
                    else{
                        codeTemp = code;
                    }
                    message = getBundleMessage(className,methodName,codeTemp,messageSource,null,request);
                    if (StringUtil.isEmpty(message)) {
                        message = error.getDefaultMessage() + "[" + code + "]";
                    }
                } else {
                    message = error.getDefaultMessage();
                }
                resultData.setMessage(message);
                if (IS_DEBUG) {
                    logger.info(String.format("valid bean binding params error:%s", JSON.toJSONString(resultData, SerializerFeature.WriteMapNullValue)));
                }
                return resultData;
            }
        }
        return null;
    }

    /**
     * 非对象参数校验
     *
     * @param target        校验目标
     * @param method        校验方法
     * @param args          参数
     * @param messageSource 国际化资源文件对象
     * @param request       request请求对象
     * @return 返回resultData对象
     */
    public static ResultData validParams(Object target, Method method, Object[] args, ResourceBundleMessageSource messageSource, HttpServletRequest request) {
        String methodName = method.getName();
        // 执行校验，获得校验结果
        Set<ConstraintViolation<Object>> validResult = validator.validateParameters(target, method, args);
        //如果有校验不通过的
        if (!validResult.isEmpty()) {
            // 获得方法的参数名称
            String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
            for (ConstraintViolation<Object> constraintViolation : validResult) {
                // 获得校验的参数路径信息
                PathImpl pathImpl = (PathImpl) constraintViolation.getPropertyPath();
                // 获得校验的参数位置
                int paramIndex = pathImpl.getLeafNode().getParameterIndex();
                // 获得校验的参数名称
                String paramName = parameterNames[paramIndex];
                //参数注解名称
                String annotationName = ValidatedUtil.getParamAnnotation(method,paramIndex);
                //返回结果对象
                ResultData resultData = new ResultData();
                resultData.setCode(ReturnCode.PARAM_ERROR.getCode());
                //错误提示信息
                String message = null;
                //类名称
                String className = target.getClass().getSimpleName();
                //国际化文件key=类名+方法名+验证注解名+参数名
                String code = annotationName + "." + paramName;
                //获取国际化文件内容
                message = getBundleMessage(className,methodName,code,messageSource,null,request);
                //提示消息为空，获取默认的提示消息
                if (StringUtil.isEmpty(message)) {
                    message = constraintViolation.getMessage();
                }
                resultData.setMessage(message);
                if (IS_DEBUG) {
                    logger.info(String.format("valid params error:%s", JSON.toJSONString(resultData, SerializerFeature.WriteMapNullValue)));
                }
                return resultData;
            }
        }
        return null;
    }

    /**
     * 验证未通过返回验证信息
     *
     * @param response   http response对象
     * @param resultData 返回结果字符串
     * @throws IOException 抛io异常
     */
    public static void responseOut(HttpServletResponse response, ResultData resultData) throws IOException {
        response.setCharacterEncoding(ENCODING_UTF);
        response.setContentType(CONTENT_TYPE_JSON);
        try {
            String reult = "";
            if (resultData != null){
                reult = JSON.toJSONString(resultData,SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect);
            }
//            if (IS_DEBUG) {
//                logger.info(String.format("responsse result:%s", reult));
//            }
            response.getOutputStream().write(reult.getBytes(ENCODING_UTF));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 强制关闭防止重写
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }
    }

    public static void responseOut(HttpServletResponse response, String object) throws IOException {
        response.setCharacterEncoding(ENCODING_UTF);
        response.setContentType(CONTENT_TYPE_TEXT);
        try {
            if (IS_DEBUG) {
                logger.info(String.format("response result:%s", object));
            }
            response.getOutputStream().write(object.getBytes(ENCODING_UTF));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 强制关闭防止重写
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }
    }

}