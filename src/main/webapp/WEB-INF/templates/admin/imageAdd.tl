<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head th:replace="admin/head">
</head>
<body>
<div th:replace="admin/menu :: menu"></div>

<div class="container admin-container">
    <p class="text-danger" th:if="${message}" th:text="${message}"></p>
    <form enctype="multipart/form-data" th:action="@{/admin/imageAdd.html}" method="post"
          class="form-vertical">
        <div class="form-group">
            <label for="file" th:text="#{form.imageAdd.file}">image</label>
            <input type="file" name="file" id="file" required="true"/>
            <img id = "filePreview" class="img-rounded" width="256" height="auto"/>
        </div>

        <div class="form-group">
            <label for="filename" th:text="#{form.imageAdd.filename}">name</label>
            <input type="text" name="filename" id="filename" required="true" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="tag" th:text="#{form.imageAdd.tag}">Tag</label>
            <select name="tag" id="tag" class="form-control">
                <option>cadebou</option>
                <option>cadebou-puppy</option>
                <option>heeler</option>
                <option>heeler-puppy</option>
                <option>minibull</option>
                <option>minibull-puppy</option>
                <option>animals</option>
                <option>other</option>

            </select>
        </div>

        <div class="form-group">
            <label for="header" th:text="#{form.imageAdd.header}">Header</label>
            <input type="text" name="header" id="header" class="form-control"/>
        </div>
        <div class="form-group">
            <input type="checkbox" name="inClub" id="inClub" value="true"/>
            <label for="inClub" th:text="#{form.imageAdd.inClub}">
            </label>
        </div>

        <div class="form-group">
            <label for="description" th:text="#{form.imageAdd.description}">Description</label>
            <input type="text" name="description" id="description" class="form-control"/>
        </div>
        <div class="form-group">
            <button name="save" type="submit" value="1" class="btn btn-primary" th:text="#{form.button.save}">Save
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
<script src="/js/imageAdd.js"></script>
</body>
</html>