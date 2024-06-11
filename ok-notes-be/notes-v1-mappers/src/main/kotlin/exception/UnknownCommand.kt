package com.otus.otuskotlin.marketplace.exception

import com.otus.otuskotlin.marketplace.models.NoteCommand

class UnknownCommand(command: NoteCommand) : Throwable("Wrong command $command at mapping to transport") {
}
