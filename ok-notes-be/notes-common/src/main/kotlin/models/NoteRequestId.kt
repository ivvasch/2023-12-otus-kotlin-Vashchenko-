package com.otus.otuskotlin.marketplace.models

@JvmInline
value class NoteRequestId(private val id: String) {
    fun asString() = id

    companion object {
        val NONE = NoteRequestId("")
    }
}
