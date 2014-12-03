<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>${user.nom} ${user.prenom}</title>
</head>

<body>

    <h1>${user.nom} ${user.prenom}</h1>
    <br>
    <h2>Mes messages :</h2>
    <br>
    <div class="row">
        <div class="col-md-10">
            <ul class="list-group">
                <g:each var="message" in="${user.messages}">
                    <li class="list-group-item" style="overflow:auto;">
                        <div class="col-md-9">
                            ${message.text}
                        </div>
                        <div class="col-md-2">
                            <span class="badge"><g:formatDate format="dd/MM/yyyy" date="${message.date}"/></span>
                        </div>
                        <div class="col-md-1">
                            <span class="glyphicon glyphicon-remove-circle badgeLien" style="color: #ff484b;" aria-hidden="true" onclick="document.location.href='/Cwitter/utilisateur/supprimerMessage?idUtilisateur=${user.id}&idMessage=${message.id}'"></span>
                        </div>
                        <br>
                    </li>
                </g:each>
            </ul>
        </div>
    </div>

    <div class="row">
        <div class="col-md-10">
            <g:form name="myForm" url="[action:'nouveauMessage',controller:'utilisateur']">
                <label>Nouveau message : </label><br/><input type="text" name="message" class="form-control" value="" required placeholder="Nouveau Message ..."><br/>
                <div class="btn_valider">
                    <input class="btn btn-default" type="submit" value="Poster" />
                </div>
            </g:form>
        </div>
    </div>
    <br><br>
    <h2>Utilisateurs que vous suivez :</h2>
    <br>
    <div class="row">
        <div class="col-md-10">
            <ul class="list-group">
                <g:each var="u" in="${user.utilisateursSuivis}">
                    <li class="list-group-item">
                        <a href='/Cwitter/utilisateur/mur?id=${u.getId()}'>${u.nom} ${u.prenom}</a>
                        <span class="badge">${u.messages.size()} messages</span>
                        <span class="badge badgeLien" onclick="document.location.href='/Cwitter/utilisateur/plusSuivre?idSuiveur=${user.id}&idSuivi=${u.id}'">Ne plus suivre</span>
                    </li>

                </g:each>
            </ul>
        </div>
    </div>
    <br><br>
    <h2>Utilisateurs qui vous suivent :</h2>
    <br>
    <div class="row">
        <div class="col-md-10">
            <ul class="list-group">
                <g:each var="u" in="${user.utilisateursSuiveurs}">
                    <a href='/Cwitter/utilisateur/mur?id=${u.getId()}' class="list-group-item"><span class="badge">${u.messages.size()} messages</span>${u.nom} ${u.prenom}</a>
                </g:each>
            </ul>
        </div>
    </div>
    <br>

    <h2>Suivre une nouvelle personne :</h2>
    <div class="row">
        <div class="col-md-6">
            <ul class="list-group">
                <g:each var="u" in="${cwitter.UtilisateurController.getListeUtilisateursASuivre(user.getId())}">
                    <a href='/Cwitter/utilisateur/suivre?idSuiveur=${user.id}&idSuivi=${u.id}' class="list-group-item">Suivre ${u.nom} ${u.prenom}</a>
                </g:each>
            </ul>
        </div>
    </div>

</body>

</html>