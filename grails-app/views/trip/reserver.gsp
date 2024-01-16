<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Réserver une place</title>
</head>
<body>
    <h1>Réserver une place</h1>
    
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:if test="${flash.error}">
        <div class="error">${flash.error}</div>
    </g:if>
    
    <g:form action="reserver" method="post">
        <g:hiddenField name="id" value="${params.id}"/>
        <g:submitButton name="Réserver la place"/>
    </g:form>
</body>
</html>
