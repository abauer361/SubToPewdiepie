package com.example.subtopewdiepie

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var subCounter: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intent.extras?.get("username")

        if (savedInstanceState != null) {
            updateCounter(savedInstanceState.getLong(SUB_COUNTER_KEY,  0))
        } else if (getStore().contains(SUB_COUNTER_KEY)) {
            updateCounter(getStore().getLong(SUB_COUNTER_KEY, 0))
        }

        subscribeButton.setOnClickListener {
            subCounter++
            myTextView.text = subCounter.toString()
        }

    }

    private fun updateCounter(num : Long){
        subCounter = num
        myTextView.text = subCounter.toString()
    }

    private fun getStore() = getPreferences(Context.MODE_PRIVATE)

    override fun onSaveInstanceState(outState: Bundle) {
        outState?.run {
            putLong(SUB_COUNTER_KEY, subCounter)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onPause() {
        super.onPause()
        getStore().edit().putLong(SUB_COUNTER_KEY, subCounter).apply()
    }

    companion object {
        private const val SUB_COUNTER_KEY = "subCounterKey"
    }
}
