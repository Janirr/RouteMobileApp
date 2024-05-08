package com.example.routeapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.example.routeapp.util.Listener
import com.example.routeapp.R
import com.example.routeapp.adapters.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity(), Listener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val viewPager: ViewPager2 = findViewById(R.id.pager)
        val sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = sectionsPagerAdapter

        val tabLayout: TabLayout = findViewById(R.id.tabs)

        val tabTitles = listOf("Łatwe szlaki", "Trudne szlaki") // Lista tekstów dla zakładek

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text =
                tabTitles[position] // Ustawiamy tekst zakładki na podstawie pozycji w liście tabTitles
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_action -> {
                val intent = Intent(this, ActionActivity::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun itemClicked(id: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_TRAIL_ID, id)
        startActivity(intent)
    }
}
