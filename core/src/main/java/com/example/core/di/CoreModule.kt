package com.example.core.di


import androidx.room.Room
import com.example.core.data.source.CryptoRepository
import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.local.room.CryptoDatabase
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.websocket.CryptoWebsocketService
import com.example.core.domain.repository.ICryptoRepository
import com.example.core.utils.Socket
import com.example.core.utils.WebsocketMoshiAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created on : 28/04/21 | 08.48
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

val databaseModule = module {
    factory {
        get<CryptoDatabase>().cryptoDao()
    }
    single {
        val pass : ByteArray = net.sqlcipher.database.SQLiteDatabase.getBytes("ministockbit".toCharArray())
        val factory = SupportFactory(pass)
        Room.databaseBuilder(
            androidContext(),
            CryptoDatabase::class.java,
            "Crypto.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {createOkHttpClient()}
    factory { createRetrofit(okHttpClient = get())}

}

private fun createRetrofit(okHttpClient: OkHttpClient) : ApiService{
        return  Retrofit.Builder()
            .baseUrl("https://min-api.cryptocompare.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
}

private fun createOkHttpClient() : OkHttpClient {
//    val hostName = "min-api.cryptocompare.com"
//    val certificatePinner = CertificatePinner.Builder()
//        .add(hostName, "sha256/Xd+EsIDqyqDn1x3n0JYVVar+W73w1U0GxC8uZa6/QTk=")
//        .add(hostName, "sha256/8Rw90Ej3Ttt8RRkrg+WYDS9n7IS03bk5bjP/UXPtaY8=")
//        .add(hostName, "sha256/Ko8tivDrEjiY90yGasP6ZpBU4jwXvHqVvQI0GS3GNdA=")
//        .build()
    return OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .build()
}



    val repositoryModule = module {
        single { createMoshi() }
        single { Socket(client = get()) }
        single { LocalDataSource(get()) }
        single { RemoteDataSource(get()) }
        single { CryptoWebsocketService(socket = get(), moshi = get()) }
        single<ICryptoRepository> { CryptoRepository(
            localDataSource = get(),
            remoteDataSource = get(),
            cryptoWebsocketService = get()
        )}
    }

    private fun createMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
}



