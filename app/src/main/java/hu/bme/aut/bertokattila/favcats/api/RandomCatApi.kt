package hu.bme.aut.bertokattila.favcats.api

import hu.bme.aut.bertokattila.favcats.models.Cat
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomCatApi {
    @GET("/v1/images/search")
     fun getCat(
        @Query("x-api-key") key : String
    ) : Call<List<Cat>>
}