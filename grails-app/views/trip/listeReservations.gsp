<html>
<head>
    <title>Liste des Réservations</title>
</head>
<body>
    <h1>Liste des Réservations</h1>
    
    <table>
        <thead>
            <tr>
                <th>Passager</th>
                <th>Trajet</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${reservations}" var="reservation">
                <tr>
                    <td>${reservation.passager}</td>
                    <td>${reservation.trajet}</td>
                </tr>
            </g:each>
        </tbody>
    </table>
</body>
</html>
