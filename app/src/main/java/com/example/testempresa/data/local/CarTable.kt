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
    val name_fab : String,

    @ColumnInfo("name_car")
    val name_car : String,

    @ColumnInfo("car_year")
    val car_year : Int,

    @ColumnInfo("car_gas")
    val car_gas : String,

    @ColumnInfo("num_port")
    val num_port : Int,

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