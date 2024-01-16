package com.aristide

class Notification {
    String title
    String body

    static belongsTo = [sender: User, receiver: User]

    static constraints = {
        title(nullable: false, blank: false)
        body(nullable: false, blank: false)
    }
}
