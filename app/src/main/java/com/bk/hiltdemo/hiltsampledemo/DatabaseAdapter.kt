package com.bk.hiltdemo.hiltsampledemo

import android.util.Log
import javax.inject.Inject

class DatabaseAdapter @Inject constructor(private var databaseService: DatabaseService) {
    fun log(msg: String){
        Log.d("DatabaseAdapter","DatabaseAdapter : $msg")
        databaseService.log(msg)
    }
}