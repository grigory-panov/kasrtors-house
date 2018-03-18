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

            <form action="/photo.html" method="get" class="form-horizontal">
                <select id="filter" name="filter">
                    <option value="" th:text="#{filter.all}"/>
                    <option th:selected="${'cadebou' == filter}" value="cadebou" th:text="#{template.cadebou}"/>
                    <option th:selected="${'heeler' == filter}" value="heeler" th:text="#{template.heeler}"/>
                    <option th:selected="${'minibull' == filter}" value="minibull" th:text="#{template.minibull}"/>
                    <option th:selected="${'cadebou-puppy' == filter}" value="cadebou-puppy" th:text="#{template.cadebou.puppy}"/>
                    <option th:selected="${'heeler-puppy' == filter}" value="heeler-puppy" th:text="#{template.heeler.puppy}"/>
                    <option th:selected="${'minibull-puppy' == filter}" value="minibull-puppy" th:text="#{template.minibull.puppy}"/>
                </select>
                <select id="club" name="club">
                    <option value=""  th:text="#{filter.all}"/>
                    <option th:selected="${'true' == club}" value="true" th:text="#{filter.inClub.yes}"/>
                    <option th:selected="${'false' == club}" value="false" th:text="#{filter.inClub.no}"/>
                </select>
            </form>
            <hr/>
            <div th:replace="navigation :: pagination"></div>

            <div th:each="row, iterStat : ${imagesPage}" th:remove="tag" th:unless="${#lists.isEmpty(imagesPage)}">

                <div class="row row-offcanvas row-offcanvas-left" th:if="${iterStat.index} % 3 == 0">

                    <div class="col-xs-6 col-lg-4" th:if="${#lists.size(imagesPage) gt iterStat.index}">
                        <a th:href="${imagesPage[iterStat.index].photoLarge}">
                            <img th:src="${imagesPage[iterStat.index].thumb}" tr:alt="${imagesPage[iterStat.index].header}" class="img-responsive img-rounded"/>
                        </a>
                        <p th:text="${imagesPage[iterStat.index].header}" class="strong"></p>
                        <p th:if="${imagesPage[iterStat.index].inClub}" th:text="#{template.club.in}">in club</p>
                        <p th:text="${imagesPage[iterStat.index].description}"></p>
                    </div>
                    <div class="col-xs-6 col-lg-4" th:if="${#lists.size(imagesPage) gt iterStat.index+1}">
                        <a th:href="${imagesPage[iterStat.index+1].photoLarge}">
                        <img th:src="${imagesPage[iterStat.index+1].thumb}" tr:alt="${imagesPage[iterStat.index+1].header}" class="img-responsive img-rounded"/>
                        </a>
                        <p th:text="${imagesPage[iterStat.index+1].header}" class="strong"></p>
                        <p th:if="${imagesPage[iterStat.index+1].inClub}" th:text="#{template.club.in}">in club</p>
                        <p th:text="${imagesPage[iterStat.index+1].description}"></p>
                    </div>
                    <div class="col-xs-6 col-lg-4" th:if="${#lists.size(imagesPage) gt iterStat.index+2}">
                        <a th:href="${imagesPage[iterStat.index+2].photoLarge}">
                        <img th:src="${imagesPage[iterStat.index+2].thumb}" tr:alt="${imagesPage[iterStat.index+2].header}" class="img-responsive img-rounded"/>
                        </a>
                        <p th:text="${imagesPage[iterStat.index+2].header}" class="strong"></p>
                        <p th:if="${imagesPage[iterStat.index+2].inClub}" th:text="#{template.club.in}">in club</p>
                        <p th:text="${imagesPage[iterStat.index+2].description}"></p>
                    </div>
                </div>
            </div>
            <div  th:if="${#lists.isEmpty(imagesPage)}" th:text="#{photo.notFound}"></div>
            <div th:replace="navigation :: pagination"></div>
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
<script src="/js/photo.js"></script>
</body>
</html>
