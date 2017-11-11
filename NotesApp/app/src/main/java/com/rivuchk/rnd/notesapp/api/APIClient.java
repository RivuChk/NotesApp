package com.rivuchk.rnd.notesapp.api;

import com.rivuchk.rnd.notesapp.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Rivu on 11-11-2017.
 */

public class APIClient {
    private static Retrofit retrofit = null;
    public static int LOG_NOT_NEEDED = 0;
    public static int LOG_REQ_RES = 1;
    public static int LOG_REQ_RES_BODY_HEADERS = 2;
    public static int LOG_REQ_RES_HEADERS_ONLY = 3;

    public static Retrofit getClient(int logLevel) {

        if(null == retrofit) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            if (logLevel == LOG_NOT_NEEDED)
                interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
            else if (logLevel == LOG_REQ_RES)
                interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            else if (logLevel == LOG_REQ_RES_BODY_HEADERS)
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            else if (logLevel == LOG_REQ_RES_HEADERS_ONLY)
                interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            else
                throw new IllegalStateException("Log level has to be among 0/1/2/3");

            OkHttpClient client = new OkHttpClient.Builder().connectTimeout(3, TimeUnit.MINUTES)
                    .writeTimeout(3, TimeUnit.MINUTES)
                    .readTimeout(3, TimeUnit.MINUTES).addInterceptor(interceptor).build();



            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();
        }

        return retrofit;
    }

    public static APIService getAPIService(int logLevel) {
        return getClient(logLevel).create(APIService.class);
    }

}
