<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Modification de mon compte</title>
</head>

<body>
<center><h1>Modification de mon compte</h1></center>
<g:if test="${params.get("messageErreur") != null}">
    <b>${params.get("messageErreur")}</b><br><br><br>
</g:if>
<br>
<g:form name="myForm" url="[action:'modifierUtilisateur',controller:'utilisateur']">
    <label>Nouveau mot de passe : </label><br/><input type="password" name="mdp" class="form-control" value=""><br />
    <label>Nouveau nom : </label><br/><input type="text" name="nom" class="form-control" value="${userAModifier.getNom()}" required><br />
    <label>Nouveau prénom : </label><br/><input type="text" name="prenom" class="form-control" value="${userAModifier.getPrenom()}" required><br />
    <div class="btn_valider">
        <input class="btn btn-default" type="submit" value="Valider" />
    </div>
</g:form>

</body>
</html>