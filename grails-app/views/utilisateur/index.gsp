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
    <title>Liste des messages</title>
</head>

<body>
Nombre de messages : ${cwitter.Utilisateur.get(params.get("id")).messages.size()}
<br><br>
<ul>
    <g:each var="message" in="${cwitter.Utilisateur.get(params.get("id")).messages}">
        <li>${message.text}</li>
    </g:each>
</ul>
</body>
</html>