package com.example.urbandicionary.ui.fragment

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.core.view.isEmpty
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.urbandicionary.R
import com.example.urbandicionary.adapter.DefinitionAdapter
import com.example.urbandicionary.databinding.FragmentDefinitionListBinding
import com.example.urbandicionary.model.ListResult
import com.example.urbandicionary.utils.*
import com.example.urbandicionary.utils.Constants.BAD_RESPONSE
import com.example.urbandicionary.utils.Constants.NO_INTERNET_CONNECTION
import com.example.urbandicionary.utils.Constants.ONLY_LETTERS_ALLOWED
import com.example.urbandicionary.utils.Constants.SERVER_ERROR_RESPONSE
import com.example.urbandicionary.viewmodel.DefinitionViewModel
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class DefinitionListFragment : DaggerFragment() {

    private lateinit var  progressBar: ProgressBar
    private lateinit var  recyclerView: RecyclerView


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var definitionAdapter: DefinitionAdapter

    private lateinit var definitionViewModel: DefinitionViewModel

    private lateinit var definitionListFragment: FragmentDefinitionListBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        definitionListFragment = DataBindingUtil.inflate(inflater, R.layout.fragment_definition_list, container, false)
        return definitionListFragment.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = definitionListFragment.recyclerview
        progressBar = definitionListFragment.progressBar
        definitionViewModel = ViewModelProviders.of(this, viewModelFactory).get(DefinitionViewModel::class.java)
        definitionViewModel.getDefinitions().observe(viewLifecycleOwner, Observer(::handleResponse))
        definitionViewModel.getOrderBy().observe(viewLifecycleOwner, Observer {
           // sortListDefinitions(it)
        })

        createDefinitionAdapter()
    }

    private fun handleResponse(apiResponse: ApiResponse){

        when (apiResponse.status) {

            Status.LOADING -> {progressBar.visibility = VISIBLE}

            Status.SUCCESS -> {
                progressBar.visibility = INVISIBLE
                doOnSuccessResponse(apiResponse.data)
            }

            Status.ERROR -> {
                progressBar.visibility = INVISIBLE
                val error  = apiResponse.error?.message
                ToastMessages.showToast("$SERVER_ERROR_RESPONSE$error",activity!!)
            }

        }

    }

    private fun doOnSuccessResponse(response: ListResult?) {
        if (response != null) {
            definitionAdapter.setListDefinitions(response)
        } else {
            ToastMessages.showToast(BAD_RESPONSE,activity!!)
        }
    }

    private fun createDefinitionAdapter(){

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = definitionAdapter
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
        val manager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(activity!!.componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("", false)
                searchItem.collapseActionView()
                checkValidInput(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun checkInternetConnection(query: String?){

        if(NetworkConnection.hasConnection(activity!!.applicationContext))
            definitionViewModel.callDefinitionsRx(query)
        else{
            ToastMessages.showToast(NO_INTERNET_CONNECTION,activity!!)
        }
    }

    private fun checkValidInput(query: String?){

        if(CheckInput.isValidInput(query))
            checkInternetConnection(query)
        else
            ToastMessages.showToast(ONLY_LETTERS_ALLOWED,activity!!)
    }

    private fun checkListNotEmpty(order:Int){

        if(recyclerView.isEmpty())
            return

        sortListDefinitions(order)
        definitionViewModel.setOrderBy(order)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings_up -> {
                checkListNotEmpty(1)
                return true
            }
            R.id.action_settings_down -> {
                checkListNotEmpty(2)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun sortListDefinitions(order: Int){
        definitionAdapter.sortByThumbs(order)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

}
