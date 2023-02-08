package com.example.testempresa.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
@ExperimentalCoroutinesApi
class CarDaoTest {

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: CarDatabase
    private lateinit var dao : CarDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CarDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        dao = database.getInstance()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertCarToDataBase() = runBlocking {
        val car = CarTable(1, "FORD", "Maverick", 1974, "alcool",2, 170.0, "Azul", "usuario", "11999999999", "usuario@usuario.com")
        dao.insertCarToDB(car)

        val showAllCars = dao.showAllCars().getOrAwaitValue()
        assertThat(showAllCars).contains(car)
    }

    @Test
    fun deleteCarFromTable() = runBlocking {
        val car = CarTable(1, "FORD", "Maverick", 1974, "alcool",2, 170.0, "Azul", "usuario", "11999999999", "usuario@usuario.com")
        dao.insertCarToDB(car)
        dao.deleteCarFromDB(car.id)

        val showAllCars = dao.showAllCars().getOrAwaitValue()
        assertThat(showAllCars).doesNotContain(car)
    }
}