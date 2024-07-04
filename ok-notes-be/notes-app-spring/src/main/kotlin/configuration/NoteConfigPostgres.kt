package com.otus.otuskotlin.marketplace.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "psql")
data class NoteConfigPostgres(
    var host: String = "localhost",
    var port: Int = 5432,
    var user: String = "postgres",
    var password: String = "postgres",
    var database: String = "notesDB",
    var schema: String = "public",
    var table: String = "notes",
) {
//    val psql: SqlProperties = SqlProperties(
//        host = host,
//        port = port,
//        user = user,
//        password = password,
//        database = database,
//        schema = schema,
//        table = table,
//    )
}
