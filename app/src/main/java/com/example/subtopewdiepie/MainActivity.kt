package com.example.subtopewdiepie

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var subCounter: Long = 0
    private var user = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        user = intent.extras?.get("username").toString()

        if (savedInstanceState != null) {
            updateCounter(savedInstanceState.getLong(user,  0))
        } else if (getStore().contains(user)) {
            updateCounter(getStore().getLong(user, 0))
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
            putLong(user, subCounter)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onPause() {
        super.onPause()
        getStore().edit().putLong(user, subCounter).apply()
    }

/*    companion object {
        private const val SUB_COUNTER_KEY = "subCounterKey"
    }*/
}
