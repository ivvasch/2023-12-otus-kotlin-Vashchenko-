package com.otus.otuskotlin.marketplace.app.spring.controllers

import ILogWrapper
import NoteLoggerProvider
import com.ninjasquad.springmockk.MockkBean
import com.otus.otuskotlin.marketplace.NoteContext
import com.otus.otuskotlin.marketplace.NoteCorSettings
import com.otus.otuskotlin.marketplace.NotesProcessor
import com.otus.otuskotlin.marketplace.stubs.NoteStub
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.wheneverBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath


@SpringBootTest
//@WebMvcTest(NotesControllerStandard::class, NoteConfig::class)
@AutoConfigureMockMvc
class NotesControllerStandardTest {

    @Autowired
    lateinit var mvc: MockMvc

    @MockkBean
    lateinit var processor: NotesProcessor

    @MockkBean
    lateinit var corSettings: NoteCorSettings

    @MockkBean
    lateinit var loggerProvider: NoteLoggerProvider

    @MockkBean
    lateinit var logWrapper: ILogWrapper

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
    fun create() {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/notes/"))
        .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(jsonPath("$.id", Matchers.notNullValue()))
            .andExpect(jsonPath("$.ownerId", Matchers.notNullValue()))
            .andExpect(jsonPath("$.title", Matchers.notNullValue()))
            .andExpect(jsonPath("$.description", Matchers.notNullValue()))
            .andExpect(jsonPath("$.createdAt", Matchers.notNullValue()))
    }

    @Test
    fun read() {
    }

    @Test
    fun update() {
    }

    @Test
    fun delete() {
    }

    @Test
    fun search() {
    }
}
