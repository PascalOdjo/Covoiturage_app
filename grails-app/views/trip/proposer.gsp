<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Proposer un trajet</title>
</head>
<body>
    <h1>Proposer un trajet</h1>
    
    <g:form action="proposer" method="post">
        <label for="departure">Point de départ:</label>
        <g:textField name="trip.departure" required=""/>
        
        <label for="destination">Destination:</label>
        <g:textField name="trip.destination" required=""/>
        
        <label for="departureTime">Heure de départ:</label>
        <g:dateTimePicker name="trip.departureTime" required=""/>
        
        <label for="availableSeats">Nombre de places disponibles:</label>
        <g:numberField name="trip.availableSeats" required=""/>
        
        <label for="isEco">Trajet écologique:</label>
        <g:checkBox name="trip.isEco"/>
        
        <g:submitButton name="Proposer le trajet"/>
    </g:form>
</body>
</html>
