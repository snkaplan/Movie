package com.movie.data.di

import com.movie.data.network.MovieApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.themoviedb.org/3/movie/"
private const val API_KEY = "28fb1f22eaf719eb9547367cb2086754"

val networkModule = Kodein.Module(name = "networkModule") {
    bind() from singleton {
        val requestInterceptor = Interceptor { chain ->
            val url = chain.request().url.newBuilder().addQueryParameter("api_key", API_KEY)
                .build()
            val request = chain.request().newBuilder().url(url).build()
            return@Interceptor chain.proceed(request)
        }
        OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).callTimeout(
                10,
                TimeUnit.SECONDS)
            addInterceptor(requestInterceptor)
        }.build()
    }

    bind() from singleton {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(instance())
            .addConverterFactory(GsonConverterFactory.create() as Converter.Factory)
            .build()
    }

    bind() from singleton {
        instance<Retrofit>().create(MovieApi::class.java)
    }
}

