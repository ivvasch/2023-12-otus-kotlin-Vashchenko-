package com.otus.otuskotlin.marketplace.models

@JvmInline
value class NoteId (private val id: String) {
    fun asString() = id
    companion object {
        val NONE = NoteId("")
    }
}
