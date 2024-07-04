package com.otus.otuskotlin.marketplace

import NoteLoggerProvider

data class NoteCorSettings(
    val loggerProvider: NoteLoggerProvider = NoteLoggerProvider(),
) {
    companion object {
        val NONE = NoteCorSettings()
    }
}
