package com.otus.otuskotlin.marketplace.stubs

import com.otus.otuskotlin.marketplace.models.Note
import com.otus.otuskotlin.marketplace.models.NoteId
import com.otus.otuskotlin.marketplace.stubs.NoteStubOne.NOTE_STUB

object NoteStub {

    fun get(): Note = NOTE_STUB.copy()

    fun prepareSearchList(filter: String) = listOf(
        noteCreate("note-1", filter),
        noteCreate("note-2", filter),
        noteCreate("note-3", filter),
        noteCreate("note-4", filter),
        noteCreate("note-5", filter),
        noteCreate("note-6", filter),
    )

    private fun noteCreate(id: String, filter: String) =
        note(NOTE_STUB, id = id, filter = filter)

    private fun note(base: Note, id: String, filter: String) = base.copy(
        id = NoteId(id),
        title = "$filter $id",
        description = "desc $filter $id",
    )
}
