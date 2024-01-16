package com.aristide

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class DriverProfile {

    String carModel
    Integer availableSeats
    byte[] photo
    byte[] licenseFile

    static belongsTo = [user: User]

    static constraints = {
        carModel nullable: true
        availableSeats nullable: true
        photo nullable: true
        licenseFile nullable: true
    }
}
