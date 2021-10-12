package com.ps.psdemo.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputBinding
import androidx.recyclerview.widget.RecyclerView
import com.ps.psdemo.data.PostData
import com.ps.psdemo.databinding.MainRecycleItemsBinding

class MainAdapter(private var postItemList: List<PostData>) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private lateinit var binding: MainRecycleItemsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        binding =
            MainRecycleItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        binding.mainRecycleItemName.text = postItemList[position].body
    }

    override fun getItemCount(): Int {
        return postItemList.size
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    fun setData(postList: List<PostData>) {
        this.postItemList = postList
    }

}