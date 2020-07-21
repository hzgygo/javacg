package com.hzgy.core.util;


import com.alibaba.fastjson.JSON;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpClientUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    public static final String HEADER_JSON = "application/json;charset=utf-8";

    public static final String HEADER_FORM = "application/x-www-form-urlencoded";

    /**
     * get请求
     * @param url 请求地址
     * @param param 请求参数
     * @return 返回字符串
     */
    public static String doGet(String url, Map<String, String> param) {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            //创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();
            //创建Http Get请求
            HttpGet httpGet = new HttpGet(uri);
            //执行请求
            response = httpClient.execute(httpGet);
            //判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String doGet(String url) {
        return doGet(url, null);
    }


    /**
     * post请求
     * @param url 请求地址
     * @param param 请求参数
     * @return 返回字符串
     */
    public static String doPost(String url,Map<String, Object> param) {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            //创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            String params = JSON.toJSONString(param);
            httpPost.setEntity(new StringEntity(params,"utf-8"));
            httpPost.setHeader("Content-Type", HEADER_JSON);
            //执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String post(String url,Map<String, String> param) {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            //创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            String params = JSON.toJSONString(param);
            httpPost.setEntity(new StringEntity(params,"utf-8"));
            httpPost.setHeader("Content-Type", HEADER_JSON);
            //执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String postMap(String url,Map<String, Object> param) {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            //创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            String params = JSON.toJSONString(param);
            httpPost.setEntity(new StringEntity(params,"utf-8"));
            httpPost.setHeader("Content-Type", HEADER_JSON);
            //执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * post请求
     * @param url 请求地址
     * @param param 请求参数
     * @return 返回字符串
     */
    public static String doPost(String url,Map<String, String> param,String basicSecret) {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        CloseableHttpResponse response = null;
        try {
            //创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            if (param != null && !param.isEmpty()){
                List<NameValuePair> nameValuePairs = new ArrayList<>();
                Set<Map.Entry<String, String>> set = param.entrySet();
                for (Map.Entry<String, String> entry : set){
                    String key = entry.getKey();
                    String value = entry.getValue();
                    nameValuePairs.add(new BasicNameValuePair(key, value));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            }
            httpPost.addHeader("Content-type", HEADER_FORM);
            httpPost.setHeader("Authorization",basicSecret);
            //执行http请求
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 获取用户真实IP地址
     * @param request 请求对象
     * @return         返回ip地址
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    /**
     * 远端服务器下载文件
     * @param fileUrl    远端地址
     * @param fileLocal  本地路径
     * @return 是否成功
     */
    public static boolean downloadFile(String fileUrl,String fileLocal){
        try {
            URL url = new URL(fileUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(6000);
            conn.setReadTimeout(6000);
            int code = conn.getResponseCode();
            if (code != HttpURLConnection.HTTP_OK) {
                return false;
            }
            //得到输入流
            InputStream inputStream = conn.getInputStream();
            //获取自己数组
            byte[] getData = readInputStream(inputStream);
            //文件保存位置
            File local = new File(fileLocal);
            File parentFile = local.getParentFile();
            if(!parentFile.exists()){
                parentFile.mkdir();
            }
            FileOutputStream fos = new FileOutputStream(local);
            fos.write(getData);
            fos.close();
            if(inputStream != null){
                inputStream.close();
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 从输入流中获取字节数组
     * @param inputStream 输入流
     * @return 字节
     * @throws IOException 异常
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    /**
     * 读取文件内容
     * @param file  文件路径
     * @return 二进制内容
     * @throws IOException 异常
     */
    public static byte[] readFile(String file) throws IOException {
        File localFile = new File(file);
        InputStream inputStream = new BufferedInputStream(new FileInputStream(localFile));
        return readInputStream(inputStream);
    }
}
