<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">

<body>
<nav th:fragment="top" class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" th:href="@{/index.html}">
                <img alt="Brand" th:src="@{/img/brand.png}"/><span th:text="#{template.club}">club</span></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a th:href="@{/heeler.html}" th:text="#{template.heeler}">heeler</a></li>
                <li><a th:href="@{/cadebou.html}" th:text="#{template.cadebou}">kadebo</a></li>
                <li><a th:href="@{/minibull.html}" th:text="#{template.minibull}">minibull</a></li>
            </ul>
        </div>
    </div>
</nav>

<p th:fragment="navbutton" class="pull-right visible-xs">
     <button type="button" th:text="#{template.navigation}" class="btn btn-primary btn-xs" data-toggle="offcanvas">navigation</button>
</p>

<!-- Main jumbotron for a primary marketing message or call to action -->
<div th:fragment="jumbotron" class="jumbotron header margin50">
    <div class="container">
        <h2 th:text="#{template.club.name}">club name</h2>

        <p><img alt="FCI RKS" th:src="@{/img/logo.png}"></img></p>

        <p th:text="#{template.club.full}">club</p>
    </div>
</div>
<div th:fragment="jumbotron-heeler" class="jumbotron header-heeler margin50">
    <div class="container">
        <h2 th:text="#{template.club.name}">club name</h2>

        <p><img alt="FCI RKS" th:src="@{/img/logo.png}"></img></p>

        <p th:text="#{template.club.full}">club</p>
    </div>
</div>
<div th:fragment="jumbotron-minibull" class="jumbotron header-minibull margin50">
    <div class="container">
        <h2 th:text="#{template.club.name}">club name</h2>

        <p><img alt="FCI RKS" th:src="@{/img/logo.png}"></img></p>

        <p th:text="#{template.club.full}">club</p>
    </div>
</div>
<div th:fragment="jumbotron-cadebou" class="jumbotron header-cadebou margin50">
    <div class="container">
        <h2 th:text="#{template.club.name}">club name</h2>

        <p><img alt="FCI RKS" th:src="@{/img/logo.png}"></img></p>

        <p th:text="#{template.club.full}">club</p>
    </div>
</div>


<div th:fragment="left" class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
    <div class="list-group">
        <a th:href="@{/photo.html?club=true}" class="list-group-item" th:text="#{navigation.right.photo.in}">description</a>
        <a th:href="@{/photo.html?club=false}" class="list-group-item" th:text="#{navigation.right.photo.out}">description</a>
        <a th:href="@{/news.html}" class="list-group-item" th:text="#{navigation.right.news}">description</a>
        <a th:href="@{/article/astro_dogs.html}" class="list-group-item" th:text="#{navigation.right.article.astrodog}">description</a>
        <a th:href="@{/article/animals.html}" class="list-group-item" th:text="#{navigation.right.article.animals}">description</a>
        <a th:href="@{/article/design.html}" class="list-group-item" th:text="#{navigation.right.article.design}">description</a>
        <a th:href="@{/contacts.html}" class="list-group-item" th:text="#{navigation.right.contacts}">description</a>
    </div>
</div>


    <nav th:fragment="pagination" th:unless="${#lists.isEmpty(pages)}">
        <ul class="pagination">
            <li th:class="${currentPage == 1 ? 'disabled' : ''}">
                <a th:href="${currentPage == 1 ? '' : pages[currentPage - 2].url}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <div th:each="page : ${pages}" th:remove="tag">
                <li th:class="${page.number == currentPage ? 'active' : ''}">
                    <a th:href="${page.url}"
                       th:text="${page.number}">1</a>
                </li>
            </div>
            <li th:class="${currentPage == #lists.size(pages) ? 'disabled' : ''}">
                <a th:href="${currentPage == #lists.size(pages) ? '' : pages[currentPage].url}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>

    <footer th:fragment="footer">
        <p>&copy; <span th:text="#{template.club.name}">club name</span> 2015-2017</p>
    </footer>
</body>
</html>
