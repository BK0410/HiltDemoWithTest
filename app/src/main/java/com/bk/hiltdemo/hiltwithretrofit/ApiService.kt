package com.bk.hiltdemo.hiltwithretrofit

import com.bk.hiltdemo.hiltwithretrofit.Constants.END_POINT
import retrofit2.http.GET

interface ApiService {
    @GET(END_POINT)
    suspend fun fetchData(): ApiResponse
}