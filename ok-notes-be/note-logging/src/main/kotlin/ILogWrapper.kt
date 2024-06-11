import kotlinx.datetime.Clock
import kotlin.time.measureTimedValue

@Suppress("unused")
interface ILogWrapper {

    val loggerId: String

    fun log(
        message: String,
        level: LogLevel,
        marker: String = "DEV",
        throwable: Throwable? = null,
        data: Any? = null,
        objs: Map<String, Any>? = null,
    )

    fun error(
        message: String,
        marker: String = "DEV",
        throwable: Throwable? = null,
        data: Any? = null,
        objs: Map<String, Any>? = null
    ) = log(message, LogLevel.ERROR, marker, throwable, data, objs)

    fun info(
        message: String,
        marker: String = "DEV",
        data: Any? = null,
        objs: Map<String, Any>? = null
    ) = log(message, LogLevel.INFO, marker, throwable = null, data, objs)

    fun debug(
        message: String,
        marker: String = "DEV",
        data: Any? = null,
        objs: Map<String, Any>? = null
    ) = log(message, LogLevel.DEBUG, marker, throwable = null, data, objs)

    suspend fun <T> doWithLog(
        id: String? = "",
        level: LogLevel = LogLevel.INFO,
        block: suspend () -> T,
    ): T = try {
        log("Started $loggerId $id", level)
        val (res, diffTime) = measureTimedValue { block() }

        log(
            message = "Finished $loggerId $id",
            level = level,
            objs = mapOf("metricHandleTime" to diffTime.toIsoString())
        )
        res
    } catch (e: Throwable){
        log(
            message = "Failed $loggerId $id",
            level = LogLevel.ERROR,
            throwable = e
        )
        throw e
    }

    suspend fun <T> doWithErrorLog(
        id: String? = "",
        throwRequired: Boolean = true,
        block: suspend () -> T,
    ): T? = try {
        val result = block()
        result
    } catch (e: Throwable){
        log(
            message = "Failed $loggerId $id",
            level = LogLevel.ERROR,
            throwable = e,
        )
        if (throwRequired) throw e else null
    }

    companion object {
        val DEFAULT = object : ILogWrapper {
            override val loggerId: String = "NONE"

            override fun log(
                message: String,
                level: LogLevel,
                marker: String,
                throwable: Throwable?,
                data: Any?,
                objs: Map<String, Any>?,
            ) {
                val markerString = if (marker == "NONE") "" else marker
                val args = listOfNotNull(
                    "${Clock.System.now()} [${level.name}]-$markerString: $message",
                    throwable?.let { "${it.message ?: "Reason is"}:\n [${it.stackTrace.joinToString("\n")}]" },
                    data.toString(),
                    objs.toString(),
                )
                println(args.joinToString("\n"))
            }
        }
    }
}
