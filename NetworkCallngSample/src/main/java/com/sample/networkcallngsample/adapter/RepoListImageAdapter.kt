package com.sample.networkcallngsample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.networkcallngsample.R
import com.sample.networkcallngsample.data.Items
import com.sample.networkcallngsample.data.RepoResult
import kotlinx.android.synthetic.main.list_item_repo_image.view.*

class RepoListImageAdapter(val repoResult: RepoResult) : RecyclerView.Adapter<RepoListImageAdapter.RepoViewHolder>() {

    class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle: AppCompatTextView
        var textViewDesc: AppCompatTextView
        var imageAvtar: AppCompatImageView
        var item: Items? = null
        init {
            textViewTitle = itemView.findViewById(R.id.repoTitle)
            textViewDesc = itemView.findViewById(R.id.repoDescription)
            imageAvtar = itemView.findViewById(R.id.repoAvtarImage)
        }
        fun setData(item: Items) {
            this.item = item
            textViewTitle.text = item.name
            textViewDesc.text = item.description

            Glide.with(itemView)  //2
                .load(item.owner.avatar_url) //3
                .centerCrop() //4
                .placeholder(R.drawable.ic_placeholder) //5
                .error(R.drawable.ic_broken_image_24) //6
                .fallback(R.drawable.ic_broken_image_24) //7
                .into(itemView.repoAvtarImage) //8
           // textViewTitle.text = item.owner.avatar_url
        }
    }

    override fun getItemCount(): Int {
        return repoResult.items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        println("resultList item size :-  " +repoResult!!.items!!.size)
        return  RepoViewHolder(
            LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_repo_image, parent, false));
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        println(" resultList item :-  " +repoResult!!.items!!.get(position).name)
        holder.setData(repoResult!!.items!!.get(position))
    }


}