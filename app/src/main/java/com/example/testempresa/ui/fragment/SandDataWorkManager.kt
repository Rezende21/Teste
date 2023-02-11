package com.example.testempresa.ui.fragment

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class SandDataWorkManager(context: Context, parameters : WorkerParameters) : Worker(context, parameters) {
    override fun doWork(): Result {
        TODO("Not yet implemented")
    }
}