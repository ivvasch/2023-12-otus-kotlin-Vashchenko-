package com.otus.otuskotlin.marketplace.app.spring.controllers

import com.otus.otuskotlin.marketplace.NoteContext
import com.otus.otuskotlin.marketplace.NotesProcessor
import com.otus.otuskotlin.marketplace.api.v1.models.*
import com.otus.otuskotlin.marketplace.controllers.NotesControllerStandard
import com.otus.otuskotlin.marketplace.stubs.NoteStub
import com.otus.otuskotlin.marketplace.toTransportCreate
import configuration.NoteConfig
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.wheneverBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters

@WebFluxTest(NotesControllerStandard::class, NoteConfig::class)
class NotesControllerStandardWTest {

    @Autowired
    private lateinit var webclient: WebTestClient

    @MockBean
    lateinit var processor: NotesProcessor

    @BeforeEach
    fun setUp() {
        wheneverBlocking { (processor.exec(any())) }.then {
            it.getArgument<NoteContext>(0).apply {
                noteResponse = NoteStub.get()
                notesResponse = NoteStub.prepareSearchList(
                    "fff"
                ).toMutableList()
            }
        }
    }

    @Test
    fun create() =
        testStubNote(
            "/api/v1/create",
            NoteCreateRequest(),
            NoteContext(
                noteResponse = NoteStub.get()
            ).toTransportCreate().copy(responseType = "create")
        )

    @Test
    fun read() =
        testStubNote(
            "/api/v1/read",
            NoteReadRequest(),
            NoteContext(
                noteResponse = NoteStub.get()
            ).toTransportCreate().copy(responseType = "read")
        )

    @Test
    fun update() =
        testStubNote(
            "/api/v1/update",
            NoteUpdateRequest(),
            NoteContext(
                noteResponse = NoteStub.get()
            ).toTransportCreate().copy(responseType = "update")
        )

    @Test
    fun delete() =
        testStubNote(
            "/api/v1/delete",
            NoteDeleteRequest(),
            NoteContext(
                noteResponse = NoteStub.get()
            ).toTransportCreate().copy(responseType = "delete")
        )

    @Test
    fun search() =
        testStubNote(
            "/api/v1/search",
            NoteSearchRequest(),
            NoteContext(
                noteResponse = NoteStub.get()
            ).toTransportCreate().copy(responseType = "search")
        )

    private inline fun <reified Req : Any, reified Res : Any> testStubNote(
        url: String,
        noteRequestObject: Req,
        noteResponseObject: Res
    ) {
        webclient
            .post()
            .uri(url)
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(noteRequestObject))
            .exchange()
            .expectStatus().isOk
            .expectBody(Res::class.java)
            .value {
                println("Received res: $it")
                assertThat(it).isEqualTo(noteResponseObject)
            }
    }
}
