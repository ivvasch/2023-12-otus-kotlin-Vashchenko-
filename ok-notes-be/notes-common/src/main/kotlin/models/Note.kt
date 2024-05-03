package com.otus.otuskotlin.marketplace.models

import com.otus.otuskotlin.marketplace.NONE
import kotlinx.datetime.Instant
import java.io.File

data class Note(
    var id: NoteId = NoteId.NONE,
    var title: String = "",
    var description: String = "",
    var ownerId: NoteOwnerId = NoteOwnerId.NONE,
    var createdAt: Instant = Instant.NONE,
    var files: MutableList<File> = mutableListOf(),
    var permission: MutableSet<NotePermissionUser> = mutableSetOf()
) {
    fun isEmpty() = this == NONE

    companion object {
        private val NONE = Note()
    }
}
