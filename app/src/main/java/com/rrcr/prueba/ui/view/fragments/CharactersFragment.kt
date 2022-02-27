package com.rrcr.prueba.ui.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rrcr.prueba.data.model.MainData
import com.rrcr.prueba.databinding.FragmentCharactersBinding
import com.rrcr.prueba.ui.view.DetailActivity
import com.rrcr.prueba.ui.view.DetailActivity.Companion.CHARACTER_ID
import com.rrcr.prueba.ui.view.DetailActivity.Companion.CHARACTER_NAME
import com.rrcr.prueba.ui.view.DetailActivity.Companion.CHARACTER_URL
import com.rrcr.prueba.ui.view.adapter.character.CharacterAdapter
import com.rrcr.prueba.ui.viewmodel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CharactersFragment @Inject constructor() : Fragment() {
    private lateinit var binding: FragmentCharactersBinding

    private val viewModel: CharacterViewModel by viewModels()
    var firstVisibleItem = 0
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0
    var previousTotal: Int = 0
    private val visibleThreshold = 5
    private var loading = true
    lateinit var adapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        initView()
        return binding.root

    }


    private fun initView() {
        initAdapter()
        initObservers()
        initListeners()
        viewModel.getData()
    }

    private fun initObservers() {
        viewModel.dataModelList.observe(this, {
            adapter.notifyDataSetChanged()
        })
        viewModel.isLoading.observe(this, {
            binding.loading.isVisible = it
        })
        viewModel.characterSelected.observe(this, { character ->
            if (character != null) {
                startActivity(
                    Intent(activity, DetailActivity::class.java)
                        .putExtra(CHARACTER_ID, character.id)
                        .putExtra(CHARACTER_URL, character.urlImagen)
                        .putExtra(CHARACTER_NAME, character.nombre)
                )
                viewModel.setCharacterSelected(null)
            }
        })
    }

    private fun initAdapter() {
        binding.rvCharacters.layoutManager = LinearLayoutManager(activity)
        adapter = CharacterAdapter(viewModel.resultados, { characterSelected ->
            onItemClicked(characterSelected)
        }, { characterSelected ->
            addToFav(characterSelected)
        })
        binding.rvCharacters.adapter = adapter
    }

    private fun initListeners() {
        binding.rvCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                visibleItemCount = binding.rvCharacters.childCount
                totalItemCount = binding.rvCharacters.layoutManager!!.itemCount
                firstVisibleItem =
                    (binding.rvCharacters.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false
                        previousTotal = totalItemCount
                    }
                }
                if (!loading && totalItemCount - visibleItemCount
                    <= firstVisibleItem + visibleThreshold
                ) {
                    viewModel.getData()
                    loading = true
                }
            }
        })
    }

    fun onItemClicked(characterModel: MainData) {
        viewModel.setCharacterSelected(characterModel)
    }

    fun addToFav(characterModel: MainData) {
        Log.d("*****Fragment", "Añadiendo a fav $characterModel")
        viewModel.addToFav(characterModel)
        Toast.makeText(activity, "Añadido a Favoritos", Toast.LENGTH_SHORT).show()
    }


}