package com.chengyun.chengyun.httpclient;

import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class HttpAPIUtil {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(HttpAPIUtil.class);

    @Autowired
    private volatile CloseableHttpClient httpClient;

    @Autowired
    private RequestConfig config;

    /**
     * 不带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     *
     * @param url
     * @return
     * @throws Exception
     */
    public String doGet(String url) throws Exception {
        // 声明 http get 请求
        HttpGet httpGet = new HttpGet(url);

        // 装载配置信息
        httpGet.setConfig(config);
        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpGet);

        // 判断状态码是否为200
        if (response.getStatusLine().getStatusCode() == 200) {
            // 返回响应体的内容
            String a= EntityUtils.toString(response.getEntity(), "UTF-8");
            JSONObject aJson = new JSONObject(a);
            aJson.put("status", response.getStatusLine().getStatusCode());
            a = aJson.toString();
            return a;
        } else {
            return new String("{\"status\":" + response.getStatusLine().getStatusCode() + ",\"errmsg\":\"" + response.getStatusLine().getReasonPhrase() + "\"}");
        }
//        return null;
    }

    public String doGetHeader(String url, String requestTime,String requestIp,String appId,String signature) throws Exception {

        // 声明 http get 请求
        HttpGet httpGet = new HttpGet(url);

        // 装载配置信息
        httpGet.setConfig(config);
        httpGet.setHeader("request_time", requestTime);
        httpGet.setHeader("request_ip", requestIp);
        httpGet.setHeader("app_id", appId);
        httpGet.setHeader("signature", signature);
        //
        logger.info("请求参数==request_time:"+requestTime+";request_ip=="+requestIp+";app_id=="+appId+";signature=="+signature);
        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpGet);

        logger.info("响应tatusCode=="+response.getStatusLine().getStatusCode());

//        System.out.println(response.getStatusLine().getStatusCode());
        // 判断状态码是否为200
        if (response.getStatusLine().getStatusCode() == 200) {
            // 返回响应体的内容
            String a= EntityUtils.toString(response.getEntity(), "UTF-8");

//            logger.info("返回响应体的内容=="+a);

            JSONObject aJson = new JSONObject(a);
            aJson.put("status", response.getStatusLine().getStatusCode());
            a = aJson.toString();

            logger.info("返回响应体的内容=="+a);

            return a;
        } else {
            return new String("{\"status\":" + response.getStatusLine().getStatusCode() + ",\"errmsg\":\"" + response.getStatusLine().getReasonPhrase() + "\"}");
        }
//        return null;
    }


    public String doGetHeaders(String url, String signature) throws Exception {

        // 声明 http get 请求
        HttpGet httpGet = new HttpGet(url);

        // 装载配置信息
        httpGet.setConfig(config);
        httpGet.setHeader("signature", signature);
        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpGet);

        // 判断状态码是否为200
        if (response.getStatusLine().getStatusCode() == 200) {
            // 返回响应体的内容
            String a= EntityUtils.toString(response.getEntity(), "UTF-8");
            JSONObject aJson = new JSONObject(a);
            aJson.put("status", response.getStatusLine().getStatusCode());
            a = aJson.toString();
            return a;
        } else {
            return new String("{\"status\":" + response.getStatusLine().getStatusCode() + ",\"errmsg\":\"" + response.getStatusLine().getReasonPhrase() + "\"}");
        }
//        return null;
    }



    /**
     * 带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     *
     * @param url
     * @return
     * @throws Exception
     */
    public String doGet(String url, Map<String, Object> map) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(url);

        if (map != null) {
            // 遍历map,拼接请求参数
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }

        // 调用不带参数的get请求
        return this.doGet(uriBuilder.build().toString());

    }

    /**
     * 带参数的post请求
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public HttpResult doPost(String url, Map<String, Object> map) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        // 加入配置信息
        httpPost.setConfig(config);

        // 判断map是否为空，不为空则进行遍历，封装from表单对象
        if (map != null) {
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            // 构造from表单对象
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");

            // 把表单放到post里
            httpPost.setEntity(urlEncodedFormEntity);
        }

        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));
    }
//
//    public HttpResult doPostJson(String url, String json) {
//        String encoderJson = null;
//        try {
//            encoderJson = URLEncoder.encode(json, HTTP.UTF_8);
//            HttpPost httpPost = new HttpPost(url);
//            httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
//            StringEntity se = new StringEntity(encoderJson);
//            se.setContentType("text/json");
//            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
//            httpPost.setEntity(se);
//            CloseableHttpResponse response = this.httpClient.execute(httpPost);
//            return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
//                    response.getEntity(), "UTF-8"));
//
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public String doPostJsons(String uri, String json) {
        String a = null;
        try {
//            System.out.println(json.toString());
            // 创建url资源
            URL url = new URL(uri);
            // 建立http连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置允许输出
            conn.setDoOutput(true);

            conn.setDoInput(true);

            // 设置不用缓存
            conn.setUseCaches(true);
            // 设置传递方式
            conn.setRequestMethod("POST");
            // 设置维持长连接
            conn.setRequestProperty("Connection", "Keep-Alive");
            // 设置文件字符集:
            conn.setRequestProperty("Charset", "UTF-8");
            //转换为字节数组
//
//            byte[] bs = json.getBytes();
//            String str = new String(bs, "GBK");
//
//            byte[] data = str.getBytes("GBK");
            // 设置文件长度
//            System.out.println(String.valueOf(data.length));
            Integer len = json.length();
//            conn.setRequestProperty("Content-Length", String.valueOf(data.length));
            conn.setRequestProperty("Content-Length", len.toString());

            // 设置文件类型:
            conn.setRequestProperty("Content-Type", "application/json");


            // 开始连接请求
            conn.connect();
            OutputStream out = conn.getOutputStream();
            // 写入请求的字符串
            out.write(json.getBytes("utf-8"));
            out.flush();
            out.close();

//            System.out.println(conn.getResponseCode());

            // 请求返回的状态
            if (conn.getResponseCode() == 200) {
//                System.out.println("success");
                // 请求返回的数据
                InputStream in = conn.getInputStream();

                try {
                    byte[] data1 = new byte[in.available()];
                    in.read(data1);
                    // 转成字符串
                    a = new String(data1);
                    JSONObject aJson = new JSONObject(a);
                    aJson.put("status", conn.getResponseCode());
                    a = aJson.toString();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } else {

                a = new String("{\"status\":" + conn.getResponseCode() + ",\"errmsg\":\"" + conn.getResponseMessage() + "\"}");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    public String doPostJsonsXml(String uri, String json) {
        String a = null;
        try {
//            System.out.println(json.toString());
            // 创建url资源
            URL url = new URL(uri);
            // 建立http连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置允许输出
            conn.setDoOutput(true);

            conn.setDoInput(true);

            // 设置不用缓存
            conn.setUseCaches(true);
            // 设置传递方式
            conn.setRequestMethod("POST");
            // 设置维持长连接
            conn.setRequestProperty("Connection", "Keep-Alive");
            // 设置文件字符集:
            conn.setRequestProperty("Charset", "UTF-8");
            //转换为字节数组
//
//            byte[] bs = json.getBytes();
//            String str = new String(bs, "GBK");
//
//            byte[] data = str.getBytes("GBK");
            // 设置文件长度
//            System.out.println(String.valueOf(data.length));
            Integer len = json.length();
//            conn.setRequestProperty("Content-Length", String.valueOf(data.length));
            conn.setRequestProperty("Content-Length", len.toString());

            // 设置文件类型:
            conn.setRequestProperty("Content-Type", "application/json");


            // 开始连接请求
            conn.connect();
            OutputStream out = conn.getOutputStream();
            // 写入请求的字符串
            out.write(json.getBytes("utf-8"));
            out.flush();
            out.close();

//            System.out.println(conn.getResponseCode());

            // 请求返回的状态
            if (conn.getResponseCode() == 200) {
//                System.out.println("success");
                // 请求返回的数据
                InputStream in = conn.getInputStream();

                try {
                    byte[] data1 = new byte[in.available()];
                    in.read(data1);
                    // 转成字符串
                    a = new String(data1);


                    JSONObject aJson = XML.toJSONObject(a);
                    aJson.put("status", conn.getResponseCode());
                    a = aJson.toString();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } else {

                a = new String("{\"status\":" + conn.getResponseCode() + ",\"errmsg\":\"" + conn.getResponseMessage() + "\"}");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }


    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter    out    = null;
        BufferedReader in     = null;
        String         result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
//            URLConnection con = realUrl.openConnection();
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            //设置通用的请求属性
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:21.0) Gecko/20100101 Firefox/21.0)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            OutputStreamWriter outWriter = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
            out = new PrintWriter(outWriter);
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                result += line;
            }
//            System.out.println(result);

            JSONObject aJson = new JSONObject(result);
            aJson.put("status", conn.getResponseCode());
            result = aJson.toString();
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 不带参数post请求
     *
     * @param url
     * @return
     * @throws Exception
     */
    public HttpResult doPost(String url) throws Exception {
        return this.doPost(url, null);
    }

    /**
     * json带参发送post请求
     *
     * @param url  url路径
     * @param
     * @return
     */
    public String HttpPostWithJson(String url,String appid,String curTime,String param,String checkSum,String body) {
        String                  returnValue     = "这是默认返回值，接口调用失败";
        CloseableHttpClient     httpClient      = HttpClients.createDefault();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        try {
            //第一步：创建HttpClient对象
            httpClient = HttpClients.createDefault();

            //第二步：创建httpPost对象
            HttpPost httpPost = new HttpPost(url);

            //第三步：给httpPost设置JSON格式的参数
            StringEntity requestEntity = new StringEntity(body, "utf-8");
            requestEntity.setContentEncoding("UTF-8");
            httpPost.setHeader("cache-control", "no-cache");
            httpPost.setHeader("Content-type", "application/json");


            httpPost.setHeader("X-Appid", appid);
            httpPost.setHeader("X-CurTime", curTime);
            httpPost.setHeader("X-Param", param);
            httpPost.setHeader("X-CheckSum", checkSum);
            httpPost.setEntity(requestEntity);

            //第四步：发送HttpPost请求，获取返回值
            returnValue = httpClient.execute(httpPost, responseHandler); //调接口获取返回值时，必须用此方法
//            CloseableHttpResponse httpResonse = httpClient.execute(httpPost);
//            int statusCode = httpResonse.getStatusLine().getStatusCode();
//            if(statusCode!=200){
//              System.out.println("请求发送失败，失败的返回参数为："+httpResonse.getStatusLine());
//              returnValue = httpResonse.getStatusLine().toString();
//            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //第五步：处理返回值
        return returnValue;
    }
}
