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
    <title>${user.nom} ${user.prenom}</title>
</head>

<body>
    <b>${user.nom} ${user.prenom}</b>
    <br><br>
    Messages de l'utilisateur :
    <br>
    <ul>
        <g:each var="message" in="${user.messages}">
            <li>${message.text}</li>
        </g:each>
    </ul>
    <br><br>
    Les messages des utilisateurs dans les groupes possédés par l’utilisateur :
    <br>

    <g:each var="groupe" in="${user.groupes}">
        <li>${groupe.nom}</li>
    </g:each>

    <ul>
        ${user.groupes.size()}
    </ul>
</body>
</html>