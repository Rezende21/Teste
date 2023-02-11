package com.example.testempresa.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Car")
data class CarTable(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    val id : Int,

    @ColumnInfo("name_fab")
    val nameFab : String,

    @ColumnInfo("name_car")
    val nameCar : String,

    @ColumnInfo("car_year")
    val carYear : Int,

    @ColumnInfo("car_gas")
    val carGas : String,

    @ColumnInfo("num_port")
    val numPort : Int,

    @ColumnInfo("price")
    val price : Double,

    @ColumnInfo("color")
    val color : String,

    @ColumnInfo("name")
    val name : String? = null,

    @ColumnInfo("phone")
    val phone : String? = null,

    @ColumnInfo("Email")
    val email : String? = null

) : Serializable