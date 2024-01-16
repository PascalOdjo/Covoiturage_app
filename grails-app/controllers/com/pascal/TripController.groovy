package com.aristide

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

import java.text.SimpleDateFormat
import java.text.ParseException


class TripController {

    def userDetailsService

    TripService tripService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_ADMIN','ROLE_PASSENGER','ROLE_DRIVER' ])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond tripService.list(params), model:[tripCount: tripService.count()]
    }

    @Secured(['ROLE_DRIVER'])
    def create() {
        respond new Trip(params)
    }

    @Secured(['ROLE_DRIVER'])
    def update(Trip trip) {
        if (trip == null) {
            notFound()
            return
        }

        try {
            tripService.save(trip)
        } catch (ValidationException e) {
            respond trip.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'trip.label', default: 'Trip'), trip.id])
                redirect trip
            }
            '*'{ respond trip, [status: OK] }
        }
    }


    @Secured(['ROLE_DRIVER'])
    def edit(Long id) {
        respond tripService.get(id)
    }

     @Secured(['ROLE_DRIVER', 'ROLE_PASSENGER'])
    def show(Long id) {
        respond tripService.get(id)
    }


    @Secured(['ROLE_DRIVER'])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }
        tripService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'trip.label', default: 'Trip'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }


    @Secured(['ROLE_DRIVER'])
    def save(Trip trip) {
        if (trip == null) {
            notFound()
            return
        }

        try {
            tripService.save(trip)
        } catch (ValidationException e) {
            respond trip.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'trip.label', default: 'Trip'), trip.id])
                redirect trip
            }
            '*' { respond trip, [status: CREATED] }
        }
    }


    @Secured(['ROLE_DRIVER'])
    def proposer() {
        if (!userDetailsService.userLoggedIn) {
            redirect(controller: 'login', action: 'index')
            return
        }

        if (request.method == 'POST') {
            def user = userDetailsService.loggedInUser
            def tripData = params['trip']
            def newTrip = new Trip(departure: tripData.departure, destination: tripData.destination,
                                   departureTime: tripData.departureTime, availableSeats: tripData.availableSeats,
                                   isEco: tripData.isEco, user: user)

            if (newTrip.save()) {
                flash.message = "Trajet proposé avec succès."
                redirect(action: 'index')
            } else {
                flash.error = "Erreur lors de la proposition du trajet."
            }
        }
    }


    @Secured(['ROLE_ADMIN', 'ROLE_PASSENGER'])
    def rechercher() {

        def searchCriteria = new Trip()
        bindData(searchCriteria, params)

        def trips = Trip.createCriteria().list(max: 10) {
            if (searchCriteria.departure) {
                ilike('departure', "%${searchCriteria.departure}%")
            }
            if (searchCriteria.destination) {
                ilike('destination', "%${searchCriteria.destination}%")
            }
            if (searchCriteria.departureTime) {
                eq('departureTime', searchCriteria.departureTime)
            }
        }
        render(view: 'rechercher', model: [trips: trips])
    }


    @Secured(['ROLE_PASSENGER'])
    def reserver() {
        if (request.method == 'POST' && (trip = Trip.get(params['id'])) && trip.availableSeats > 0) {
            def user = userDetailsService.loggedInUser

            if (!user) {
                flash.error = "Utilisateur non connecté."
                redirect(action: 'index')
                return
            }

            Trip.withTransaction { status ->
                def reservation = new Reservation(passager: user, trajet: trip)

                if (reservation.save(flush: true)) {
                    trip.availableSeats -= 1
                    trip.save(flush: true)

                    flash.message = "Place réservée avec succès."
                    redirect(action: 'listeReservations') 
                } else {
                    status.setRollbackOnly()
                    flash.error = "Erreur lors de la réservation de la place."
                }
            }
        } else {
            flash.error = "Le trajet est complet ou n'existe pas."
            redirect(action: 'index')
        }
    }
    
    @Secured(['ROLE_PASSENGER'])
    def listeReservations() {
        def reservations = Reservation.list()
        [reservations: reservations]
    }


    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'trip.label', default: 'Trip'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }



}