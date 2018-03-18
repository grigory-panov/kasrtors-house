<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head th:replace="admin/head">
</head>
<body>
<div th:replace="admin/menu :: menu"></div>

<div class="container admin-container">
    <form th:action="@{/admin/settings.html}" th:object="${settings_object}" method="post" class="form-vertical">
        <input type="hidden" th:field="*{key}"/>

        <div class="form-group">
            <label for="value" th:text="${key}"></label>
            <input type="text" th:field="*{value}" class="form-control"
                   placeholder=""/>
        </div>
        <div class="form-group">
            <label for="description" th:text="#{form.settings.description}">

            </label>
            <input type="text" th:field="*{description}" class="form-control" name="description"
                   placeholder=""/>
        </div>
        <div class="form-group">
            <button type="submit" id="submitButton" class="btn btn-primary" th:text="#{form.button.save}">
                save
            </button>
        </div>
    </form>


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
