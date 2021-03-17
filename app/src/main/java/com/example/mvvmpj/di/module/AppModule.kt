package com.example.mvvmpj.di.module

import androidx.room.Room
import com.example.mvvmpj.App
import com.example.mvvmpj.api.NaverBookAPI
import com.example.mvvmpj.di.ActivityScope
import com.example.mvvmpj.repository.AppDatabase
import com.example.mvvmpj.repository.BookMarkDao
import com.example.mvvmpj.view.MainActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val NAVER_CLIENT_ID = "X-Naver-Client-Id"
private const val NAVER_CLIENT_SECRET = "X-Naver-Client-Secret"
private const val NAVER_CLIENT_KEY = "your key"
private const val NAVER_CLIENT_SECRET_KEY = "your key"
private const val NAVER_OPEN_API_URL = "https://openapi.naver.com/"
private const val DB_NAME = "bookMarkDatabase"

@Module
abstract class AppModule {

    companion object {
        @Singleton
        @Provides
        fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(500, TimeUnit.MILLISECONDS)
            .addInterceptor(Interceptor {
                it.proceed(
                    it.request()
                        .newBuilder()
                        .addHeader(NAVER_CLIENT_ID, NAVER_CLIENT_KEY)
                        .addHeader(NAVER_CLIENT_SECRET, NAVER_CLIENT_SECRET_KEY)
                        .build()
                )
            }).build()

        @Singleton
        @Provides
        fun provideNaverApi(client: OkHttpClient): NaverBookAPI =
            Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create() as Converter.Factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(NAVER_OPEN_API_URL)
                .build()
                .create(NaverBookAPI::class.java)

        @Singleton
        @Provides
        fun provideAppDatabase(app : App) : BookMarkDao =
            Room.databaseBuilder(
                app.applicationContext,
                AppDatabase::class.java,
                DB_NAME)
                .build()
                .bookMarkDao()
    }

    @ActivityScope
    @ContributesAndroidInjector(modules = [ActivityModule::class])
    abstract fun mainActivity(): MainActivity
}
