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
<br><br><br>

Les messages des utilisateurs dans les groupes possédés par ${user.nom} ${user.prenom}
<g:each var="groupe" in="${user.groupes}">
    <ul>
        <li>
            <b>${groupe.nom}</b> : ${groupe.users.size()} membre(s)
            <ul>
                <g:each var="membre" in="${groupe.users}">
                    <g:each var="message" in="${membre.messages}">
                        <li>${message.text}</li>
                    </g:each>
                </g:each>
            </ul>
        </li>
    </ul>
    <br><br>
</g:each>


Liste des utilisateurs dans les groupes possédés par ${user.nom} ${user.prenom}
<g:each var="groupe" in="${user.groupes}">
    <ul>
        <li>
            <b>${groupe.nom}</b> : ${groupe.users.size()} membre(s)
            <ul>
                <g:each var="membre" in="${groupe.users}">
                    <li>${membre.nom} ${membre.prenom}</li>
                </g:each>
            </ul>
        </li>
    </ul>
    <br><br>
</g:each>


Une liste de tous les messages (ceux de ${user.nom} ${user.prenom} et ceux des utilisateurs dans ses groupes) triés par ordre inverse de création :

</body>
</html>