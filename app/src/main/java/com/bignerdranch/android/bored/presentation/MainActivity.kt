package com.bignerdranch.android.bored.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.bored.R
import com.bignerdranch.android.bored.databinding.ActivityMainBinding
import data.repository.BoredIdeaRepositoryImpl
import domain.usecase.GetBoredIdeaUsecase
import util.Resource


class MainActivity : AppCompatActivity() {
    private val boredIdeaRepositoryImpl = BoredIdeaRepositoryImpl()
    private val getBoredIdeaUsecase = GetBoredIdeaUsecase(boredIdeaRepositoryImpl)
    private val viewModelProviderFactory = MainViewModelProviderFactory(getBoredIdeaUsecase)

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val vm = ViewModelProvider(this, viewModelProviderFactory).get(MainViewModel::class.java)
        val view = binding.root
        setContentView(view)
        vm.boredIdea.observe(this, Observer {response ->
        when(response) {
            is Resource.Success ->{
                hideProgressBar()
                response.data?.let {
                    boredIdeaResponse -> binding.ideaTV.text = boredIdeaResponse.activity
                    binding.typeTV.text = boredIdeaResponse.type.replaceFirst(boredIdeaResponse.type.first(), boredIdeaResponse.type.first().toUpperCase(), false)
                    binding.priceTV.text = "Price: ${boredIdeaResponse.price}"
                    binding.participantsTV.setText("Homies: ${boredIdeaResponse.participants.toString()}")
                }
            }
            is Resource.Error -> {
                hideProgressBar()
                response.message?.let {
                        message ->
                    Toast.makeText(this, "An error occured: $message", Toast.LENGTH_SHORT).show()
                }
            }
            is Resource.Loading -> {
                showProgressBar()
            }


        }

        })
        binding.button.setOnClickListener {
            vm.getBoredIdea()
        }
    }
    private fun hideProgressBar(){
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar(){
        binding.progressBar.visibility = View.VISIBLE
    }
}


