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
import com.bignerdranch.android.smartplazatest.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),CustomDialogFragment.Result {

   private var adapter:Adapter? = null
    private lateinit var viewModel: MainViewModel

    override fun getResult(search: String, highCost: String, lowCost: Int, sortName: String) {
        Log.i("Dialog", "search: $search")

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getProduct(search, highCost.toInt(),sortName, lowCost,12,1)

        Log.i("Dialog", sortName)
        viewModel.getProductResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                adapter!!.addAll(response.body()!!.product)

                for (product in response.body()!!.product) {
                    Log.i("Dialog", product.toString())
                }

            }
        })
        recyclerView.visibility = View.VISIBLE
        TextView.visibility = View.INVISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("Dialog", "activity")

        recyclerView.layoutManager = GridLayoutManager(this, 3)
        adapter = Adapter(this, mutableListOf(), R.layout.product_item)
        recyclerView.adapter = adapter


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