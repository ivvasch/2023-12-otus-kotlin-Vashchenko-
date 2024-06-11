package com.otus.otuskotlin.marketplace.base

import com.otus.otuskotlin.marketplace.NoteCorSettings
import com.otus.otuskotlin.marketplace.NotesProcessor

data class AppSettings(
    override val corSettings: NoteCorSettings,
    override val processor: NotesProcessor,
): INotesAppSettings {
}
