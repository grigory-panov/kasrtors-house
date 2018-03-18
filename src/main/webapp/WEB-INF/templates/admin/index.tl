<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head th:replace="admin/head">
</head>
<body>
<div th:replace="admin/menu :: menu"></div>

<div class="container admin-container">
    <a href="#" th:text="#{admin.index.add}" th:href="@{/admin/article.html}" class="btn btn-default">add</a>
    <table  class="table" th:unless="${#lists.isEmpty(articles)}">
        <thead>
        <tr>
            <th th:text="#{admin.index.id}">id</th>
            <th th:text="#{admin.index.link}">link</th>
            <th th:text="#{admin.index.header}">header</th>
            <th th:text="#{admin.index.delete}">delete</th>
        </tr>
        </thead>
        <tbody>
        <tr th:each="sb : ${articles}">
            <td th:text="${sb.id}">1</td>
            <td th:text="${'/article/'+ sb.page}">article.html</td>
            <td><a href="#" th:text="${sb.header}" th:href="${'/admin/article.html?id=' + sb.id}">header</a></td>
            <td><a href="#"  >delete</a></td>
        </tr>
        </tbody>
    </table>

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


