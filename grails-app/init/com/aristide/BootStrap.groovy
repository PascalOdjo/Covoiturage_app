package com.aristide

class BootStrap {

    def init = { servletContext ->
        Role adminRole = new Role(authority: "ROLE_ADMIN")
        Role passengerRole = new Role(authority: "ROLE_PASSENGER")
        Role driverRole = new Role(authority: "ROLE_DRIVER")

        adminRole.save()
        passengerRole.save()
        driverRole.save()

    }
    def destroy = {
    }
}
