<%--
  Created by IntelliJ IDEA.
  User: Jokho
  Date: 05/11/2014
  Time: 14:25
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Liste des utilisateurs</title>
</head>

<body>

    <h2>Nombre d'utilisateurs : ${cwitter.Utilisateur.count}</h2><br><br>
    <div class="col-md-10">
        <ul class="list-group">

            <g:each var="user" in="${cwitter.Utilisateur.all}">
                <a href='/Cwitter/utilisateur/mur?id=${user.getId()}' class="list-group-item"><span class="badge">${user.messages.size()} messages</span>${user.nom} ${user.prenom}</a>
            </g:each>

        </ul>
    </div>
</body>
</html>