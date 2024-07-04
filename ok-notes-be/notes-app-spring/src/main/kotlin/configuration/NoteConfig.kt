package configuration

import NoteLoggerProvider
import com.otus.otuskotlin.marketplace.NoteCorSettings
import com.otus.otuskotlin.marketplace.NotesProcessor
import com.otus.otuskotlin.marketplace.base.AppSettings
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class NoteConfig {

    @Bean
    fun processor(corSettings: NoteCorSettings) = NotesProcessor(corSettings = corSettings)

    @Bean
    fun corSettings(): NoteCorSettings = NoteCorSettings(
        loggerProvider = NoteLoggerProvider(),
    )

    @Bean
    fun appSettings(
        corSettings: NoteCorSettings,
        processor: NotesProcessor,
    ) = AppSettings(
        corSettings = corSettings,
        processor = processor,
        )
}
