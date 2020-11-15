package com.bignerdranch.android.smartplazatest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.smartplazatest.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*




class MainActivity : AppCompatActivity(),CustomDialogFragment.Result {

   private var adapter:Adapter? = null
   lateinit var viewModel: MainViewModel

    var isLoading: Boolean = false
    var page = 1

    var searchMain: String = ""
    var highCostMain: Int?= null
    var lowCostMain: Int? = null
    var sortNameMain: String? = null


    override fun getResult(search: String, highCost: Int?, lowCost: Int?, sortName: String?) {
        Log.i("Dialog", "search: $search")
        searchMain = search
        highCostMain = highCost
        lowCostMain = lowCost
        sortNameMain = sortName
        Log.i("Product", "$highCostMain   $searchMain   $lowCostMain   $sortNameMain" )
        page = 1
        fetchProdust(search, highCost?.toInt(),sortName, lowCost,12,page)


    }





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("Dialog", "activity")


        val layoutManager = GridLayoutManager(this, 3)

        recyclerView.layoutManager = layoutManager
        adapter = Adapter(this, mutableListOf(), R.layout.product_item)
        recyclerView.adapter = adapter

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)


        recyclerView?.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                        page++
                        Log.i("Page" , "Load page: "+ page)
                        fetchNextProdust(searchMain, highCostMain?.toInt(),sortNameMain, lowCostMain,12,page)
                        Log.i("Chats" , "Load "+ page)

                    }
                }
            }
        })

        viewModel.getProductResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                if(!response.body()!!.product.isEmpty()){
                    adapter!!.clearAll()
                    adapter!!.addAll(response.body()!!.product)
                    isLoading = false

                    recyclerView.visibility = View.VISIBLE
                    TextView.visibility = View.INVISIBLE
                }else {
                    recyclerView.visibility = View.INVISIBLE
                    TextViewError.visibility = View.VISIBLE
                    TextView.visibility = View.INVISIBLE
                }
            }
        })


        viewModel.getNextProductResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                adapter!!.addAll(response.body()!!.product)
                isLoading = false

            }
        })







}

    private fun fetchProdust(search: String,
                                 highCost: Int?,
                                 sortName: String?,
                                 lowCost: Int?,
                                 pageSize: Int,
                                 pageNumber: Int) {

        isLoading = true

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)



        viewModel.getProduct(search, highCost?.toInt(),sortName, lowCost,pageSize,pageNumber)

    }
    private fun fetchNextProdust(search: String,
                                 highCost: Int?,
                                 sortName: String?,
                                 lowCost: Int?,
                                 pageSize: Int,
                                 pageNumber: Int) {

        isLoading = true

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)



        viewModel.getNextPage(search, highCost?.toInt(),sortName, lowCost,pageSize,pageNumber)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.menu, menu)
            return true
    }



    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.search -> {
            // User chose the "Print" item
            val myDialogFragment = CustomDialogFragment()
            val manager = supportFragmentManager

            myDialogFragment.show(manager, "myDialog")
            true
        }
        else ->{
            super.onOptionsItemSelected(item)
        }

    }



}