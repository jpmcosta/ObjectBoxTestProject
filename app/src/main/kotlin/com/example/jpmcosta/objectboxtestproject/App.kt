package com.example.jpmcosta.objectboxtestproject

import android.app.Application
import com.example.jpmcosta.objectboxtestproject.objectbox.Item
import com.example.jpmcosta.objectboxtestproject.objectbox.MyObjectBox
import com.example.jpmcosta.objectboxtestproject.objectbox.Note
import com.example.jpmcosta.objectboxtestproject.objectbox.Project
import com.example.jpmcosta.objectboxtestproject.objectbox.core.put
import io.objectbox.BoxStore
import java.util.*

class App : Application() {

    companion object {

        private const val ITEM_COUNT = 20000

        private const val NOTE_COUNT_PER_ITEM = 4
    }

    lateinit var boxStore: BoxStore
        private set


    override fun onCreate() {
        super.onCreate()

        // Delete previous data for testing purposes.
        deleteData(MyObjectBox.builder().androidContext(this).build())

        boxStore = MyObjectBox.builder().androidContext(this).build()
        createData(boxStore)
    }

    private fun deleteData(boxStore: BoxStore) {
        boxStore.close()
        boxStore.deleteAllFiles()
    }

    private fun createData(boxStore: BoxStore) {
        val random = Random()

        val project = Project.create()

        for (i in 0 until ITEM_COUNT) {
            val item = Item.create()

            for (j in 0 until NOTE_COUNT_PER_ITEM) {
                val note = Note.create(text = "random text ${random.nextLong()}")
                item.notes.add(note)
            }

            project.items.add(item)
        }

        boxStore.put(project)
    }
}
