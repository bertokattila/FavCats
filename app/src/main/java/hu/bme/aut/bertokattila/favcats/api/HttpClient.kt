package hu.bme.aut.bertokattila.favcats.api

import hu.bme.aut.bertokattila.favcats.models.Cat
import hu.bme.aut.bertokattila.favcats.utils.Config
import hu.bme.aut.bertokattila.favcats.utils.Config.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpClient {
    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val randomCatApi: RandomCatApi = retrofit.create(RandomCatApi::class.java)

    ///  JSON response is a list which contains a single object,  List<Cat>
    fun getCat() : Call<List<Cat>> {
        return randomCatApi.getCat(Config.API_KEY)
    }
}