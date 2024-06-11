package com.otus.otuskotlin.marketplace.controllers

import com.otus.otuskotlin.marketplace.*
import com.otus.otuskotlin.marketplace.api.v1.models.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/")
class NotesControllerStandard(private val processor: NotesProcessor) {

    @GetMapping("/test")
    fun test(): String {
        return "Test OK"
    }

    @PostMapping("create")
    suspend fun create(@RequestBody request: NoteCreateRequest): NoteCreateResponse {
        val context = NoteContext()
        context.fromTransport(request)
        processor.exec(context)
        return context.toTransportCreate()
    }

    @PostMapping("read")
    suspend fun read(@RequestBody request: NoteReadRequest): NoteReadResponse {
        val context = NoteContext()
        context.fromTransport(request)
        processor.exec(context)
        return context.toTransportRead()
    }

    @PostMapping("update")
    suspend fun update(@RequestBody request: NoteUpdateRequest): NoteUpdateResponse {
        val context = NoteContext()
        context.fromTransport(request)
        processor.exec(context)
        return context.toTransportUpdate()
    }

    @PostMapping("delete")
    suspend fun delete(@RequestBody request: NoteDeleteRequest): NoteDeleteResponse {
        val context = NoteContext()
        context.fromTransport(request)
        processor.exec(context)
        return context.toTransportDelete()
    }

    @PostMapping("search")
    suspend fun search(@RequestBody request: NoteSearchRequest): NoteSearchResponse {
        val context = NoteContext()
        context.fromTransport(request)
        processor.exec(context)
        return context.toTransportSearch()
    }
}
