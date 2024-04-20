package com.github.rviannaoliveira.dynamicview.xml.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.codandotv.craftd.xml.ui.CraftDViewAdapter
import com.github.rviannaoliveira.dynamicview.app.databinding.ActivityDynamicComponentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CraftDComponentActivity : AppCompatActivity() {
    lateinit var binding: ActivityDynamicComponentBinding
    private val vm: DynamicViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDynamicComponentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.adapter = vm.dynamic as com.github.codandotv.craftd.xml.ui.CraftDViewAdapter

        vm.deeplink.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }

        vm.analytics.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }

    override fun onResume() {
        super.onResume()
        vm.loadDynamic()
    }
}