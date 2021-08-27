package com.mayisheng.wanandroid.Utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpUtil {
    private static volatile OkHttpUtil mOkHttpUtil;
    private OkHttpClient.Builder mBuilder;
    private Request.Builder mRequestBuilder;
    private OkHttpClient mOkHttpClient;

    private OkHttpUtil() {
        mBuilder = new OkHttpClient.Builder();
        mOkHttpClient = mBuilder.addInterceptor(new RequestLoggerInterceptor())
                .addInterceptor(new ResponseLoggerInterceptor())
                .connectTimeout(3, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(3, TimeUnit.SECONDS)//设置读取超时时间
                .build();
        mRequestBuilder = new Request.Builder();//省的每次都new  request操作,直接builder出来,随后需要什么往里加,build出来即可
    }

    public static OkHttpUtil getInstance() {
        if (null == mOkHttpUtil) {
            synchronized (OkHttpUtil.class) {
                if (null == mOkHttpUtil) {
                    mOkHttpUtil = new OkHttpUtil();
                }
            }
        }
        return mOkHttpUtil;
    }

    public void httpGet(String url, final ICallback callback) {
        Request request = mRequestBuilder.url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.invoke("数据错误");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.invoke(response.body().string());
            }
        });
    }

    public void httpPost(String urlString, String json, Callback callback) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = mRequestBuilder.url(urlString).method("POST", requestBody).build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }

    public Response httpPostSyn(String urlString, String json) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = mRequestBuilder.url(urlString).method("POST", requestBody).build();
        Response response;
        try {
            response = mOkHttpClient.newCall(request).execute();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 接口用于回调数据
     */
    public interface ICallback {
        void invoke(String string);
    }

    /**
     * 请求拦截器
     */
    static class RequestLoggerInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            return chain.proceed(request);
        }
    }

    /**
     * 响应拦截器
     */
    static class ResponseLoggerInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response response = chain.proceed(chain.request());
            if (response.body() != null && response.body().contentType() != null) {
                MediaType mediaType = response.body().contentType();
                String string = response.body().string();

                ResponseBody responseBody = ResponseBody.create(mediaType, string);
                return response.newBuilder().body(responseBody).build();
            } else {
                return response;
            }
        }
    }
}
