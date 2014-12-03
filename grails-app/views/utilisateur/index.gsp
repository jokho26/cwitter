<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Cwitter</title>
</head>

<body>
<center>
<h1>Bienvenue sur Cwitter</h1>
<br>
<g:if test="${params.get("messageErreur") != null}">
    <b>${params.get("messageErreur")}</b><br><br><br>
</g:if>
<br>
<img src="${assetPath(src: 'cwitter.png')}">
</center>


</body>
</html>