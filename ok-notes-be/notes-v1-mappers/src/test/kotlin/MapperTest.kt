import com.otus.otuskotlin.marketplace.NoteContext
import com.otus.otuskotlin.marketplace.api.v1.models.*
import com.otus.otuskotlin.marketplace.fromTransport
import com.otus.otuskotlin.marketplace.models.NoteId
import com.otus.otuskotlin.marketplace.models.NoteOwnerId
import com.otus.otuskotlin.marketplace.models.NoteWorkMode
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.io.File
import kotlin.test.assertNotNull

class MapperTest {

    @Test
    fun fromTransportCreateRequest() {
        val request = NoteCreateRequest(
            requestType = "create",
            debug = NoteDebug(NoteRequestDebugMode.TEST
            ),
            note = NoteCreateObject(
                title = "title-1",
                description = "description-1",
                owner = NoteOwner(
                    id = OwnerId(id = "id-1"),
                    name = "name-1",
                    phone = "7777-77",
                    email = "email@test.com"
                ),
                files = File("")
            )
        )

        val context = NoteContext()
        context.fromTransport(request)

        assertNotNull(context.noteRequest)
        assertEquals(NoteId(""), context.noteRequest.id)
        assertEquals("title-1", context.noteRequest.title)
        assertEquals("description-1", context.noteRequest.description)
        assertEquals(NoteWorkMode.TEST, context.workMode)
        assertEquals(NoteOwnerId("id-1"),context.noteRequest.ownerId)
        assertEquals(4, context.noteRequest.permission.size)
    }

    @Test
    fun fromTransportDeleteRequest() {
        val request = NoteDeleteRequest(
            requestType = "delete",
            debug = NoteDebug(NoteRequestDebugMode.TEST),
            note = NoteDeleteObject(
                id = "id-1",
            )
        )

        val context = NoteContext()
        context.fromTransport(request)

        assertNotNull(context.noteRequest)
        assertEquals(NoteId("id-1"), context.noteRequest.id)
    }

    @Test
    fun fromTransportUpdateRequest() {
        val request = NoteUpdateRequest(
            requestType = "update",
            debug = NoteDebug(NoteRequestDebugMode.TEST),
            note = NoteUpdateObject(
                noteId = "id-1",
                title = "title-1",
                description = "description-1",
                owner = NoteOwner(
                    id = OwnerId(id = "owner-1"),
                    name = "name-1",
                    phone = "7777-77",
                    email = "email@test.com",
                ),
                files = File(""),

            )
        )
        val context = NoteContext()
        context.fromTransport(request)

        assertNotNull(context.noteRequest)
        assertEquals(NoteId("id-1"), context.noteRequest.id)
        assertEquals("title-1", context.noteRequest.title)
        assertEquals("description-1", context.noteRequest.description)
        assertEquals(NoteWorkMode.TEST, context.workMode)
        assertEquals(NoteOwnerId("owner-1"), context.noteRequest.ownerId)
    }

    @Test
    fun fromTransportReadRequest() {
        val request = NoteReadRequest(
            requestType = "read",
            debug = NoteDebug(NoteRequestDebugMode.TEST),
            note = NoteReadObject(
                id = "id-1",
            )
        )
        val context = NoteContext()
        context.fromTransport(request)

        assertNotNull(context.noteRequest)
        assertEquals(NoteId(id = "id-1"), context.noteRequest.id)
    }

    @Test
    fun fromTransportSearchRequest() {
        val request = NoteSearchRequest(
            requestType = "search",
            debug = NoteDebug(NoteRequestDebugMode.TEST),
            noteFilter = NoteSearchFilter(
                searchString = "debug properties"
            )
        )
        val context = NoteContext()
        context.fromTransport(request)

        assertNotNull(context.noteRequest)
        assertEquals("debug properties", context.noteFilterRequest.searchString)
    }
}
