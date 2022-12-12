# Дипломный проект по профессии Тестировщик #  
**О проекте**  
Проект - это автоматизированное тестирование сервиса по покупке туров двумя способами:  
а) Обычная оплата по дебетовой карте   
б) Выдача кредита по данным банковской карты.    
Само приложение не обрабатывает данные по картам, а пересылает их банковским сервисам:  
а) сервису платежей (Payment Gate)    
б) кредитному сервису (Credit Gate)    
Приложение в собственной СУБД  сохраняет информацию о том, успешно ли был совершён платёж
и каким способом.  
**Документация**  
[План автоматизации тестирования](https://github.com/Serg5858/diploma/blob/main/docs/Plan.md)  
[Отчет по итогам тестирования](https://github.com/Serg5858/diploma/blob/main/docs/Report.md)  
[Отчет по итогам автоматизации](https://github.com/Serg5858/diploma/blob/main/docs/Summary.md)   
**Перед началом работы**  
1. Склонировать [репозиторий](https://github.com/Serg5858/diploma) при помощи команды git clone  
2. Установить INTELLI J IDEA(желательно версию Community Edition) по [ссылке](https://www.jetbrains.com/idea/)    
3. Установить Docker, установка по [ссылке](https://github.com/netology-code/aqa-homeworks/blob/master/docker/installation.md)
Открыть Docker Desktop.   
4. Открыть проект в INTELLI J IDEA.  
**Запуск**  
1. Запустить контейнер с СУБД MySQL, PostgreSQL и Node.js в терминале командой:      
`docker-compose up`   
2. Запустить приложение для тестирования в новой вкладке терминала командой:  
для MySQL:   
`java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar`   
(Возможно, кавычки не понадобятся в данной команде(это зависит от операционной системы,в Windows нужны)   
для PostgreSQL:  
`java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar`   
(Возможно, кавычки не понадобятся в данной команде(это зависит от операционной системы,в Windows нужны)    
Приложение запускается по адресу http://localhost:8080  
3. Запустить авто-тесты:  
**Запуск тестов**    
Для СУБД MySQL:  
В новой вкладке терминала ввести команду:  
`./gradlew clean test "-Ddatasource.url=jdbc:mysql://localhost:3306/app"`  
Для СУБД PostgreSQL:  
В новой вкладке терминала ввести команду:  
`./gradlew clean test "-Ddatasource.url=jdbc:postgresql://localhost:5432/app"`    
**Генерация отчета Allure**   
Данную команду нужно ввести после прогона всех тестов.     
Для открытия страницы с отчетом в браузере в новой вкладке терминала написать команду:    
`./gradlew allureServe`   
Чтобы остановить allureServe нужно использовать сочетание CTRL + C, далее нажать Y для подтверждения
команды.  
**Остановка и удаление контейнеров**  
1. Для остановки контейнеров использовать команду в новом окне терминала:  
`docker-compose stop`   
2. Для удаления контейнеров использовать команду в новом окне терминала:  
`docker-compose down` 













