package com.rrcr.prueba.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.rrcr.prueba.R
import com.rrcr.prueba.databinding.ActivityButtomNavBinding
import com.rrcr.prueba.ui.view.fragments.CharactersFragment
import com.rrcr.prueba.ui.view.fragments.FavFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ButtomNavActivity : AppCompatActivity() {
    private lateinit var binding: ActivityButtomNavBinding

    @Inject
    lateinit var favFragment: FavFragment
    @Inject
    lateinit var charactersFragment: CharactersFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityButtomNavBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        setListeners()
        loadFragment(charactersFragment)
    }

    private fun setListeners() {
        binding.bottomNavigation.setOnItemSelectedListener { menuItem->
            when(menuItem.itemId){
                R.id.charactersItem -> {
                    loadFragment(charactersFragment)
                    true
                }
//                R.id.favItem -> {
//                    loadFragment(favFragment)
//                    true
//                }
                else -> false
            }

        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.placeHolder.id, fragment)
        transaction.commit()

    }
}