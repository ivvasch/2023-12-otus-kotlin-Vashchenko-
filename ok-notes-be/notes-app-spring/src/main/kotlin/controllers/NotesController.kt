//package com.otus.otuskotlin.marketplace.controllers
//
//import com.otus.otuskotlin.marketplace.api.v1.models.*
//import com.otus.otuskotlin.marketplace.base.AppSettings
//import com.otus.otuskotlin.marketplace.base.controllerHelper
//import com.otus.otuskotlin.marketplace.fromTransport
//import com.otus.otuskotlin.marketplace.models.NoteRequestId
//import org.springframework.web.bind.annotation.CrossOrigin
//import org.springframework.web.bind.annotation.PostMapping
//import org.springframework.web.bind.annotation.RequestBody
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.RestController
//import kotlin.reflect.KClass
//
//@RestController
//@RequestMapping("/v1/notes")
//@CrossOrigin(origins = ["*"])
//class NotesController(
//    private val appSettings: AppSettings
//) {
//
//
//
//    @PostMapping("create")
//    suspend fun create(@RequestBody request: NoteCreateRequest): NoteCreateResponse =
//        process(request = request, this::class, "create")
//
//    @PostMapping("read")
//    suspend fun read(@RequestBody request: NoteReadRequest): NoteReadResponse =
//        process(request = request, this::class, "read")
//
//    @PostMapping("update")
//    suspend fun update(@RequestBody request: NoteUpdateRequest): NoteUpdateResponse =
//        process(request = request, this::class, "update")
//
//    @PostMapping("delete")
//    suspend fun delete(@RequestBody request: NoteDeleteRequest): NoteDeleteResponse =
//        process(request = request, this::class, "delete")
//
//    @PostMapping("search")
//    suspend fun search(@RequestBody request: NoteSearchRequest): NoteSearchResponse =
//        process(request = request, this::class, "search")
//
//    companion object{
//        suspend inline fun <reified Q : IRequest, reified R : IResponse> process(
//            appSettings: AppSettings,
//            request: Q,
//            clazz: KClass<*>,
//            logId: String,
//        ): R = appSettings.controllerHelper(
//            {
//            fromTransport(request)
//            },
//            {
//                toTransport() as R
//            },
//            clazz,
//            logId,
//        )
//    }
//
//}