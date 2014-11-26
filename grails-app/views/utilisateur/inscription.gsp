<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Inscription</title>
</head>

<body>
<center><h1>Inscription</h1></center>
<g:if test="${params.get("messageErreur") != null}">
    <b>${params.get("messageErreur")}</b><br><br><br>
</g:if>
<br>
<g:form name="myForm" url="[action:'inscription',controller:'utilisateur']">
    <label>Login : </label><br/><input type="text" name="login" class="form-control" value="" required><br/>
    <label>Mot de passe : </label><br/><input type="password" name="mdp" class="form-control" value="" required><br />
    <label>Nom : </label><br/><input type="text" name="nom" class="form-control" value="" required><br />
    <label>Prénom : </label><br/><input type="text" name="prenom" class="form-control" value="" required><br />
    <input type="hidden" name="actionForm" value="creerUtilisateur"/>

    <div class="btn_valider">
        <input class="btn btn-default" type="submit" value="Valider" />
    </div>
</g:form>

</body>
</html>