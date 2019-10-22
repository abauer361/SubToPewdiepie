package com.example.subtopewdiepie

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var countViewModel: CountViewModel

    private var subCounter: Long = 0
    private fun getUsername() = intent.extras?.get("username").toString().toLowerCase(Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countViewModel = ViewModelProviders.of(this).get(CountViewModel::class.java)
        countViewModel.getUserCount(getUsername()).observe(this,
            androidx.lifecycle.Observer { updateCounter(it) })

        subscribeButton.setOnClickListener {
            countViewModel.setUserCount(getUsername(), subCounter + 1)
        }

    }

    private fun updateCounter(num: Long) {
        subCounter = num
        myTextView.text = subCounter.toString()
    }
}


/*
    private fun getStore() = getPreferences(Context.MODE_PRIVATE)

    override fun onSaveInstanceState(outState: Bundle) {
        outState?.run {
            putLong(user, subCounter)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onPause() {
        super.onPause()
        getStore().edit().putLong(user, subCounter).apply()
    }

 companion object {
        private const val SUB_COUNTER_KEY = "subCounterKey"
    }*/
