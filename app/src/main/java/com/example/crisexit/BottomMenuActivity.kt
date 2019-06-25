package com.example.crisexit


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.fragment.app.Fragment


class BottomMenuActivity : AppCompatActivity() {

    private var content: FrameLayout? = null
    private lateinit var token:String

    private var bundle = Bundle()


    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_guia-> {
                val fragment = GuiaFragment.newInstance()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_extrato -> {
                val fragment = ExtratoFragment()
                fragment.arguments = bundle
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                val fragment = ProfileFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)
            .replace(R.id.content, fragment, fragment.javaClass.simpleName)
            .addToBackStack(fragment.javaClass.simpleName)
            .commit()

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_menu)
        this.content = findViewById(R.id.content)

        val intent = getIntent()
        val token = intent.getStringExtra("TOKEN")
        bundle.putString("TOKEN", token)


        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val fragment = GuiaFragment.newInstance()
        addFragment(fragment)
    }



}
