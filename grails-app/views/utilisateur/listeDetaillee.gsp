<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Gestion des utilisateurs</title>
</head>

<body>
Liste d'utilisateurs :
<br>
<ul>
    <g:each var="user" in="${cwitter.Utilisateur.getAll()}">
        <li>
            <b>${user.nom} ${user.prenom}</b>
            <br>
            Suivis :
            <ul>
                <g:each var="uSuivi" in="${user.utilisateursSuivis}">
                    <li>${uSuivi.nom} ${uSuivi.prenom}</li>
                </g:each>
            </ul>
            Suiveurs :
            <ul>
                <g:each var="uSuiveur" in="${user.utilisateursSuiveurs}">
                    <li>${uSuiveur.nom} ${uSuiveur.prenom}</li>
                </g:each>
            </ul>
            <br><br>
        </li>
    </g:each>
</ul>
</body>
</html>