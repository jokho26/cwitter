<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>${user.nom} ${user.prenom}</title>
</head>

<body>
<h1>${user.nom} ${user.prenom}</h1>
<br>
<h2>Messages de l'utilisateur :</h2>
<br>
<div class="row">
    <div class="col-md-3">
        <ul class="list-group">
            <g:each var="message" in="${user.messages}">
                <li class="list-group-item">${message.text}</li>
            </g:each>
        </ul>
    </div>
</div>
<br><br>
<h2>Utilisateurs que ${user.nom} ${user.prenom} suit :</h2>
<br>
<div class="row">
    <div class="col-md-3">
        <ul class="list-group">
            <g:each var="u" in="${user.utilisateursSuivis}">
                <a href='/Cwitter/utilisateur/mur?id=${u.getId()}' class="list-group-item"><span class="badge">${u.messages.size()}</span>${u.nom} ${u.prenom}</a>
            </g:each>
        </ul>
    </div>
</div>
<br><br>
<h2>Utilisateurs qui suivent ${user.nom} ${user.prenom} :</h2>
<br>
<div class="row">
    <div class="col-md-3">
        <ul class="list-group">
            <g:each var="u" in="${user.utilisateursSuiveurs}">
                <a href='/Cwitter/utilisateur/mur?id=${u.getId()}' class="list-group-item"><span class="badge">${u.messages.size()}</span>${u.nom} ${u.prenom}</a>
            </g:each>
        </ul>
    </div>
</div>

</body>
</html>