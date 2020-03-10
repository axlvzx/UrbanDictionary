package com.example.urbandicionary.ui.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.urbandicionary.R
import com.example.urbandicionary.databinding.FragmentDefinitionDetailBinding
import com.example.urbandicionary.utils.ApiResponse
import com.example.urbandicionary.viewmodel.DefinitionViewModel
import javax.inject.Inject


class DefinitionDetailFragment : Fragment() {



    private lateinit var definitionViewModel: DefinitionViewModel

    private lateinit var definitionDetailBinding: FragmentDefinitionDetailBinding

    private var position: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        position = arguments?.getString("position")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        definitionDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_definition_detail, container, false)
        return definitionDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        definitionViewModel = ViewModelProviders.of(activity!!).get(DefinitionViewModel::class.java)
        definitionViewModel.getDefinitions().observe(viewLifecycleOwner, Observer {
            setDataOnView(it)
        })
    }

    private fun setDataOnView(data: ApiResponse) {

        val definition = data.data!!.results[position!!.toInt()]

        definitionDetailBinding.apply {
            tvTerm.text = definition.word
            tvDefinition.text = definition.definition
            tvAutor.text = definition.author
            tvExample.text = definition.example
            thumbUpRate.text = definition.thumbsUp.toString()
            thumbDowmRate.text = definition.thumbsDown.toString()
        }


    }


}
