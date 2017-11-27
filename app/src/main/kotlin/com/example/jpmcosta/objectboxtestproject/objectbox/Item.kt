package com.example.jpmcosta.objectboxtestproject.objectbox

import io.objectbox.annotation.Backlink
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany
import io.objectbox.relation.ToOne

@Entity
class Item {

    companion object {

        fun create(): Item = Item()
    }

    @Id
    var id: Long = NEW_ID

    var isActive: Boolean = true

    @Backlink
    lateinit var notes: ToMany<Note>

    lateinit var parentProject: ToOne<Project>
}