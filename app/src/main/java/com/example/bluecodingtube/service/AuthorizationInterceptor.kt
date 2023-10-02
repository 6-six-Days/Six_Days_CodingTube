package com.example.bluecodingtube.service

import com.example.bluecodingtube.R
import com.example.bluecodingtube.service.api.SixDays
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .url(
                chain.request().url.newBuilder()
                    .addQueryParameter(
                        "AIzaSyDWsci-1uJ0aBaJXP-Y_rFowIlcUNhMHxI",
                        SixDays.getApp().getString(R.string.YouTube_API_Key)
                    )
                    .build()
            )
            .build()
        return chain.proceed(newRequest)
    }
}






