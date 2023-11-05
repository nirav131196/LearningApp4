package com.loyaltywork.johnsoncustomertask.view.SplashScreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.loyaltywork.johnsoncustomertask.BuildConfig
import com.loyaltywork.johnsoncustomertask.R
import com.loyaltywork.johnsoncustomertask.baseclass.BaseActivity
import com.loyaltywork.johnsoncustomertask.utils.PreferenceHelper
import com.loyaltywork.johnsoncustomertask.view.Dashboard.Dashboard
import com.loyaltywork.johnsoncustomertask.view.SelectLanguage.SelectLanguage

class SplashScreen : BaseActivity() {

    override fun callInitialServices() {

    }

    override fun callObservers() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        Handler(Looper.getMainLooper()).postDelayed(Runnable {

            when{
                PreferenceHelper.getBooleanValue(this,BuildConfig.IsLoggedIn) ->{

                    val intent = Intent(this, Dashboard::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                else ->{
                    startActivity(Intent(this,SelectLanguage::class.java))
                }
            }
            finish()
        },2000)

    }
}