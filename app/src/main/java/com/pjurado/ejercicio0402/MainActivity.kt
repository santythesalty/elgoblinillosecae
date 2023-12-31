package com.pjurado.ejercicio0402

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import com.pjurado.ejercicio0402.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    companion object{
        var FRAGMENT: Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        when(resources.configuration.orientation){
            Configuration.ORIENTATION_PORTRAIT -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, PrimerFragment())
                    .commit()
                    FRAGMENT = 0
            }
            Configuration.ORIENTATION_LANDSCAPE -> {
                when(FRAGMENT){
                    0 ->{
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, PrimerFragment())
                            .replace(R.id.detalle, BlankFragment())
                            .commit()
                    }
                    1 ->{
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, PrimerFragment())
                            .replace(R.id.detalle, DetailFragment())
                            .commit()
                    }
                }


            }
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, PrimerFragment())
            .commit()
    }

    fun cargaDetailFragment(contacto: Contacto){
        val fragment = DetailFragment()
        fragment.arguments = bundleOf(DetailFragment.CONTACTO to contacto)

        when(resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .addToBackStack(null)
                    .commit()
            }
            Configuration.ORIENTATION_LANDSCAPE -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.detalle, fragment)
                    .commit()
            }

        }
    }
}