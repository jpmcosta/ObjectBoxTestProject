package com.example.jpmcosta.objectboxtestproject.adapter

import android.support.v7.widget.RecyclerView
import com.example.jpmcosta.objectboxtestproject.objectbox.Item
import com.example.jpmcosta.objectboxtestproject.objectbox.extension.BoxStoreCrud
import io.objectbox.BoxStore
import io.objectbox.android.AndroidScheduler
import io.objectbox.reactive.DataObserver
import io.objectbox.reactive.DataSubscription

class ProjectAdapter(
        private val boxStore: BoxStore,
        private val projectId: Long
) : ItemListAdapter(), BoxStoreCrud, DataObserver<List<Item>> {

    private var subscription: DataSubscription? = null


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)

        boxStore.getProjectItemsQuery(projectId)
                .subscribe()
                .on(AndroidScheduler.mainThread())
                .observer(this)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView?) {
        super.onDetachedFromRecyclerView(recyclerView)

        subscription?.cancel()
    }

    override fun onData(data: List<Item>?) {
        items = data
    }
}