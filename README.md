# Дипломный проект по профессии Тестировщик #  
**О проекте**  
Проект - это автоматизированное тестирование сервиса по покупке туров двумя способами:  
1.Обычная оплата по дебетовой карте  
2.Выдача кредита по данным банковской карты  
Само приложение не обрабатывает данные по картам, а пересылает их банковским сервисам:  
1.сервису платежей (Payment Gate)    
2.кредитному сервису (Credit Gate)    
Приложение в собственной СУБД  сохраняет информацию о том, успешно ли был совершён платёж
и каким способом.  
**Документация**  
[План автоматизации тестирования](https://github.com/Serg5858/diploma/blob/main/docs/Plan.md)  
[Отчет по итогам тестирования](https://github.com/Serg5858/diploma/blob/main/docs/Report.md)  
[Отчет по итогам автоматизации](https://github.com/Serg5858/diploma/blob/main/docs/Summary.md)   
**Перед началом работы**  
1.Склонировать [репозиторий](https://github.com/Serg5858/diploma) при помощи команды git clone  
2.Установить INTELLI J IDEA(желательно версию Community Edition) по [ссылке](https://www.jetbrains.com/idea/)    
3.Установить Docker, установка по [ссылке](https://github.com/netology-code/aqa-homeworks/blob/master/docker/installation.md)
Открыть Docker Desktop.   
4.Открыть проект в INTELLI J IDEA.  
**Запуск**  
1.Запускаем контейнер с СУБД MySQL, PostgreSQL и Node.js в терминале командой:      
docker-compose up  
2.Запускаем приложение для тестирования в новой вкладке терминала командой:  
для MySQL:   
java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar  
Возможно, кавычки не понадобятся в данной команде(это зависит от операционной системы)  
для PostgreSQL:  
java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar  
Возможно, кавычки не понадобятся в данной команде(это зависит от операционной системы)  
Приложение запускается по адресу http://localhost:8080  
3.Запускаем авто-тесты:  
**Предусловия**  
Для запуска тестов в СУБД MySQL нужно изменить адрес БД  в build.gradle :  
systemProperty 'db.url', System.getProperty('db.url' , 'jdbc:mysql://localhost:3306/app'  
Для запуска тестов в СУБД PostgreSQL нужно изменить адрес БД в build.gradle :  
systemProperty 'db.url', System.getProperty('db.url', 'jdbc:postgresql://localhost:5432/app')  
**Запуск тестов**    
Для СУБД MySQL:  
В новой вкладке терминала ввести команду:  
./gradlew clean test "-Ddatasource.url=jdbc:mysql://localhost:3306/app"  
Для СУБД PostgreSQL:  
В новой вкладке терминала ввести команду:  
./gradlew clean test "-Ddatasource.url=jdbc:postgresql://localhost:5432/app"  
**Генерация отчета Allure**   
Данную команду нужно ввести после прогона всех тестов.     
Для открытия страницы с отчетом в браузере в новой вкладке терминала пишем команду:  
./gradlew allureServe  
Чтобы остановить allureServe нужно использовать сочетание CTRL + C, далее нажать Y для подтверждения
команды.  
**Остановка и удаление контейнеров**  
1.Для остановки контейнеров используем команду в новом окне терминала:  
docker-compose stop  
2.Для удаления контейнеров используем команду в новом окне терминала:  
docker-compose down  













