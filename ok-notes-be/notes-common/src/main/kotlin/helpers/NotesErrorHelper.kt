package com.otus.otuskotlin.marketplace.helpers

import com.otus.otuskotlin.marketplace.models.NoteError


fun Throwable.noteError(
    group: String = "exceptions",
    message: String = this.message ?: "",
    code: String = "uncnown"
) = NoteError(
    group = group,
    message = message,
    code = code
)
