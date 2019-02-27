package com.swarn.weather.api

import com.swarn.weather.util.LiveDataCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * @author Swarn Singh.
 */
object APIClient {

    private const val BASE_URL = "https://s3.eu-west-2.amazonaws.com/interview-question-data/metoffice/"

    private var adapterBuilder: retrofit2.Retrofit.Builder? = null

    private fun createDefaultAdapter() {
        adapterBuilder = retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
    }

    fun <S> createService(serviceClass: Class<S>): S {
        if (adapterBuilder == null) {
            createDefaultAdapter()
        }
        return this.adapterBuilder!!.build().create(serviceClass)
    }
}