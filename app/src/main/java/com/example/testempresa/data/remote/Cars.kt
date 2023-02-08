package com.example.testempresa.data.remote

import com.google.gson.annotations.SerializedName

data class Cars(
    @SerializedName("id")
    val id : Int,

    @SerializedName("marca_id")
    val marca_id : Int,

    @SerializedName("marca_nome")
    val marca_nome : String,

    @SerializedName("nome_modelo")
    val nome_modelo : String,

    @SerializedName("ano")
    val ano : Int,

    @SerializedName("combustivel")
    val combustivel : String,

    @SerializedName("num_portas")
    val num_portas : Int,

    @SerializedName("valor_fipe")
    val valor_fipe : Double,

    @SerializedName("cor")
    val cor : String
)
