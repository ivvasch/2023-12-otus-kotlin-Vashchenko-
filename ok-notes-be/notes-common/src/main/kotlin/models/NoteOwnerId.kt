package com.otus.otuskotlin.marketplace.models

@JvmInline
value class NoteOwnerId (private val id: String) {
    fun asString(): String = id
    companion object {
        val NONE = NoteOwnerId("")
    }
}
