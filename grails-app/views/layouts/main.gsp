<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">

  		<asset:stylesheet src="application.css"/>
        <asset:stylesheet src="bootstrap.css"/>
        <asset:stylesheet src="simple-sidebar.css"/>
        <asset:stylesheet src="bootstrap-theme.css"/>

        <asset:javascript src="application.js"/>

        <g:layoutHead/>
	</head>
<body>

<nav class="navbar navbar-inverse"" role="navigation">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Cwitter</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

            <g:form name="formConnexion" class="navbar-form navbar-right" url="[action:'connection',controller:'utilisateur']">

                <div class="form-group">
                    <input type="text" name="login" class="form-control" placeholder="Login">&nbsp;&nbsp;
                    <input type="password" name="mdp" class="form-control" placeholder="Mot de passe">
                </div>
                &nbsp;&nbsp;<button type="submit" class="btn btn-default">Connexion</button>
            </g:form>

        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<div id="wrapper">



    <!-- Sidebar -->
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            ==>${session["estConnecte"]}

                <li>
                    <g:link controller="utilisateur" action="inscription">Inscription</g:link>
                </li>

            <li>
                <a href="#">Oune</a>
            </li>
            <li>
                <a href="#">SANDALE</a>
            </li>
        </ul>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <g:layoutBody/>
                </div>
            </div>
        </div>
    </div>
    <!-- /#page-content-wrapper -->

</div>
<!-- /#wrapper -->


</body>





</html>
