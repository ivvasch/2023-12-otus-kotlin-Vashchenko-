package com.otus.otuskotlin.marketplace.stubs

import com.otus.otuskotlin.marketplace.models.Note
import com.otus.otuskotlin.marketplace.models.NoteId
import com.otus.otuskotlin.marketplace.models.NoteOwnerId
import com.otus.otuskotlin.marketplace.models.NotePermissionUser
import kotlinx.datetime.Clock

object NoteStubOne {
    val NOTE_STUB: Note get() = Note(
        id = NoteId("test-id"),
        title = "Test title",
        description = "Test description",
        ownerId = NoteOwnerId("owner-id"),
        createdAt = Clock.System.now(),
        permission = mutableSetOf(
            NotePermissionUser.CREATE,
            NotePermissionUser.READ,
            NotePermissionUser.UPDATE,
            NotePermissionUser.DELETE
        )
    )
}
