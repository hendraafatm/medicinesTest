package com.medicines.app.di

import android.content.Context
import androidx.room.Room
import com.medicines.app.data.local.AppDatabase
import com.medicines.app.data.local.MedicineDao
import com.medicines.app.data.remote.ApiService
import com.medicines.app.data.repository.MedicineRepositoryImpl
import com.medicines.app.domain.mapper.MedicineMapper
import com.medicines.app.domain.repository.MedicineRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext app: Context): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "app_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideMedicineDao(db: AppDatabase): MedicineDao {
        return db.medicineDao()
    }

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMedicineMapper(): MedicineMapper {
        return MedicineMapper()
    }

    @Provides
    @Singleton
    fun provideMedicineRepository(
        medicineDao: MedicineDao,
        apiService: ApiService,
        mapper: MedicineMapper
    ): MedicineRepository {
        return MedicineRepositoryImpl(medicineDao, apiService, mapper)
    }
}
