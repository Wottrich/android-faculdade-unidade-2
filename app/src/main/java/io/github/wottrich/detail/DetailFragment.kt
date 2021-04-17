package io.github.wottrich.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.wottrich.databinding.FragmentDetailBinding
import io.github.wottrich.main.MainFragment

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 17/04/2021
 *
 * Copyright © 2021 AndroidFaculdadeUnidade2. All rights reserved.
 *
 */

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadScreen()
        setupListener()
    }

    private fun loadScreen() {
        binding.textViewName.text =
            arguments?.getString(DetailActivity.INTENT_NAME_CLICKED, "Nome não infomado!")
    }

    private fun setupListener() {
        binding.buttonDelete.setOnClickListener {
            activity?.setResult(MainFragment.RESULT_CODE_DELETE)
            activity?.finish()
        }
    }

}