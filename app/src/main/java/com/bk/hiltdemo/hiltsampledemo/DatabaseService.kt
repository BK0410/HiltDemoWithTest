package com.bk.hiltdemo.hiltsampledemo

import android.util.Log
import javax.inject.Inject

class DatabaseService @Inject constructor() {
    fun log(msg: String){
        Log.d("DatabaseService","DatabaseService msg : $msg")
    }
}