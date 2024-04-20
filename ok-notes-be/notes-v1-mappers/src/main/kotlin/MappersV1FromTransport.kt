package com.otus.otuskotlin.marketplace

import com.otus.otuskotlin.marketplace.api.v1.models.*
import com.otus.otuskotlin.marketplace.exception.UnknownRequestClass
import com.otus.otuskotlin.marketplace.models.*
import com.otus.otuskotlin.marketplace.models.Note
import kotlinx.datetime.toKotlinInstant
import java.io.File
import java.time.Instant

fun NoteContext.fromTransport(request: IRequest)  = when(request){
    is NoteCreateRequest -> fromTransport(request)
    is NoteDeleteRequest -> fromTransport(request)
    is NoteReadRequest -> fromTransport(request)
    is NoteUpdateRequest -> fromTransport(request)
    is NoteSearchRequest -> fromTransport(request)
    else -> throw UnknownRequestClass(request::class.java)
}


fun NoteContext.fromTransport(request: NoteReadRequest) {
    state = NoteState.RUNNING
    noteRequest = request.note?.toInternal() ?: Note()
    workMode = request.debug?.mode.transportToWorkMode()
}

fun NoteContext.fromTransport(request: NoteUpdateRequest) {
    state = NoteState.RUNNING
    noteRequest = request.note?.toInternal() ?: Note()
    workMode = request.debug?.mode.transportToWorkMode()
}

fun NoteContext.fromTransport(request: NoteSearchRequest) {
    state = NoteState.RUNNING
    noteFilterRequest = request.noteFilter.toInternal()
    noteFilterRequest.ownerId = this.noteRequest.ownerId.asString()
    workMode = request.debug?.mode.transportToWorkMode()
}

fun NoteContext.fromTransport(request: NoteDeleteRequest) {
    state = NoteState.RUNNING
    noteRequest = request.note?.toInternal() ?: Note()
    workMode = request.debug?.mode.transportToWorkMode()
}

private fun NoteSearchFilter?.toInternal(): NoteFilter  = NoteFilter(
    searchString = this?.searchString ?: ""
)

private fun NoteUpdateObject?.toInternal(): Note  = Note(
    id = this?.noteId.toNoteId(),
    title = this?.title ?: "",
    description = this?.description ?: "",
    ownerId = NoteOwnerId(this?.owner?.id?.id ?: ""),
    files = this?.files.toListFiles(),
    permission = this?.owner.toSetPermission()
)

private fun NoteReadObject?.toInternal(): Note = if (this != null){
    Note(
        id = id.toNoteId()
    )
} else {
    Note()
}

private fun NoteDeleteObject?.toInternal(): Note = if (this != null) {
    Note(
        id = id.toNoteId()
    )
} else {
    Note()
}

private fun String?.toNoteId() = this?.let { NoteId(it)} ?: NoteId.NONE

// ---------
fun NoteContext.fromTransport(request: NoteCreateRequest) {
    state = NoteState.RUNNING
    noteRequest = request.note?.toInternal() ?: Note()
    workMode = request.debug?.mode.transportToWorkMode()
}

private fun NoteRequestDebugMode?.transportToWorkMode(): NoteWorkMode {
    if (this?.name.equals(NoteWorkMode.TEST.name)) {
        return NoteWorkMode.TEST
    } else {
        return NoteWorkMode.PROD
    }
}

private fun NoteCreateObject.toInternal(): Note = Note(
    title = this.title ?: "",
    description = this.description ?: "",
    ownerId = this.owner.toOwnerId(),
    createdAt = Instant.now().toKotlinInstant(),
    files = this.files.toListFiles(),
    permission = this.owner.toSetPermission()
)

private fun NoteOwner?.toSetPermission(): MutableSet<NotePermissionUser> {
    return mutableSetOf<NotePermissionUser>(NotePermissionUser.READ,
        NotePermissionUser.READ,
        NotePermissionUser.UPDATE,
        NotePermissionUser.DELETE,
        NotePermissionUser.CREATE)
    }


private fun Any?.toListFiles(): MutableList<File> {
    val files = mutableListOf<File>()
    files += this as File
    return files
}

private fun NoteOwner?.toOwnerId(): NoteOwnerId {
return NoteOwnerId(this?.id?.id.toString())
}
// -----
