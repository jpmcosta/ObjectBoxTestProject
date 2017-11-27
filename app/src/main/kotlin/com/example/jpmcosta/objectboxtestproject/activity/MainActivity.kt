package com.example.jpmcosta.objectboxtestproject.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.jpmcosta.objectboxtestproject.App
import com.example.jpmcosta.objectboxtestproject.R
import com.example.jpmcosta.objectboxtestproject.adapter.ItemListAdapter
import com.example.jpmcosta.objectboxtestproject.adapter.ProjectAdapter
import com.example.jpmcosta.objectboxtestproject.objectbox.Item
import com.example.jpmcosta.objectboxtestproject.objectbox.core.put
import com.example.jpmcosta.objectboxtestproject.objectbox.extension.BoxStoreCrud
import io.objectbox.BoxStore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BoxStoreCrud {

    private val boxStore: BoxStore by lazy { (applicationContext as App).boxStore }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val adapter = ProjectAdapter(boxStore, boxStore.getFirstProject()!!.id)
        adapter.setOnItemClickListener(object : ItemListAdapter.OnItemClickListener {

            override fun onItemClicked(item: Item) {
                item.isActive = !item.isActive
                boxStore.put(item)
            }
        })

        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
    }
}
