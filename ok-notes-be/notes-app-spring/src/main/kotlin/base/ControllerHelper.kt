package com.otus.otuskotlin.marketplace.base

import com.otus.otuskotlin.marketplace.NoteContext
import com.otus.otuskotlin.marketplace.helpers.noteError
import com.otus.otuskotlin.marketplace.models.NoteState
import kotlinx.datetime.Clock
import org.apache.logging.log4j.LogManager.getLogger
import kotlin.reflect.KClass

suspend inline fun <T> INotesAppSettings.controllerHelper(
    crossinline getRequest: suspend NoteContext.() -> Unit,
    crossinline toResponse: suspend NoteContext.() -> T,
    clazz: KClass<*>,
    logId: String,
): T {
    val logger = getLogger(clazz)
    val ctx = NoteContext(
        timeStart = Clock.System.now(),
    )
    return try {
        ctx.getRequest()
        logger.info("Request $logId started for ${clazz.simpleName}, BIZ.")
        processor.exec(ctx)
        logger.info("Request $logId processed for ${clazz.simpleName}, BIZ.")
        ctx.toResponse()
    }catch (e: Throwable) {
        logger.info("Request $logId failed for ${clazz.simpleName}, BIZ.")
        ctx.state = NoteState.FAILING
        ctx.errors.add(e.noteError())
        processor.exec(ctx)
        ctx.toResponse()
    }
}
