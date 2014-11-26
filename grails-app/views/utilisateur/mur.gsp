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
<br><br><br>
Utilisateurs que ${user.nom} ${user.prenom} suit :
<br>
<ul>
    <g:each var="u" in="${user.utilisateursSuivis}">
        <li>${u.nom} ${u.prenom} - <g:link controller="utilisateur" id="${u.id}" action="mur">${u.messages.size()} message(s)</g:link></li></li>
    </g:each>
</ul>
<br><br><br>
Utilisateurs qui suivent ${user.nom} ${user.prenom} :
<br>
<ul>
    <g:each var="u" in="${user.utilisateursSuiveurs}">
        <li>${u.nom} ${u.prenom} - <g:link controller="utilisateur" id="${u.id}" action="mur">${u.messages.size()} message(s)</g:link></li>
    </g:each>
</ul>

</body>
</html>