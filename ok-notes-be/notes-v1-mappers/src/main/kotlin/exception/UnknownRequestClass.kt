package com.otus.otuskotlin.marketplace.exception

class UnknownRequestClass(clazz: Class<*>) : RuntimeException("Class $clazz cannot be mapped to NoteContext") {
}
