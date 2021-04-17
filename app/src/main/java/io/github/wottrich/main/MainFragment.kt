package io.github.wottrich.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import io.github.wottrich.NamesAdapter
import io.github.wottrich.databinding.FragmentMainBinding
import io.github.wottrich.detail.DetailActivity

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 17/04/2021
 *
 * Copyright © 2021 AndroidFaculdadeUnidade2. All rights reserved.
 *
 */
 
class MainFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: FragmentMainBinding

    private val adapter: NamesAdapter by lazy {
        NamesAdapter(::onAdapterClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupListeners()
        setupObservers()
        registerForActivityResult(ActivityResultContracts.GetContent()) {

        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            adapter = this@MainFragment.adapter
        }
    }

    private fun setupListeners() {
        binding.apply {
            buttonSend.setOnClickListener {
                viewModel.sendName(inputName.text.toString())
            }
        }
    }

    private fun setupObservers() {
        viewModel.listItems.observe(viewLifecycleOwner) {
            adapter.updateItems(it)
        }
    }

    private fun onAdapterClick(clickedName: String) {
        viewModel.updateLastClickedName(clickedName)
        DetailActivity.start(fragment = this, REQUEST_CODE_DETAIL_ACTIVITY, clickedName)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_DETAIL_ACTIVITY) {
            if (resultCode == RESULT_CODE_DELETE) {
                viewModel.removeLastClickedName()
            }
        }
    }

    companion object {
        const val REQUEST_CODE_DETAIL_ACTIVITY = 123
        const val RESULT_CODE_DELETE = 321
    }

}