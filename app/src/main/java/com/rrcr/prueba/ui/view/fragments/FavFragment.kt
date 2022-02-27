package com.rrcr.prueba.ui.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rrcr.prueba.R
import com.rrcr.prueba.data.model.CharacterData
import com.rrcr.prueba.data.model.MainData
import com.rrcr.prueba.databinding.FragmentCharactersBinding
import com.rrcr.prueba.databinding.FragmentFavBinding
import com.rrcr.prueba.ui.view.DetailActivity
import com.rrcr.prueba.ui.view.adapter.character.CharacterAdapter
import com.rrcr.prueba.ui.view.adapter.fav.FavAdapter
import com.rrcr.prueba.ui.viewmodel.CharacterViewModel
import com.rrcr.prueba.ui.viewmodel.FavViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class FavFragment @Inject constructor(): Fragment() {

    private lateinit var binding: FragmentFavBinding
    private val viewModel: FavViewModel by viewModels()
    lateinit var adapter: FavAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavBinding.inflate(inflater, container, false)
        initView()
        return binding.root

    }

    private fun initView() {
        initObservers()
        initAdapter()
        viewModel.getData()
    }


    private fun initAdapter() {
        binding.rvCharacters.layoutManager = LinearLayoutManager(activity)
        adapter = FavAdapter(viewModel.resultados) { characterSelected ->
            onItemClicked(characterSelected)
        }
        binding.rvCharacters.adapter = adapter
    }

    private fun initObservers() {
        viewModel.isLoading.observe(this, {
            binding.loading.isVisible = it
        })
        viewModel.characterSelected.observe(this, { character ->
            if (character != null) {
                val urlImagen = character.datosImagen.url + "." + character.datosImagen.extension
                startActivity(
                    Intent(activity, DetailActivity::class.java)
                        .putExtra(DetailActivity.CHARACTER_ID, character.idCharacter)
                        .putExtra(DetailActivity.CHARACTER_URL, urlImagen)
                        .putExtra(DetailActivity.CHARACTER_NAME, character.nombre)
                )

                viewModel.setCharacterSelected(null)
            }
        })

        viewModel.noData.observe(this, {
            if(it){
                binding.noDataTxt.visibility = View.VISIBLE
                binding.rvCharacters.visibility = View.GONE
            }else{
                binding.noDataTxt.visibility = View.GONE
                binding.rvCharacters.visibility = View.VISIBLE
            }

        })

        viewModel.dataModelList.observe(this, {data ->
            if(data.isNotEmpty()){
                adapter = FavAdapter(data) { characterSelected ->
                    onItemClicked(characterSelected)
                }
                binding.rvCharacters.adapter = adapter

            }
            //viewModel.getData()

        })
    }
    fun onItemClicked(characterModel: CharacterData) {
        viewModel.setCharacterSelected(characterModel)
    }

}