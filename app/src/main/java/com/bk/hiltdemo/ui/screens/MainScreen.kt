package com.bk.hiltdemo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.bk.hiltdemo.hiltwithretrofit.DogViewModel

@Composable
fun MainScreen(){
    val viewModel: DogViewModel = hiltViewModel()
//    val dogData = viewModel.data.observeAsState()
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Main Screen")
//        Text(text = dogData.value?.message.toString())
//        Text(text = dogData.value?.status.toString())
    }
}