<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Trouvez votre prochain voiture</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
    <style>
        body {
            font-family: sans-serif;
            background-color: #f5f5f5;
        }
        .search-form {
            max-width: 700px;
            margin: 50px auto;
            background-color: #fff;
            padding: 30px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .search-form h2 {
            margin-bottom: 20px;
            font-weight: 400;
        }
        .results-table {
            margin-top: 50px;
        }
        .results-table th {
            text-align: left;
        }
        .results-table td {
            vertical-align: middle;
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="search-form">
            <h2>Trouvez votre prochaine voiture</h2>
            <g:form action="rechercher" method="post" class="row g-3">
                <div class="col-md-6">
                    <label for="departure" class="form-label">Départ:</label>
                    <g:textField name="departure" required="" class="form-control"/>
                </div>
                <div class="col-md-6">
                    <label for="destination" class="form-label">Destination:</label>
                    <g:textField name="destination" required="" class="form-control"/>
                </div>
                <div class="col-md-6">
                    <label for="departureTime" class="form-label">Heure de départ:</label>
                    <g:datePicker name="departureTime" required="" class="form-control"/>
                </div>
                <div class="col-md-4">
                    <g:submitButton name="Rechercher des trajets" class="btn btn-primary"/>
                    <hr>
                    <g:link action="proposer"  class="btn btn-sm btn-primary" style="height:35px; width:200px; font-size:15px">Proposer un trajet</g:link>
                </div>
            </g:form>
        </div>

        <h2 class="results-title mt-5">Résultats de la recherche</h2>

        <table class="results-table table table-bordered table-hover mt-3">
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
                        <td><g:link action="reserver" params="[id: trip.id]" class="btn btn-sm btn-primary">Réserver</g:link></td>
                    </tr>
                </g:each>
            </tbody>
        </table>
    </div>
</body>
</html>
