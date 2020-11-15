package com.bignerdranch.android.smartplazatest

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.smartplazatest.model.Product
import com.squareup.picasso.Picasso

class Adapter(private val context: Activity?, private val Products: List<Product>, private val mRowLayout: Int): RecyclerView.Adapter<Adapter.MyViewHolder>() {


    fun addAll(data: List<Product>) {

        (Products as ArrayList<Product>?)!!.addAll(data)


        notifyDataSetChanged()
    }
    fun clearAll(){
        (Products as ArrayList<Product>?)!!.clear()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(mRowLayout, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return Products.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = Products[position].name
        holder.price.text = Products[position].price.toString() + " тг"


        val picasso:Picasso = Picasso.get()
            picasso.load("https://api.smartplaza.kz/mp/products/photos/" + Products[position].photo_1)
                .fit()
                .into(holder.image)
    }

    class MyViewHolder(item: View):RecyclerView.ViewHolder(item) {
        val name: TextView = item.findViewById(R.id.tvName)
        val price: TextView = item.findViewById(R.id.tvPrice)
        val image: ImageView = item.findViewById(R.id.ivImage)
    }


}
