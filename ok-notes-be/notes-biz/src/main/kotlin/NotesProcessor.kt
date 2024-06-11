package com.otus.otuskotlin.marketplace

import com.otus.otuskotlin.marketplace.models.NoteState
import com.otus.otuskotlin.marketplace.stubs.NoteStub

@Suppress("unused", "RedundantSuspendModifier")
class NotesProcessor(val corSettings: NoteCorSettings) {

    suspend fun exec(ctx: NoteContext) {
        ctx.noteResponse = NoteStub.get()
        ctx.notesResponse = mutableListOf()
        ctx.state = NoteState.RUNNING
    }
}
