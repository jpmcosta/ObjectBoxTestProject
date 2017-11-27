package com.example.jpmcosta.objectboxtestproject.objectbox

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

@Entity
class Note {

    companion object {

        fun create(text: String?): Note {
            val note = Note()
            note.text = text
            return note
        }
    }

    @Id
    var id: Long = NEW_ID

    var text: String? = null

    lateinit var parentItem: ToOne<Item>
}