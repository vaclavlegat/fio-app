package cz.venjudev.fio.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import cz.venjudev.fio.entity.FioStatementResponse
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

import java.util.concurrent.TimeUnit


interface FioApi {

    @GET("periods/{token}/{dateFrom}/{dateTo}/transactions.json")
    fun getTransactions(@Path("token") token: String, @Path("dateFrom") dateFrom: String, @Path("dateTo") dateTo: String): Deferred<Response<FioStatementResponse>>

    companion object {

        private const val API_ENDPOINT: String = "https://www.fio.cz/ib_api/rest/"
        private const val TIMEOUT: Long = 30

        fun create(): FioApi {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor).build()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API_ENDPOINT)
                .client(httpClient)
                .build()

            return retrofit.create(FioApi::class.java)
        }
    }
}

