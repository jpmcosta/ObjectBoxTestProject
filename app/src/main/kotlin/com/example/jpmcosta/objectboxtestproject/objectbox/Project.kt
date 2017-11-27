package com.example.jpmcosta.objectboxtestproject.objectbox

import io.objectbox.annotation.Backlink
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany

@Entity
class Project {

    companion object {

        fun create(): Project = Project()
    }

    @Id
    var id: Long = NEW_ID

    @Backlink
    lateinit var items: ToMany<Item>
}