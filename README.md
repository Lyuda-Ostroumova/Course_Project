**Курсовой проект по модулю «Автоматизация тестирования»**

![](pic/service.png)

Приложение предлагает купить тур по определённой цене с помощью двух способов:
1.	Обычная оплата по дебетовой карте
2.	Уникальная технология: выдача кредита по данным банковской карты
Само приложение не обрабатывает данные по картам, а пересылает их банковским сервисам:

•	сервису платежей (далее - Payment Gate)

•	кредитному сервису (далее - Credit Gate)

**Запуск SUT и автотестов:**

1.	Клонировать репозиторий командой git clone
2.	Запустить docker
3.	Открыть проект в IntelliJ IDEA Ultimate
4.	В терминале IDEA запустить контейнер командой docker-compose up
5.	Запустить jar файл командой java -jar “artifacts\aqa-shop.jar”
6.	Открыть в браузере адрес localhost:8080
7.	Запустить автотесты командой ./gradlew clean test
