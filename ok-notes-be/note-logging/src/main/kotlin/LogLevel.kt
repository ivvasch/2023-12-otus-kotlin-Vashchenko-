enum class LogLevel(
    private val levelInt: Int,
    private val levelStr: String,

) {
    ERROR(1, "ERROR"),
    WARNING(2, "WARNING"),
    INFO(3, "INFO"),
    DEBUG(4, "DEBUG"),
    TRACE(5, "TRACE");

    fun toInt(): Int {
        levelInt
        return this.levelInt
    }

    override fun toString(): String {
        return levelStr
    }
}
