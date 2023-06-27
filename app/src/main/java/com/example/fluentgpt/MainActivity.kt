package com.example.fluentgpt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fluentgpt.adapter.ItemAdapter
import com.example.fluentgpt.data.Datasource
import com.example.fluentgpt.databinding.ActivityMainBinding
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val firstFragment=Conversations()
        val secondFragment=Routine()
        val thirdFragment=Settings()

        setCurrentFragment(firstFragment)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.conversations->setCurrentFragment(firstFragment)
                R.id.timeline->setCurrentFragment(secondFragment)
                R.id.settings->setCurrentFragment(thirdFragment)

            }
            true
        }

    }
    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }
}