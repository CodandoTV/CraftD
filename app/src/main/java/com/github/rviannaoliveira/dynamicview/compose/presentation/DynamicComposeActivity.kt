package com.github.rviannaoliveira.dynamicview.compose.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class DynamicComposeActivity : AppCompatActivity() {
    private val vm: DynamicComposeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                InitialScreen(vm = vm)
            }
        }
    }
}
