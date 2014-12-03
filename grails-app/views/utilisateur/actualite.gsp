<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Actualité</title>
</head>

<body>

    <h1>Liste des messages de mon actualité</h1>
    <div class="row">
        <div class="col-md-3">
            <ul class="list-group">
                <g:each var="message" in="${user.messages}">
                    <li class="list-group-item">${message.text}</li>
                </g:each>
            </ul>
        </div>
    </div>

</body>

</html>