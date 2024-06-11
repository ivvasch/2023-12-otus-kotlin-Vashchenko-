package com.otus.otuskotlin.marketplace

import com.otus.otuskotlin.marketplace.api.v1.models.*
import com.otus.otuskotlin.marketplace.exception.UnknownCommand
import com.otus.otuskotlin.marketplace.models.*
import com.otus.otuskotlin.marketplace.models.Note
import java.io.File

fun NoteContext.toTransport(): IResponse = when (val cmd = command) {
    NoteCommand.CREATE -> toTransportCreate()
    NoteCommand.READ -> toTransportRead()
    NoteCommand.UPDATE -> toTransportUpdate()
    NoteCommand.DELETE -> toTransportDelete()
    NoteCommand.SEARCH -> toTransportSearch()
    else -> throw UnknownCommand(cmd)
}


fun NoteContext.toTransportCreate() = NoteCreateResponse(
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    note = noteResponse.toTransportNote()
)

fun NoteContext.toTransportRead() = NoteReadResponse(
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    note = noteResponse.toTransportNote()
)

fun NoteContext.toTransportUpdate() = NoteUpdateResponse(
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    note = noteResponse.toTransportNote()
)

fun NoteContext.toTransportDelete() = NoteDeleteResponse(
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    note = noteResponse.toTransportNote()
)

fun NoteContext.toTransportSearch() = NoteSearchResponse(
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    notes = mutableListOf(noteResponse.toTransportNote())
)

internal fun NoteState.toResult(): ResponseResult? = when (this) {
    NoteState.RUNNING -> ResponseResult.SUCCESS
    NoteState.FAILING -> ResponseResult.ERROR
    NoteState.FINISHING -> ResponseResult.SUCCESS
    NoteState.NONE -> null
}

internal fun List<NoteError>.toTransportErrors(): List<Error>? = this
    .map { it.toTransportNote() }
    .toList()
    .takeIf { it.isNotEmpty() }

internal fun NoteError.toTransportNote() = Error(
    code = code.takeIf { it.isNotBlank() },
    group = group.takeIf { it.isNotBlank() },
    field = field.takeIf { it.isNotBlank() },
    message = message.takeIf { it.isNotBlank() },
)

private fun Note?.toTransportNote(): NoteResponseObject = NoteResponseObject(
    noteId = this?.id?.takeIf { it != NoteId.NONE }?.asString(),
    title = this?.title.takeIf { it?.isNotBlank() ?: true },
    description = this?.description.takeIf { it?.isNotBlank() ?: true },
    owner = NoteOwner(OwnerId(this?.ownerId?.asString()), name = "", phone = "", email = ""),
    files = /*this?.files.toListFiles()*/ mutableListOf<File>(),
    permission = this?.permission?.toPermission()
)

internal fun Set<NotePermissionUser>.toPermission(): Set<NotePermission> = this
    .map { it.toPermission() }
    .toSet()

private fun NotePermissionUser.toPermission(): NotePermission = when (this) {
    NotePermissionUser.READ -> NotePermission.READ
    NotePermissionUser.UPDATE -> NotePermission.UPDATE
    NotePermissionUser.DELETE -> NotePermission.DELETE
    NotePermissionUser.CREATE -> NotePermission.CREATE
}

private fun Any?.toListFiles(): MutableList<File> {
    val files = mutableListOf<File>()
    files += this as File
    return files
}
