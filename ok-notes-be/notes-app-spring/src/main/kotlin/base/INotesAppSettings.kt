package com.otus.otuskotlin.marketplace.base

import com.otus.otuskotlin.marketplace.NoteCorSettings
import com.otus.otuskotlin.marketplace.NotesProcessor

interface INotesAppSettings {
    val corSettings: NoteCorSettings
    val processor: NotesProcessor
}
