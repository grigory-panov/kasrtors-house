<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head th:replace="admin/head">
</head>
<body>
<div th:replace="admin/menu :: menu"></div>

<div class="container admin-container">
    <form action="#" th:action="@{/admin/article.html}" th:object="${article}" method="post" class="form-vertical">
        <div class="form-group">
            <label for="header" th:text="#{form.article.header}">Header</label>
            <input type="text" th:field="*{header}" required="true" th:errorclass="" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="text" th:text="#{form.article.text}">Text</label>
            <textarea th:field="*{text}" required="required" class="markdown-textarea form-control" th:errorclass="" rows="10"/>
        </div>
        <div class="form-group">
            <label for="page" th:text="#{form.article.page}">Page</label>
            <input type="text" th:field="*{page}" required="true" th:errorclass="" class="form-control"/>
            <input type="hidden" th:field="*{id}"/>
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
<script src="//code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/main.js"></script>
<script src="/js/jquery.scrollto.js"></script>
<script src="/js/pagedown.js"></script>
<script src="/js/pagedown-extra.js"></script>
<script src="/js/diff.js"></script>
<script src="/js/editor.js"></script>
</body>
</html>