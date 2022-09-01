package com.example.voicenotes.android

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.voicenotes.usecase.GetNotes
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val scope = MainScope()

    private val getNotes: GetNotes by inject()

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = "Loading..."

        scope.launch {
            kotlin.runCatching {
                getNotes()
            }.onSuccess {
                tv.text = it.first().title
            }.onFailure {
                tv.text = it.localizedMessage
            }
        }
    }
}
