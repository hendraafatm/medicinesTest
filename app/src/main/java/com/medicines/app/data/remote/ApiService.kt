package com.medicines.app.data.remote

import com.medicines.app.data.model.DataMedicine
import retrofit2.http.GET

interface ApiService {
    @GET("68d47cff-f909-4341-8a1d-6c313227299e")
    suspend fun getMedicines(): List<DataMedicine>
}