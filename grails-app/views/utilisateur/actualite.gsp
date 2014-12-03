<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Actualité</title>
</head>

<body>

    <h1>Liste des messages de mon actualité</h1>
    <div class="row">
        <div class="col-md-10">
            <ul class="list-group">
                <g:each var="message" in="${messages}">
                    <li class="list-group-item" style="overflow:auto;">
                        <div class="col-md-10">
                            <a href='/Cwitter/utilisateur/mur?id=${message.user.getId()}' >${message.user.nom} ${message.user.prenom}</a> : ${message.text}
                        </div>
                        <div class="col-md-2">
                            <span class="badge"><g:formatDate format="dd/MM/yyyy HH:mm" date="${message.date}"/></span>
                        </div>
                        <br>
                    </li>
                </g:each>
            </ul>
        </div>
    </div>

</body>

</html>