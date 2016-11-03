package com.iuoon.tw.util;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by mwuyz on 2016/10/31.
 */
public class HttpUtil {

    private static Logger logger= LoggerFactory.getLogger(HttpUtil.class);

    public static String api_url="https://www.oschina.net/action/apiv2/";

    public static String getMsg(String action){
        String url=api_url+action;
        return sendGetRequest(url,"UTF-8","UTF-8");
    }

    public static String postMsg(String action,String params){
        String url=String.format(api_url,action);
        return sendPostRequest(url,params,"UTF-8","UTF-8");
    }

    /**
     * 发送HTTP_POST请求
     * @param reqURL        请求地址
     * @param params        请求参数
     * @param encodeCharset 编码字符集,编码请求数据时用之,其为null时默认采用UTF-8解码
     * @param decodeCharset 解码字符集,解析响应数据时用之,其为null时默认采用UTF-8解码
     * @return 远程主机响应正文
     */
    public static String sendPostRequest(String reqURL, String params, String encodeCharset, String decodeCharset){
        String responseContent = null;
//        HttpClient httpClient = new DefaultHttpClient();
        HttpClient httpClient = getNewHttpClient();
        //连接超时时间
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,60000);
        //数据传输时间
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,30000);
        HttpPost httpPost = new HttpPost(reqURL);
        httpPost.setHeader(HTTP.CONTENT_TYPE, "application/json");
        StringEntity paramsEntity = new StringEntity(params.toString(), encodeCharset);
        try{
            httpPost.setEntity(paramsEntity);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            StatusLine status = response.getStatusLine();
            int statuscode=status.getStatusCode();
            if (null != entity) {
                if (statuscode== HttpStatus.SC_OK) {
                    responseContent = EntityUtils.toString(entity, decodeCharset == null ? "UTF-8" : decodeCharset);
                    EntityUtils.consume(entity);
                }
            }
        }catch(Exception e){
            logger.info("通信过程中发生异常,堆栈信息如下:{}",e.fillInStackTrace());
        }finally{
            httpClient.getConnectionManager().shutdown();
        }
        return responseContent;
    }


    public static String sendGetRequest(String reqURL, String encodeCharset, String decodeCharset){
        String responseContent = null;
//        HttpClient httpClient = new DefaultHttpClient();  // 默认情况下https请求是要进行证书验证的
        HttpClient httpClient = getNewHttpClient();
        //连接超时时间
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,60000);
        //数据传输时间
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,30000);
        HttpPost httpPost = new HttpPost(reqURL);
        httpPost.setHeader(HTTP.CONTENT_TYPE, "application/json");
        try{
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            StatusLine status = response.getStatusLine();
            int statuscode=status.getStatusCode();
            if (null != entity) {
                if (statuscode== HttpStatus.SC_OK) {
                    responseContent = EntityUtils.toString(entity, decodeCharset == null ? "UTF-8" : decodeCharset);
                    EntityUtils.consume(entity);
                }
            }
        }catch(Exception e){
            logger.info("通信过程中发生异常,堆栈信息如下:{}",e.fillInStackTrace());
        }finally{
            httpClient.getConnectionManager().shutdown();
        }
        return responseContent;
    }

    public static HttpClient getNewHttpClient() {
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
            SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            HttpParams params = new BasicHttpParams();
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("http", PlainSocketFactory
                    .getSocketFactory(), 8080));
            registry.register(new Scheme("https", sf, 8443));
            ClientConnectionManager ccm = new ThreadSafeClientConnManager(
                    params, registry);
            return new DefaultHttpClient(ccm, params);
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }


    private static class MySSLSocketFactory extends SSLSocketFactory {
        SSLContext sslContext = SSLContext.getInstance("TLS");

        public MySSLSocketFactory(KeyStore truststore)
                throws NoSuchAlgorithmException, KeyManagementException,
                KeyStoreException, UnrecoverableKeyException {
            super(truststore);

            TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };

            sslContext.init(null, new TrustManager[] { tm }, null);
        }

        @Override
        public Socket createSocket(Socket socket, String host, int port, boolean autoClose)
                throws IOException, UnknownHostException {
            return sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
        }

        @Override
        public Socket createSocket() throws IOException {
            return sslContext.getSocketFactory().createSocket();
        }
    }


    public static void main(String[] args) {
        System.out.println(getMsg("news_list"));
    }

}
