package com.example.jpmcosta.objectboxtestproject.objectbox.extension

import com.example.jpmcosta.objectboxtestproject.objectbox.Item
import com.example.jpmcosta.objectboxtestproject.objectbox.Item_
import com.example.jpmcosta.objectboxtestproject.objectbox.Project
import com.example.jpmcosta.objectboxtestproject.objectbox.core.box
import io.objectbox.Box
import io.objectbox.BoxStore
import io.objectbox.query.Query

interface BoxStoreCrud {

    val BoxStore.projectBox: Box<Project> get() = box()

    val BoxStore.itemBox: Box<Item> get() = box()


    fun BoxStore.getFirstProject(): Project? = projectBox.query().build().findFirst()

    fun BoxStore.getProjectItemsQuery(id: Long): Query<Item> =
            itemBox.query()
                    .equal(Item_.parentProjectId, id)
                    .build()
}