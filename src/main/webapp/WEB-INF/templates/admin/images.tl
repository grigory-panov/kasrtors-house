<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head th:replace="admin/head">
</head>
<body>
<div th:replace="admin/menu :: menu"></div>

<div class="container admin-container">
    <a href="#" th:text="#{admin.images.add}" th:href="@{/admin/imageAdd.html}" class="btn btn-default">add</a>
    <hr/>
    <div th:replace="navigation :: pagination"></div>
    <table  class="table" th:unless="${#lists.isEmpty(imagesPage)}">
        <thead>
        <tr>
            <th th:text="#{admin.images.name}">name</th>
            <th th:text="#{admin.images.inClub}">in club</th>
            <th th:text="#{admin.images.header}">header</th>
            <th th:text="#{admin.images.thumb.url}">thumb</th>
            <th th:text="#{admin.images.full.url}">full</th>
            <th th:text="#{admin.images.thumbnail}">preview</th>
            <th th:text="#{admin.images.delete}">delete</th>
        </tr>
        </thead>
        <tbody>
        <tr th:each="img : ${imagesPage}">
            <td>
                <a th:href="${'/admin/imageEdit.html?tag=' + img.tag + '&amp;name=' + img.filename}" th:text="${img.filename}">name</a>
            </td>
            <td th:text="${img.inClub ? 'Да' : 'Нет'}">in club</td>
            <td th:text="${img.header}">header</td>
            <td th:text="${img.thumb}">thumb</td>
            <td th:text="${img.full}">full</td>

            <td><img th:src="${img.thumb}" src="/img/logo.png"/></td>
            <td><a href="#" th:href="${'/admin/action/images/delete/' + img.tag + '/' + img.filename}">delete</a></td>
        </tr>
        </tbody>
    </table>
    <div th:replace="navigation :: pagination"></div>
    <hr/>
    <footer th:replace="navigation :: footer"/>
</div>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/main.js"></script>
</body>
</html>