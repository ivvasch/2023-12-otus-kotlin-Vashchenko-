//package com.otus.otuskotlin.marketplace.configuration
//
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.web.reactive.HandlerMapping
//import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
//import org.springframework.web.reactive.socket.WebSocketHandler
//
//@Configuration
//class WebSocketConfig(
//    private val notesController: NotesControllerWS,
//) {
//    @Bean
//fun handlerMapping(): HandlerMapping {
//    val handlerMap: Map<String, WebSocketHandler> = mapOf(
//        "/vq/ws" to notesController,
//    )
//        return SimpleUrlHandlerMapping(handlerMap, 1)
//}
//}