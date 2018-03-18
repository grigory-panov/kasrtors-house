<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head th:replace="head">
</head>
<body>

<div class="container">
    <div th:unless="${param.error == null}" class="bg-danger">Неверный логин или пароль</div>

    <form class="form-signin"  method="post" action="/j_spring_security_check">
        <h2 class="form-signin-heading">Введите логин и пароль</h2>
        <label for="j_username" class="sr-only">Логин</label>
        <input type="text" id="j_username" name="j_username" class="form-control" placeholder="Login" required="true" autofocus="true"/>
        <label for="j_password" class="sr-only">Пароль</label>
        <input type="password" id="j_password" name="j_password" class="form-control" placeholder="Password" required="true"/>
        <div class="checkbox">
            <label>
                <input type="checkbox" name="_spring_security_remember_me"/> Запомнить меня
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
    </form>

</div> <!-- /container -->

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/main.js"></script>
</body>
</html>


