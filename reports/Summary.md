**Отчет по итогам автоматизации**

__Что было запланировано?__

Было запланировано 34 тестов (34 UI теста, 2 теста на взаимодействие с БД).

__Что было сделанно?__

Автоматизировано 37 тестов, так как в ходе написания автотестов было выявлено, что при вводе значения 00 и текущего года, отображается ошибка "неверно указан срок действия", но в случае с будущим годом форма принимает значение месяца 00.
Предполаголось, что будет сгенерирован отчет Allure, но из-за существующей проблемы взаимодействия новых версий плагина Allure с gradle, был использован отчет Gradle. 

**Сработавшие риски**

Потребовалось определенное время на поиск решений некоторых проблем, в частности, причин вышеуказанной ошибки Allure report.

**Общий итог по времени**

1. Планирование - 1 час 
2. Написание автотестов - 4 дня по 3-4 часа (~16 ч). Быстрее, чем запланировано, так как UI тесты однотипные. Тест базы данных занял больше времени.
3. Составление отчетности - 4 часа. Больше времени было затрачено в связи с поиском причины проблемы с отчетом Allure и соответствующие модификицаии с проектом. 
4. Создание issues на gitHub - 1 час.

**Итого**: 24 часа (подготовка и запуск SUT: 3 часа, планирвание, автоматизация, отчетность: 21 час)


