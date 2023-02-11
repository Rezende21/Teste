package com.example.testempresa.data.remote

import com.google.gson.annotations.SerializedName

data class Cars(
    @SerializedName("id")
    val id : Int,

    @SerializedName("marca_id")
    val marcaId : Int,

    @SerializedName("marca_nome")
    val marcaNome : String,

    @SerializedName("nome_modelo")
    val nomeModelo : String,

    @SerializedName("ano")
    val ano : Int,

    @SerializedName("combustivel")
    val combustivel : String,

    @SerializedName("num_portas")
    val numPortas : Int,

    @SerializedName("valor_fipe")
    val valorFipe : Double,

    @SerializedName("cor")
    val cor : String
)
