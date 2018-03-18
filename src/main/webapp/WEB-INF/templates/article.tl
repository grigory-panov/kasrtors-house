<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head th:replace="head">
</head>
<body>
<div th:replace="navigation :: top"></div>
<!--<div th:replace="navigation :: jumbotron"></div>-->
<div class="container padding60">
    <div class="row row-offcanvas row-offcanvas-left">
        <div class="col-xs-12 col-sm-9">
            <p th:replace="navigation :: navbutton"/>
            <div class="row">
                <h2 th:text="${article.header}">header</h2>

                <div class="article" th:utext="${article.text}">TExt Textetxte fslfjsldf erweiuhr fdsjgofg</div>
            </div>
        </div>
        <div th:replace="navigation :: left"></div>
        <!--/.sidebar-offcanvas-->

    </div>

    <hr/>

    <footer th:replace="navigation :: footer"/>
</div>
<!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/main.js"></script>
</body>
</html>
