package com.chengyun.chengyun.httpclient;

/**
 * 文件：Http
 * 描述：
 * 创建人：JackieZhou
 * 创建时间：2017-11-07 18:36
 */

public class Http {
//    public static String postJsons(String uri, JSONObject json) {
//        String      a  = null;
//        try {
//
//            // 创建url资源
//            URL url = new URL(uri);
//            // 建立http连接
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            // 设置允许输出
//            conn.setDoOutput(true);
//
//            conn.setDoInput(true);
//
//            // 设置不用缓存
//            conn.setUseCaches(false);
//            // 设置传递方式
//            conn.setRequestMethod("POST");
//            // 设置维持长连接
//            conn.setRequestProperty("Connection", "Keep-Alive");
//            // 设置文件字符集:
//            conn.setRequestProperty("Charset", "UTF-8");
//            //转换为字节数组
//            byte[] data = (json.toString()).getBytes();
//            // 设置文件长度
//            conn.setRequestProperty("Content-Length", String.valueOf(data.length));
//
//            // 设置文件类型:
//            conn.setRequestProperty("contentType", "application/json");
//
//
//            // 开始连接请求
//            conn.connect();
//            OutputStream out = conn.getOutputStream();
//            // 写入请求的字符串
//            out.write((json.toString()).getBytes());
//            out.flush();
//            out.close();
//
//            System.out.println(conn.getResponseCode());
//
//            // 请求返回的状态
//            if (conn.getResponseCode() == 200) {
//                System.out.println("连接成功");
//                // 请求返回的数据
//                InputStream in = conn.getInputStream();
//
//                try {
//                    byte[] data1 = new byte[in.available()];
//                    in.read(data1);
//                    // 转成字符串
//                    a = new String(data1);
//                } catch (Exception e1) {
//                    // TODO Auto-generated catch block
//                    e1.printStackTrace();
//                }
//            } else {
//                System.out.println("no++");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return a;
//    }
}