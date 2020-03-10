package com.example.urbandicionary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.urbandicionary.R
import com.example.urbandicionary.databinding.ResultItemBinding
import com.example.urbandicionary.model.Definition
import com.example.urbandicionary.model.ListResult

class DefinitionAdapter : RecyclerView.Adapter<DefinitionAdapter.DefinitionListHolder>() {

    private var listDefinitions: ListResult? = null


    class DefinitionListHolder(@param:NonNull val definitionListItemBinding: ResultItemBinding) :
        RecyclerView.ViewHolder(definitionListItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionListHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ResultItemBinding.inflate(layoutInflater, parent, false)
        return DefinitionListHolder(binding)
    }

    override fun onBindViewHolder(holder: DefinitionListHolder, position: Int) {

        holder.apply {
            definitionListItemBinding.tvTerm.text = listDefinitions!!.results[position].word
            definitionListItemBinding.tvDefinition.text =
                listDefinitions!!.results[position].definition
            definitionListItemBinding.thumbUpRate.text =
                listDefinitions!!.results[position].thumbsUp.toString()
            definitionListItemBinding.thumbDowmRate.text =
                listDefinitions!!.results[position].thumbsDown.toString()
        }

        holder.itemView.apply {
            setOnClickListener { view ->
                val bundle = bundleOf("position" to position.toString())
                view.findNavController().navigate(
                    R.id.action_definitionListFragment_to_definitionDetailFragment,
                    bundle
                )
            }
        }


    }

    override fun getItemCount(): Int {

        if (listDefinitions == null)
            return 0

        return listDefinitions?.results!!.size

    }

    fun setListDefinitions(_listDefinitions: ListResult) {
        listDefinitions = _listDefinitions
        notifyDataSetChanged()
    }

    fun sortByThumbs(order: Int) {

        if (listDefinitions == null)
            return

        var sortedList: List<Definition>?

        when (order) {
            1 -> {
                listDefinitions.also {
                    sortedList = it?.results?.sortedWith(compareBy({ it.thumbsUp }))
                    listDefinitions?.results = sortedList!!.toMutableList()
                }
            }
            2 -> {

                listDefinitions.also {
                    sortedList = it?.results?.sortedWith(compareBy({ it.thumbsDown }))
                    listDefinitions?.results = sortedList!!.toMutableList()
                }
            }
        }

        notifyDataSetChanged()

    }

}