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
        <h2>
            Контакты
        </h2>
        <address>
            <strong>Кинологичекий питомник Кастор*С Хаус</strong><br/>
            Московская область, Пушкинский район, деревня Зимогорье, СНТ Спартак д. 35 <br/>
            Попова Ольга Александровна<br/>
            тел: 8 (916) 879-05-53
        </address>

        <address>
            <strong>E-mail</strong><br/>
            <a href='mailto:kadebomaster@mail.ru'>kadebomaster@mail.ru</a>
        </address>

        <input class="btn btn-primary" type="button" value="Показать карту" id="toggle"/>
        <div>
            <div id="map" style="width: 400px; height: 300px"></div>
        </div>
        </div>
        <div th:replace="navigation :: left"></div>
    </div>

    <hr/>

    <footer th:replace="navigation :: footer"/>
</div>
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/main.js"></script>
<script src="http://api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript"></script>
<script src="/js/contacts.js"></script>

</body>
</html>