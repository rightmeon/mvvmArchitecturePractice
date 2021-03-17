package com.example.mvvmpj.presentation.di.module

import androidx.room.Room
import com.example.mvvmpj.App
import com.example.mvvmpj.data.local.AppDatabase
import com.example.mvvmpj.data.local.BookMarkDao
import com.example.mvvmpj.data.remote.NaverBookAPI
import com.example.mvvmpj.domain.BookRepositoryImpl
import com.example.mvvmpj.presentation.di.scope.ActivityScope
import com.example.mvvmpj.presentation.view.activity.MainActivity
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
private const val NAVER_CLIENT_KEY = "4dyaHy8sKQkxTpOAwuXE"
private const val NAVER_CLIENT_SECRET_KEY = "6GbH06xIzb"
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
                DB_NAME
            )
                .build()
                .bookMarkDao()

        @Singleton
        @Provides
        fun provideRepository(api : NaverBookAPI, dao: BookMarkDao) = BookRepositoryImpl(api, dao)
    }

    @ActivityScope
    @ContributesAndroidInjector(modules = [ActivityModule::class])
    abstract fun mainActivity(): MainActivity
}
