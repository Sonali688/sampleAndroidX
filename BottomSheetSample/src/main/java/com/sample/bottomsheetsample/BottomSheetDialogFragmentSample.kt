package com.sample.bottomsheetsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sample.bottomsheetsample.adapter.ItemAdapter
import kotlinx.android.synthetic.main.layout_modal_bottom_sheet.*


class BottomSheetDialogFragmentSample : BottomSheetDialogFragment(),ItemAdapter.ItemListener {

    companion object {

        const val TAG = "BottomSheetDialogFragmentSample"

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_modal_bottom_sheet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        button1.setOnClickListener {
            //handle click event
            Toast.makeText(context, "First Button Clicked", Toast.LENGTH_SHORT).show()
        }
        button2.setOnClickListener {
            //handle click event
            Toast.makeText(context, "Second Button Clicked", Toast.LENGTH_SHORT).show()
        }
        button3.setOnClickListener {
            //handle click event
            Toast.makeText(context, "Third Button Clicked", Toast.LENGTH_SHORT).show()
        }

        recyclerView.setHasFixedSize(true)
        recyclerView.setLayoutManager(LinearLayoutManager(this.context))

        val items = ArrayList<Any>()
        items.add("Item 1")
        items.add("Item 2")
        items.add("Item 3")
        items.add("Item 4")
        items.add("Item 5")
        items.add("Item 6")

        var mAdapter  = ItemAdapter(items, this)
        recyclerView.setAdapter(mAdapter)

    }

    override fun onItemClick(item: String?) {
        this.dismiss()

        //TODO("Not yet implemented")
    }
}