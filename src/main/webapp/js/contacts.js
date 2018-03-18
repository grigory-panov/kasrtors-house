ymaps.ready(init);

// Инициализация и уничтожение карты при нажатии на кнопку.
function init () {
    var myMap;
    var contacts_lat=56.112651;
    var contacts_lng=37.879077;
    $('#toggle').bind({
        click: function () {
            if (!myMap) {
                myMap = new ymaps.Map('map', {
                    center: [contacts_lat, contacts_lng], // Московская обл, Пушкинский район, снт Спартак
                    zoom: 13
                });
                myPlacemark = new ymaps.Placemark([contacts_lat, contacts_lng], {
                    // Чтобы балун и хинт открывались на метке, необходимо задать ей определенные свойства.
                    hintContent: "Мы находимся здесь: " + contacts_lat + ", " + contacts_lng
                });
                myMap.geoObjects.add(myPlacemark);
                $("#toggle").attr('value', 'Скрыть карту');
            }
            else {
                myMap.destroy();// Деструктор карты
                myMap = null;
                $("#toggle").attr('value', 'Показать карту снова');
            }
        }
    });
}