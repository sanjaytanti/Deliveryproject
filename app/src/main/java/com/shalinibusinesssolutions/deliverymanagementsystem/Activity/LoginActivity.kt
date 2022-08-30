package com.shalinibusinesssolutions.deliverymanagementsystem.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shalinibusinesssolutions.deliverymanagementsystem.MainActivity
import com.shalinibusinesssolutions.deliverymanagementsystem.R
import com.shalinibusinesssolutions.deliverymanagementsystem.Utility.Resource
import com.shalinibusinesssolutions.deliverymanagementsystem.Utility.SharedPreferencesUtil
import com.shalinibusinesssolutions.deliverymanagementsystem.api.RetrofitClient
import com.shalinibusinesssolutions.deliverymanagementsystem.databinding.ActivityLoginBinding
import com.shalinibusinesssolutions.deliverymanagementsystem.repository.AppRepository
import com.shalinibusinesssolutions.deliverymanagementsystem.viewmodel.LoginUserViewModel
import com.shalinibusinesssolutions.deliverymanagementsystem.viewmodel.ViewModelFactory

class LoginActivity : AppCompatActivity() ,View.OnClickListener{
    lateinit var binding:ActivityLoginBinding
    private lateinit var loginUserViewModel: LoginUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        binding.btnLogin.setOnClickListener(this)
        initViewModel()
        setContentView(binding.root)


    }

    private fun initViewModel() {
        val appRepository = AppRepository(RetrofitClient.apiservice)

         loginUserViewModel=ViewModelProvider(this, ViewModelFactory(appRepository,this))[LoginUserViewModel::class.java]


    }


    override fun onClick(p0: View?) {
        when(p0?.id)
        {
            R.id.btnLogin->
            {
                loginUser(
                    binding.etLoginEmail.text.toString().trim(),
                    binding.etLoginPass.text.toString().trim()
                )

            }
        }

    }

    private fun loginUser(email: String, password: String) {
        if (TextUtils.isEmpty(email)) {
            binding.etLoginEmail.setError("Email Cannot be empty ")
            binding.etLoginEmail.requestFocus()

        } else if (TextUtils.isEmpty(password)) {
            binding.etLoginPass.setError("Email Cannot be empty ")
            binding.etLoginPass.requestFocus()

        } else {

            loginUserViewModel.loginUser(email,password)
            loginUserViewModel.response.observe(this, Observer {
                it.getContentIfNotHandled().let  {resource->
                    when(resource)
                    {
                      is Resource.Success->
                      {
                            resource.data.let { userloginResponse->
                                if(userloginResponse?.status=="true")
                                {

                                    var userId = userloginResponse!!.data.Id
                                    var userName = userloginResponse!!.data.UserName
                                    SharedPreferencesUtil().setUserId(
                                        userId.toString(),
                                        this
                                    )
                                    SharedPreferencesUtil().setUserName(
                                        userName.toString(),
                                        this
                                    )
                                    startActivity(Intent(this, MainActivity::class.java))
                                    binding.progressbar.visibility = View.GONE


                                }
                                else {

                                    Toast.makeText(
                                        this,
                                        "" + userloginResponse?.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    binding.progressbar.visibility = View.GONE
                                }
                            }

                      }
                        is Resource.Error->
                        {
                            Toast.makeText(
                                this,
                                " " + resource.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()

                            binding.progressbar.visibility = View.GONE

                        }
                        is Resource.Loading->
                        {
                            binding.progressbar.visibility = View.VISIBLE
                        }
                        else -> {}
                    }
                }
            })
        }
    }

}