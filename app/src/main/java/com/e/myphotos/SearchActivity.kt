package com.e.myphotos

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.e.myphotos.databinding.ActivitySearchBinding
import com.e.myphotos.ui.ResultActivity

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    var word: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        binding.searchButton.setOnClickListener {
            addSearch(it)
        }

    }

    private fun addSearch(view: View) {

        if (binding.searchBox.getText().toString().isNotEmpty()) {

            word = binding.searchBox.text.toString()
            val intent = Intent(this, ResultActivity::class.java).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, word)
            }
            startActivity(intent)
        } else {

            Toast.makeText(
                this,
                getString(R.string.toast),
                Toast.LENGTH_LONG
            ).show()
        }

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}