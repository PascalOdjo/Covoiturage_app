package com.aristide

class Trip {
    String departure
    String destination
    Date departureTime
    int availableSeats
    boolean isEco

    static belongsTo = [user: User]
    static hasMany = [notifications: Notification]

    static constraints = {
        departure nullable: false, blank: false
        destination nullable: false, blank: false
        departureTime nullable: false
        availableSeats nullable: false, min: 1
    
    }
}

