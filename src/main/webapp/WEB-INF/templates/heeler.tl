<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head th:replace="head">
</head>
<body>
<div th:replace="navigation :: top"></div>
<div th:replace="navigation :: jumbotron-heeler"></div>
<div class="container">
    <div class="row row-offcanvas row-offcanvas-left">
        <div class="col-xs-12 col-sm-9">
            <p th:replace="navigation :: navbutton"/>
            <div class="row">

                <div class="col-xs-6 col-lg-4">
                    <h2 th:text="#{template.about.breed}">about breed</h2>

                    <div th:utext="${about_heeler}" th:remove="tag"></div>

                    <p><a class="btn btn-default" href="/article/about_heeler.html" th:utext="#{button.next}" role="button">next aquo;</a></p>
                </div>
                <div class="col-xs-6 col-lg-4" th:if="${img_heeler}">
                    <a th:href="${'/photo.html?club=true&amp;filter=' + img_heeler.tag}"><h2 th:text="#{link.our.dogs}">our dogs</h2></a>
                    <a th:href="${img_heeler.photoLarge}">
                    <img th:src="${img_heeler.thumb}" tr:alt="${img_heeler.header}" class="img-responsive img-rounded"/>
                    </a>
                    <p th:text="${img_heeler.header}"></p>

                    <p><a class="btn btn-default" th:href="${'/photo.html?club=true&amp;filter=' + img_heeler.tag}" th:utext="#{button.galery}" role="button">galery &raquo;</a></p>
                </div>
                <div class="col-xs-6 col-lg-4" th:if="${img_puppy}">
                    <a th:href="${'/photo.html?club=true&amp;filter=' + img_puppy.tag}"><h2 th:text="#{link.our.puppies}">our puppies</h2></a>
                    <a th:href="${img_puppy.photoLarge}">
                        <img th:src="${img_puppy.thumb}" tr:alt="${img_puppy.header}" class="img-responsive img-rounded"/>
                    </a>
                    <p th:text="${img_puppy.header}"></p>

                    <p><a class="btn btn-default" th:href="${'/photo.html?club=true&amp;filter=' + img_puppy.tag}" th:utext="#{button.galery}" role="button">galery &raquo;</a></p>
                </div>
                <div class="col-xs-6 col-lg-4" th:unless="${img_puppy}">
                    <h2 th:text="#{link.our.puppies}">our puppies</h2>
                    <p th:text="#{link.our.heeler.puppies.gone}" >Not found</p>
                </div>

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
