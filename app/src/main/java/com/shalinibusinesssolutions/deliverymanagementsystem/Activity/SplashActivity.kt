package com.shalinibusinesssolutions.deliverymanagementsystem.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.shalinibusinesssolutions.deliverymanagementsystem.MainActivity
import com.shalinibusinesssolutions.deliverymanagementsystem.R
import com.shalinibusinesssolutions.deliverymanagementsystem.Utility.SharedPreferencesUtil
import com.shalinibusinesssolutions.deliverymanagementsystem.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding= ActivitySplashBinding.inflate(layoutInflater)

        setContentView(binding.root)

        Handler().postDelayed({


            var userName = SharedPreferencesUtil().getUserName(this).toString()
            var userId = SharedPreferencesUtil().getUserId(this).toString()

            if (userId == "")
            {

                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)


            } else {

                val intent =Intent(this,MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)


            }



        },3000)

    }


}