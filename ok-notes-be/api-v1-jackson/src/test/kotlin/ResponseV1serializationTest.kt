import com.otus.otuskotlin.marketplace.api.v1.models.*
import com.otus.otuskotlin.marketplace.apiV1Mapper
import org.junit.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ResponseV1serializationTest {
private val response = NoteCreateResponse(
    responseType = "create",
    result = ResponseResult.SUCCESS,
    errors = listOf(),
    note = NoteResponseObject(
        noteId = "5",
        title = "note-1",
        description = "description-1",
        owner = NoteOwner(
            id = OwnerId(id = "id-1"),
            name = "name-1",
            phone = "777-77-77",
            email = "test@test.com"
        ),
        files = null,
        permission = null

    )
)

    @Test
    fun serializationTest() {
        val json = apiV1Mapper.writeValueAsString(response)

        assertContains(json, Regex("\"responseType\":\\s*\"create\""))
        assertContains(json, Regex("\"result\":\\s*\"success\""))
        assertContains(json, Regex("\"errors\":\\s*\\[]"))
        assertContains(json, Regex("\"noteId\":\\s*\"5\""))
        assertContains(json, Regex("\"title\":\\s*\"note-1\""))
        assertContains(json, Regex("\"description\":\\s*\"description-1\""))
        assertContains(json, Regex("\"owner\":"))
        assertContains(json, Regex("\"id\":\\s*\"id-1\""))
        assertContains(json, Regex("\"name\":\\s*\"name-1\""))
        assertContains(json, Regex("\"phone\":\\s*\"777-77-77\""))
        assertContains(json, "test@test.com")
        assertContains(json, Regex("\"files\":null"))
        assertContains(json, Regex("\"permission\":"))
    }

    @Test
    fun deserializationTest() {
        val json = apiV1Mapper.writeValueAsString(response)
        val obj = apiV1Mapper.readValue(json, IResponse::class.java) as NoteCreateResponse

        assertEquals(response, obj)

    }
}