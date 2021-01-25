package com.sample.networkcallngsample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.sample.networkcallngsample.R
import com.sample.networkcallngsample.data.RepoResult

class RepoListAdapter(val repoResult: RepoResult) : RecyclerView.Adapter<RepoListAdapter.RepoViewHolder>() {

    class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle: AppCompatTextView
        var item: String? = null
        init {
            textViewTitle = itemView.findViewById(R.id.repoTitle)
        }
        fun setData(item: String?) {
            this.item = item
            textViewTitle.text = item
        }
    }

    override fun getItemCount(): Int {
        return repoResult.items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        println("resultList item size :-  " +repoResult!!.items!!.size)
        return  RepoViewHolder(
            LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_repo, parent, false));
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        println(" resultList item :-  " +repoResult!!.items!!.get(position).name)
        holder.setData(repoResult!!.items!!.get(position).name)
    }


}