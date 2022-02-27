package com.rrcr.prueba.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.rrcr.prueba.R
import com.rrcr.prueba.databinding.ActivityButtomNavBinding
import com.rrcr.prueba.databinding.ActivityDetailBinding
import com.rrcr.prueba.utils.load
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding
    lateinit var idCharacter: String
    lateinit var nameCharacter: String
    lateinit var imageCharacter: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        idCharacter = intent.getStringExtra(CHARACTER_ID).toString()
        imageCharacter = intent.getStringExtra(CHARACTER_URL).toString()
        nameCharacter = intent.getStringExtra(CHARACTER_NAME).toString()
        binding.nameCharacter.text = nameCharacter
        binding.imageCharacter.load(imageCharacter)
        setListeners()
    }

    private fun setListeners() {
        binding.comicsBtn.setOnClickListener { startGenericDetail(MODE_COMICS) }
        binding.eventsBtn.setOnClickListener { startGenericDetail(MODE_EVENTS) }
        binding.seriesBtn.setOnClickListener { startGenericDetail(MODE_SERIES) }
        binding.storiesBtn.setOnClickListener { startGenericDetail(MODE_STORIES) }
    }

    fun startGenericDetail(mode: Int) {

        startActivity(
            Intent(applicationContext, GenericDetailActivity::class.java).putExtra(
                CHARACTER_ID, idCharacter
            )
                .putExtra(MODE, mode)
        )
    }

    companion object {
        const val MODE_SERIES = 0
        const val MODE_COMICS = 1
        const val MODE_STORIES = 2
        const val MODE_EVENTS = 3

        const val CHARACTER_ID = "CHARACTER_ID"
        const val CHARACTER_URL = "CHARACTER_URL"
        const val CHARACTER_NAME = "CHARACTER_NAME"
        const val MODE = "MODE"


    }
}