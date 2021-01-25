package com.sample.bottomsheetsample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.sample.bottomsheetsample.R


class ItemAdapter (
    val mItems: List<*>,
    val  mListener: ItemListener
        ) :RecyclerView.Adapter<ItemAdapter.ViewHolder>(){

    class ViewHolder(itemView: View, val mListener: ItemListener) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var textView: AppCompatTextView
        var item: String? = null
        fun setData(item: String?) {
            this.item = item
            textView.text = item
        }



        init {
            itemView.setOnClickListener(this)
            textView = itemView.findViewById(R.id.textView)
        }

        override fun onClick(v: View?) {

            if (mListener != null) {
                mListener.onItemClick(item);
            }
        }
    }

    interface ItemListener {
        fun onItemClick(item: String?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(
            LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bottom_sheet_item, parent, false),
        mListener);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(mItems!!.get(position) as String?);
    }

    override fun getItemCount(): Int {
        return mItems!!.size
    }
}