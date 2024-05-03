import com.otus.otuskotlin.marketplace.api.v1.models.*
import com.otus.otuskotlin.marketplace.apiV1Mapper
import org.junit.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class RequestV1serializationTest {
    private val request = NoteCreateRequest(
        requestType = "create",
        debug = NoteDebug(mode = NoteRequestDebugMode.TEST),
        note = NoteCreateObject(
            noteId = "1",
            title = "test note",
            description = "note for tests",
            owner = NoteOwner(
                id = OwnerId("123"),
                name = "Owner",
                phone = "777-77-77",
                email = "test@test.com"
            ),
            files = null
        )
    )


    @Test
    fun `serialize request`() {
        val json = apiV1Mapper.writeValueAsString(request)

        assertContains(json, Regex("\"requestType\":\\s*\"create\""))
        assertContains(json, Regex("\"noteId\":\\s*\"1\""))
        assertContains(json, Regex("\"title\":\\s*\"test note\""))
        assertContains(json, Regex("\"description\":\\s*\"note for tests\""))
        assertContains(json, Regex("\"id\":\\s*\"123\""))
        assertContains(json, Regex("\"name\":\\s*\"Owner\""))
        assertContains(json, Regex("\"phone\":\\s*\"777-77-77\""))
        assertContains(json, "test@test.com")
    }

    @Test
    fun deserialize() {
        val json = apiV1Mapper.writeValueAsString(request) // почему в json 2 requestType?
        val obj = apiV1Mapper.readValue(json, IRequest::class.java) as NoteCreateRequest

        assertEquals(request, obj)
    }

    @Test
    fun deserializeObj() {
        val jsonString = """
            {"note": null} 
        """.trimIndent()

        val obj = apiV1Mapper.readValue(jsonString, NoteCreateRequest::class.java)

        assertEquals(null, obj.note)
    }

}