<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head th:replace="admin/head">
</head>
<body>
<div th:replace="admin/menu :: menu"></div>

<div class="container admin-container">
<table class="table" th:unless="${#lists.isEmpty(settings)}">
    <thead>
    <tr>
        <th th:text="#{admin.settings.key}">key</th>
        <th th:text="#{admin.settings.value}">value</th>
        <th th:text="#{admin.settings.description}">description</th>
    </tr>
    </thead>
    <tbody>
            <tr th:each="o : ${settings}">
                <td><a th:href="${'/admin/settingsEdit.html?key=' + o.key}" th:text="${o.key}" >key</a></td>
                <td th:text="${o.value}">value</td>
                <td th:text="${o.description}">description</td>
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
