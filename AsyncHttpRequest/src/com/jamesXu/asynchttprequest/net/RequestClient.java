package com.jamesXu.asynchttprequest.net;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import org.apache.http.HttpEntity;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;

import com.jamesXu.asynchttprequest.AppContext;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * 
 * 请求客户端
 * <p>其中的方法都是基于android-async-http-1.4.3.jar 中的异步网络请求实现的  <http://loopj.com/android-async-http> </p>
 * 
 * @author lwz
 *
 */
public class RequestClient {
	
	/** 
	 * 定义一个异步网络客户端
	 * 默认超时为10秒
	 * 当超时，默认重连次数为5次
	 * 默认最大连接数为10个
	 */
    private static AsyncHttpClient mClient = new AsyncHttpClient();
    
    ////////////////////
    //// 添加证书信任模块，用于 HTTPS ，信任所有证书
    static {
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
	        trustStore.load(null, null);
	        SSLSocketFactory sf = new EasySSLSocketFactory(trustStore);
			sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			mClient.setSSLSocketFactory(sf);
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    static class MyHostnameVerifier implements X509HostnameVerifier {

		@Override
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}

		@Override
		public void verify(String host, SSLSocket ssl) throws IOException {
		}

		@Override
		public void verify(String host, X509Certificate cert)
				throws SSLException {
		}

		@Override
		public void verify(String host, String[] cns, String[] subjectAlts)
				throws SSLException {
		}
    }
    
    /**
	 * post 请求
	 * @param url		API 地址
	 * @param handler	数据加载句柄对象
	 */
	public static void post(String url, AsyncHttpResponseHandler handler){
		post(url, (RequestParams)null, handler);
	}
	
	/**
	 * post 请求
	 * @param url		API 地址
	 * @param params	请求的参数
	 * @param handler	数据加载句柄对象
	 */
	public static void post(String url, RequestParams params, AsyncHttpResponseHandler handler){
		mClient.post(url, params, handler);
	}
	
	/**
	 * post 请求
	 * @param url		API 地址
	 * @param jsonStrParam  	请求的参数, 所有参数的 JSON 字符串
	 * @param handler	数据加载句柄对象
	 */
	public static void post( String url, String jsonStrParam, AsyncHttpResponseHandler handler){
		try {
			HttpEntity entity = new StringEntity(jsonStrParam, "UTF-8");
			final String CONTENT_TYPE = "application/json";
			mClient.post(AppContext.getInstance().getApplicationContext(), url, entity, CONTENT_TYPE, handler);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * get 请求
	 * @param url		API 地址
	 * @param handler	数据加载句柄对象
	 */
	public static void get(String url, AsyncHttpResponseHandler handler){
		get(url, null, handler);
	}
	
	/**
	 * get 请求
	 * @param url		API 地址
	 * @param params	请求的参数
	 * @param handler	数据加载句柄对象
	 */
	public static void get(String url, RequestParams params, AsyncHttpResponseHandler handler){
		mClient.get(url, params, handler);
	}
    
}

