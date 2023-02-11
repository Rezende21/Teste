package com.example.testempresa.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CarDao {

    @Query("SELECT * FROM Car")
    suspend fun showAllCars() : List<CarTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCarToDB(car: CarTable)

    @Query("DELETE FROM Car WHERE id =:carId")
    suspend fun deleteCarFromDB(carId: Int)
}