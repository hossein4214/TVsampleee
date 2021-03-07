package com.mohsen.tvsample

import android.app.Application
import com.mohsen.tvsample.data.remote.api.ApiInterface
import com.videostreamshows.data.remote.Client
import com.videostreamshows.data.remote.CreateApiInterface
import com.mohsen.tvsample.data.remote.RetrofitBuilder
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

class TvSampleApplication
    :Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@TvSampleApplication))

        // network
        bind<OkHttpClient>() with singleton { Client().invoke() }
        bind<Retrofit>() with singleton { RetrofitBuilder(instance()).invoke() }
        bind<ApiInterface>() with singleton { CreateApiInterface(instance()).invoke() }

    }

}