<!DOCTYPE html>




<!--
<head>
    <meta name="layout" content="main"/>
    <title>Liste des trajets disponibles</title>
</head>
<body>
    <h1>Liste des trajets disponibles</h1>
    
    <table>
        <thead>
            <tr>
                <th>Départ</th>
                <th>Destination</th>
                <th>Heure de Départ</th>
                <th>Places Disponibles</th>
                <th>Écologique</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${trips}" var="trip">
                <tr>
                    <td>${trip.departure}</td>
                    <td>${trip.destination}</td>
                    <td>${trip.departureTime}</td>
                    <td>${trip.availableSeats}</td>
                    <td>${trip.isEco ? 'Oui' : 'Non'}</td>
                    <td><g:link action="reserver" params="[id: trip.id]">Réserver</g:link></td>
                </tr>
            </g:each>
        </tbody>
    </table>
</body>
</html>
-->

<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'trip.label', default: 'Trip')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
    <div id="content" role="main">
        <div class="container">
            <section class="row">
                <a href="#list-trip" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
                <div class="nav" role="navigation">
                    <ul>
                        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                    </ul>
                </div>
            </section>
            <section class="row">
                <div id="list-trip" class="col-12 content scaffold-list" role="main">
                    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
                    <g:if test="${flash.message}">
                        <div class="message" role="status">${flash.message}</div>
                    </g:if>
                    <f:table collection="${tripList}" />

                    <g:if test="${tripCount > params.int('max')}">
                    <div class="pagination">
                        <g:paginate total="${tripCount ?: 0}" />
                    </div>
                    </g:if>
                </div>
            </section>
        </div>
    </div>
    </body>
</html>