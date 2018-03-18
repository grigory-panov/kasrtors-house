<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head th:replace="head">
</head>
<body>
<div th:replace="navigation :: top"></div>
<div th:replace="navigation :: jumbotron"></div>
<div class="container">
    <div class="row row-offcanvas row-offcanvas-left">
        <div class="col-xs-12 col-sm-9">
            <p th:replace="navigation :: navbutton"/>
            <div class="row">

                <div class="col-xs-6 col-lg-4">
                    <h2 th:text="#{index.about.club}">About club</h2>

                    <div th:utext="${about_kennelclub}" th:remove="tag"></div>

                    <p><a class="btn btn-default" th:utext="#{button.next}" th:href="@{/article/about_kennelclub.html}" role="button">Next &raquo;</a></p>
                </div>
                <div class="col-xs-6 col-lg-4" th:if="${img_dog}">
                    <a th:href="${'/photo.html?club=true&amp;filter=' + img_dog.tag}"><h2 th:text="#{link.our.dogs}" >Our dogs</h2></a>
                    <a th:href="${img_dog.photoLarge}">
                        <img th:src="${img_dog.thumb}" tr:alt="${img_dog.header}" class="img-responsive img-rounded"/>
                    </a>
                    <p th:text="${img_dog.header}"></p>
                    <p><a class="btn btn-default" th:utext="#{button.galery}" th:href="${'/photo.html?club=true&amp;filter=' + img_dog.tag}" href="#" role="button">Galery &raquo;</a></p>
                </div>
                <div class="col-xs-6 col-lg-4" th:if="${img_puppy}">
                    <a th:href="${'/photo.html?club=true&amp;filter=' + img_puppy.tag}"><h2 th:text="#{link.our.puppies}" >Our puppies</h2></a>
                    <a th:href="${img_puppy.photoLarge}">
                        <img th:src="${img_puppy.thumb}" tr:alt="${img_puppy.header}" class="img-responsive img-rounded"/>
                    </a>
                    <p th:text="${img_puppy.header}"></p>

                    <p><a class="btn btn-default" th:utext="#{button.galery}" th:href="${'/photo.html?club=true&amp;filter=' + img_puppy.tag}" href="#" role="button">Galery &raquo;</a></p>
                </div>
                <div class="col-xs-6 col-lg-4" th:unless="${img_puppy}">
                    <h2 th:text="#{link.our.puppies}" >Puppies</h2>
                    <p th:text="#{link.our.puppies.gone}" >Not found</p>
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
