<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>${user.nom} ${user.prenom}</title>
</head>

<body>
<b>${user.nom} ${user.prenom} - Mon mur</b>
<br><br>
Mes messages :
<br>
<ul>
    <g:each var="message" in="${user.messages}">
        <li>${message.text}</li>
    </g:each>
</ul>
<br><br><br>
Utilisateurs que vous suivez :
<br>
<ul>
    <g:each var="u" in="${user.utilisateursSuivis}">
        <li>${u.nom} ${u.prenom} - <g:link controller="utilisateur" id="${u.id}" action="mur">${u.messages.size()} message(s)</g:link> <g:link controller="utilisateur" action="plusSuivre" params="[idSuiveur: user.id, idSuivi:u.id]">-</g:link></li></li>
    </g:each>
</ul>
<br><br><br>
Utilisateurs qui vous suivent :
<br>
<ul>
    <g:each var="u" in="${user.utilisateursSuiveurs}">
        <li>${u.nom} ${u.prenom} - <g:link controller="utilisateur" id="${u.id}">${u.messages.size()} message(s)</g:link></li>
    </g:each>
</ul>
<br><br><br>
Suivre une nouvelle personne :
<ul>
    <g:each var="u" in="${cwitter.UtilisateurController.getListeUtilisateursASuivre(user.getId())}">
        <li>${u.nom} ${u.prenom} - <g:link controller="utilisateur" action="suivre" params="[idSuiveur: user.id, idSuivi:u.id]">+</g:link></li>
    </g:each>
</ul>


</body>
</html>