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
Nombre d'utilisateur : ${cwitter.Utilisateur.count}<br>
<ul>
    <g:each var="user" in="${cwitter.Utilisateur.all}">
        <li>${user.nom} ${user.prenom} - <g:link controller="utilisateur" id="${user.id}">${user.messages.size()} message(s)</g:link></li>
    </g:each>
</ul>
</body>
</html>