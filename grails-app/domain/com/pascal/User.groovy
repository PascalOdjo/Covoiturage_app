package com.aristide

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

    private static final long serialVersionUID = 1

    String username
    String password
    String firstname
    String lastname
    String email
    String telephone
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    //DriverProfile driverProfile

    Set<Role> getAuthorities() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }

    static constraints = {
        password nullable: false, blank: false, password: true
        username nullable: false, blank: false, unique: true
        firstname nullable: false, blank: false, unique: true
        lastname nullable: false, blank: false, unique: true
        email unique: true, email: true, nullable: true
        telephone nullable: false, blank: false
    }

    /*static mapping = {
	    password column: '`password`'
        driverProfile cascade: 'all-delete-orphan'
    }*/
    
}
