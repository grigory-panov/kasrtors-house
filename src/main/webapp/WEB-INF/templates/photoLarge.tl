<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head th:replace="head">
</head>
<body>
<div th:replace="navigation :: top"></div>

<div class="container padding60">
    <div class="row row-offcanvas row-offcanvas-left">
        <div class="col-xs-12 col-sm-9">
            <p th:replace="navigation :: navbutton"/>
            <div class="row" th:unless="${#lists.isEmpty(images)}">
                <div id="blueimp-gallery-carousel" class="blueimp-gallery blueimp-gallery-carousel">
                    <div class="slides"></div>
                    <h3 class="title"></h3>
                    <a class="prev">‹</a>
                    <a class="next">›</a>
                    <a class="play-pause"></a>
                    <ol class="indicator"></ol>
                </div>
            </div>
            <div class="row" th:if="${#lists.isEmpty(images)}">
                <h2>Изображение не найдено</h2>
            </div>
        </div>
        <div th:replace="navigation :: left"></div>
        <!--/.sidebar-offcanvas-->

    </div>

    <hr/>

    <div id="links" >
         <a th:each="row : ${images}" th:unless="${#lists.isEmpty(images)}" th:href="${row.full}" href="images/banana.jpg" th:title="${row.header}"/>
    </div>
    <footer th:replace="navigation :: footer"/>
</div>
<!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/main.js"></script>
<script src="/js/blueimp-gallery.min.js"></script>
<script src="/js/photoLarge.js"></script>
</body>
</html>
