package com.rjt.retrofit.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rjt.retrofit.R
import com.rjt.retrofit.model.CategoryModel
import kotlinx.android.synthetic.main.row_category_adapter.view.*

class AdapterCategory(val mContext: Context, var mList: ArrayList<CategoryModel>) : RecyclerView.Adapter<AdapterCategory.ViewHolder>() {

    fun setData(list: ArrayList<CategoryModel>){
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterCategory.ViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.row_category_adapter, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: AdapterCategory.ViewHolder, position: Int) {
        var cat = mList.get(position)
        holder.bindView(cat)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindView(cat: CategoryModel){

            itemView.text_view_name.text = cat.productName
            itemView.text_view_desc.text = cat.description
            itemView.text_view_price.text = "$ " + cat.price.toString()
        }
    }
}