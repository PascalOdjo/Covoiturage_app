package com.aristide

import grails.validation.ValidationException
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured
import com.aristide.User
import com.aristide.Role
import com.aristide.UserRole

@Transactional
@Secured('permitAll')
class RegisterController {

    static allowedMethods = [register: "POST"]

    def index() { }

    def register() {
        if (!params.password.equals(params.repassword)) {
            flash.message = "Password and confirmation do not match"
            redirect action: "index"
            return
        } else {
            try {
                def user = User.findByUsername(params.username) ?: new User(
                        username: params.username,
                        password: params.password,
                        firstname: params.firstname,
                        lastname: params.lastname,
                        email: params.email,
                        telephone: params.telephone
                ).save()
                def role = Role.get(params.role.id)
                if (user && role) {
                    UserRole.create user, role

                    UserRole.withSession {
                        it.flush()
                        it.clear()
                    }

                    flash.message = "You have registered successfully. Please login."
                    redirect controller: "login", action: "auth"
                } else {
                    flash.message = "Echec d'inscription"
                    render view: "index"
                    return
                }
            } catch (ValidationException e) {
                flash.message = "Registration Failed"
                redirect action: "index"
                return
            }
        }
    }
}
