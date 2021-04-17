package io.github.wottrich.detail

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import io.github.wottrich.R
import io.github.wottrich.databinding.ActivityDetailBinding

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 17/04/2021
 *
 * Copyright © 2021 AndroidFaculdadeUnidade2. All rights reserved.
 *
 */

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add<DetailFragment>(R.id.fragmentContainer, args = intent.extras)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val INTENT_NAME_CLICKED = "INTENT_NAME_CLICKED"

        fun start(fragment: Fragment, resultCode: Int, clickedName: String) {
            val intent = Intent(fragment.activity, DetailActivity::class.java).apply {
                putExtra(INTENT_NAME_CLICKED, clickedName)
            }
            fragment.startActivityForResult(intent, resultCode)
        }
    }

}