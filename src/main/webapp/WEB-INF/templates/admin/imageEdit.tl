<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head th:replace="admin/head">
</head>
<body>
<div th:replace="admin/menu :: menu"></div>

<div class="container admin-container">
    <form th:object="${image}" th:action="${'/admin/imageEdit.html?tag=' + image.tag + '&amp;name=' + image.filename}" method="post" class="form-vertical">
        <div class="form-group">
            <img th:src="${image.thumb}" th:alt="${image.header}"/>
        </div>

        <div class="form-group">
            <p th:text="#{form.imageAdd.filename}" class="strong">name</p>
            <p th:text="${image.filename}"></p>
        </div>
        <div class="form-group">
            <p th:text="#{form.imageAdd.tag}" class="strong">tag</p>
            <p th:text="${image.tag}"></p>
        </div>

        <div class="form-group">
            <label for="header" th:text="#{form.imageAdd.header}">Header</label>
            <input type="text" th:field="*{header}" class="form-control"/>
        </div>
        <div class="form-group">
            <input type="checkbox" th:field="*{inClub}"/>
            <label for="inClub" th:text="#{form.imageAdd.inClub}"/>
        </div>

        <div class="form-group">
            <label for="description" th:text="#{form.imageAdd.description}">Description</label>
            <input type="text" th:field="*{description}" class="form-control"/>
        </div>
        <div class="form-group">
            <button name="save" type="submit" value="1" class="btn btn-primary" th:text="#{form.button.save}">Save</button>
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