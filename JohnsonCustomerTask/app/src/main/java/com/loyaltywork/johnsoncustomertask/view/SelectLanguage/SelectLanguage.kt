package com.loyaltywork.johnsoncustomertask.view.SelectLanguage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.loyaltywork.johnsoncustomertask.R
import com.loyaltywork.johnsoncustomertask.databinding.ActivitySelectLanguageBinding
import com.loyaltywork.johnsoncustomertask.view.AccountTypeSelection.AccountTypeSelection

class SelectLanguage : AppCompatActivity() , View.OnClickListener {

    private lateinit var binding: ActivitySelectLanguageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.cardviewEnglish.setOnClickListener(this)
        binding.cardviewHindi.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view!!.id){

            R.id.cardviewEnglish ->{
                startActivity(Intent(this,AccountTypeSelection::class.java))
            }

            R.id.cardviewHindi ->{
                startActivity(Intent(this,AccountTypeSelection::class.java))
            }
        }
    }
}