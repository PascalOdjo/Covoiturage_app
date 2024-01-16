package com.aristide

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured


class NotificationController {

    def userDetailsService

    @Secured(['ROLE_PASSENGER'])
    def index() {
        def currentUser = userDetailsService.loggedInUser
        def notifications = Notification.findAllByReceiver(currentUser)
        [notifications: notifications]
    }

    @Secured(['ROLE_PASSENGER'])
    def consulter() {
        def notification = Notification.get(params.id)

        if (notification) {
            notification.read = true
            notification.save(flush: true)
            respond notification
        } else {
            respond "Notification non trouvée", status: 404
        }
    }

    @Secured(['ROLE_PASSENGER'])
    def laisserAvis() {
        // Logique pour laisser un avis sur une notification
        def currentUser = userDetailsService.loggedInUser
        def notification = Notification.get(params.id)
        
        if (notification) {

            respond "Avis laissé avec succès"
        } else {
            respond "Notification non trouvée", status: 404
        }
    }

     def save(Notification notification) {
        if (notification == null) {
            notFound()
            return
        }

        try {
            notificationService.save(notification)
        } catch (ValidationException e) {
            respond notification.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'notification.label', default: 'Notification'), notification.id])
                redirect notification
            }
            '*' { respond notification, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond notificationService.get(id)
    }

    def update(Notification notification) {
        if (notification == null) {
            notFound()
            return
        }

        try {
            notificationService.save(notification)
        } catch (ValidationException e) {
            respond notification.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'notification.label', default: 'Notification'), notification.id])
                redirect notification
            }
            '*'{ respond notification, [status: OK] }
        }
    }




}

    /*

    NotificationService notificationService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond notificationService.list(params), model:[notificationCount: notificationService.count()]
    }

    def show(Long id) {
        respond notificationService.get(id)
    }

    def create() {
        respond new Notification(params)
    }

    def save(Notification notification) {
        if (notification == null) {
            notFound()
            return
        }

        try {
            notificationService.save(notification)
        } catch (ValidationException e) {
            respond notification.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'notification.label', default: 'Notification'), notification.id])
                redirect notification
            }
            '*' { respond notification, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond notificationService.get(id)
    }

    def update(Notification notification) {
        if (notification == null) {
            notFound()
            return
        }

        try {
            notificationService.save(notification)
        } catch (ValidationException e) {
            respond notification.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'notification.label', default: 'Notification'), notification.id])
                redirect notification
            }
            '*'{ respond notification, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        notificationService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'notification.label', default: 'Notification'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'notification.label', default: 'Notification'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }*/

