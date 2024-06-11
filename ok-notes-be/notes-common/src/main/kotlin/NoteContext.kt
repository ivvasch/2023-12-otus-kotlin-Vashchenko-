package com.otus.otuskotlin.marketplace

import com.otus.otuskotlin.marketplace.models.NoteFilter
import com.otus.otuskotlin.marketplace.models.*
import kotlinx.datetime.Instant

data class NoteContext (
    var command: NoteCommand = NoteCommand.NONE,
    var state: NoteState = NoteState.NONE,
    val errors: MutableList<NoteError> = mutableListOf(),
    var workMode: NoteWorkMode = NoteWorkMode.PROD,

    var requestId: NoteRequestId = NoteRequestId.NONE,
    var timeStart: Instant = Instant.NONE,
    var noteRequest: Note = Note(),
    var noteFilterRequest: NoteFilter = NoteFilter(),

    var noteResponse: Note = Note(),
    var notesResponse: MutableList<Note> = mutableListOf(),

)
