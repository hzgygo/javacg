package com.hzgy.core.common.constant;


/**
 * 系统常量定义类
 */

public class Constants {

    /**
     * UTF-8 统一编码
     */
    public static final String ENCODING_UTF = "UTF-8";

    /**
     * UTF-8 统一编码
     */
    public static final String CONTENT_TYPE_JSON = "application/json; charset=utf-8";


    public static final String CONTENT_TYPE_TEXT= "text/html;charset=utf-8";


    /**
     *
     * session验证代码
     */
    public static final String SESSION_CODE_KEY = "session_code";

    /**
     * 图形验证码
     */
    public static final String CAPTCHA_CODE_KEY = "captcha";

    /**
     * request请求中用于替换国际化文件动态内容的字段
     * 多个替换字段用逗号分隔（replaceParam1,replaceParam2,replaceParam3）
     */
    public static final String REPLACE_PARAMS = "_replaceParams";

    public static final String TREE_SELECTED_ID_KEY = "_tree_selected_id";

    /**
     * 域名配置文件key值
     */
    public static final String PLATFORM_DOMAIN = "platform.domain";

    public static final String RESOURCE_DOMAIN = "resource.domain";

    public static final String LOGIN_URL = "login.url";



    /**
     * springboot项目配置目录名
     */
    public static final String CONFIG = "config";

    /**
     * 区块链BAAS服务器地址
     */
    public static final String BLOCKCHAIN_BAAS_SERVICE_URL = "blockchain.baas.service.url";

    /**
     * 区块链BAAS服务器秘钥别名
     */
    public static final String BLOCKCHAIN_BAAS_CLIENT_ENCRYPT_ALIAS = "blockchain.baas.client.encrypt.alias";

    /**
     * 是否为调试模式
     */
    public static Boolean IS_DEBUG = false;

    /**
     * url或文件路径分割符号
     */
    public final static String PATH_SEPARATOR = "/";


    /**
     * 上传根目录
     */
    public final static String UPLOAD_PATH = "upload";

    /**
     * 资源文件根目录
     */
    public final static String RES_PATH = "res";

    /**
     * 二维码扫描内容页文件根目录
     */
    public final static String QRCODE_PAGE = "qrpage";

    /**
     * 资源文件存放根目录
     */
    public final static String FILE_ROOT_PATH = "file.root.path";


    /**
     * 文件服务地址
     */
    public final static String FILE_SERVICE_DOMAIN = "file.service.domain";



    /**
     * 文件下载存放根目录
     */
    public final static String FILE_DOWNLOAD_ROOT_PATH = "file.download.root.path";

    /**
     * 资源服务器域名
     */
    public final static String RES_DOMAIN = "resource.domain";

    /** mongodb特殊字符 */
    public final static String[] MONGODB_KEYWORDS = {"\\","(",")","^","$","*","+","?",".","|","[","]","{","}"};
}
