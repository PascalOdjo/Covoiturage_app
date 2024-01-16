<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="${gspLayout ?: 'main'}"/>
    <title>Inscription</title>
</head>

<body>
    <div class="row">
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card card-signin my-5">
          <div class="card-body">
            <h5 class="card-title text-center">Inscription</h5>
            <g:if test='${flash.message}'>
                <div class="alert alert-danger" role="alert">${flash.message}</div>
            </g:if>
            <form class="form-signin" action="register" method="POST" id="registerForm" autocomplete="off">
                <div class="form-group">
                    <label for="role">Profil</label>
                    <g:select class="form-control" name="role.id"
                        from="${com.aristide.Role.list()}"
                        optionKey="id" />
                </div>

                <div class="form-group">
                    <label for="username">Nom d'utilisateur</label>
                    <input type="text" placeholder="Votre nom d'utilisateur" class="form-control" name="username" id="username" autocapitalize="none"/>
                </div>

                <div class="form-group">
                    <label for="password">Mot de passe</label>
                    <input type="password" placeholder="Mot de passe" class="form-control" name="password" id="password"/>
                </div>

                <div class="form-group">
                    <label for="repassword">Confirmation</label>
                    <input type="password" placeholder="entrer à nouveau votre mot de passe" class="form-control" name="repassword" id="repassword"/>
                </div>
                

                <div class="form-group">
                    <label for="firstName">Nom</label>
                    <input type="text" placeholder="Votre nom" class="form-control" name="firstname" id="firstname" autocapitalize="none"/>
                </div>

                <div class="form-group">
                    <label for="lastName">Prénom</label>
                    <input type="text" placeholder="Votre prénon" class="form-control" name="lastname" id="lastname" autocapitalize="none"/>
                </div>

                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="text" placeholder="adresse email" class="form-control" name="email" id="email"/>
                </div>

                <div class="form-group">
                    <label for="telephone">Telephone</label>
                    <input type="text" placeholder="téléphone" class="form-control" name="telephone" id="telephone"/>
                </div>

                <button id="submit" class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">S'inscrire</button>
                <hr class="my-4">
                <p>Vous avez déjà un compte? <g:link controller="login" action="auth">se connecter</g:link></p>
            </form>
          </div>
        </div>
      </div>
    </div>
    <script type="text/javascript">
        document.addEventListener("DOMContentLoaded", function(event) {
            document.forms['registerForm'].elements['username'].focus();
        });
    </script>
</body>
</html>

