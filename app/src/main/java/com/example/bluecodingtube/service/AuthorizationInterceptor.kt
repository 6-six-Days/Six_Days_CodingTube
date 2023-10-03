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
                        "key",
                        "AIzaSyBfaJPCzYR-ff1z4Xbx0lVGwoS6hpS2Sj8"
                    )
                    .build()
            )
            .build()
        return chain.proceed(newRequest)
    }
}






