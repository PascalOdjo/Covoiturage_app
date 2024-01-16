package com.aristide

class Reservation {
    User passager
    Trip trajet

    static constraints = {
        passager nullable: false
        trajet nullable: false
        unique(['passager', 'trajet'])
    }
}
