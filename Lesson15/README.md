Web pet clinic. Lesson15 + Lesson16 + Lesson17 + Lesson18 + Lesson19
========================

Веб-клиника домашних животных.
------------------------------------
По функциональности аналогична [консольной клинике](https://github.com/Bikmop/Java_study/tree/master/PetClinic_2).

Lesson15:
------------------------------------
Обзор клиники:
![CC0](https://github.com/Bikmop/Java_study/blob/master/Lesson15/DemoInterface/15_Clinic.png)

Добавление клиента:
![CC0](https://github.com/Bikmop/Java_study/blob/master/Lesson15/DemoInterface/15_AddClient.png)

Редактирование клиента: 
![CC0](https://github.com/Bikmop/Java_study/blob/master/Lesson15/DemoInterface/15_EditClient.png)

Демонстрация работы:
![CC0](https://github.com/Bikmop/Java_study/blob/master/Lesson15/DemoInterface/15_demo.png)

Lesson16:
------------------------------------
Добавлены тесты сервлетов + мелкий рефакторинг.
Покрытие тестами:
![CC0](https://github.com/Bikmop/Java_study/blob/master/Lesson15/DemoInterface/16_JaCoCo.png)

Lesson17:
------------------------------------
Изменена структура JSP (верстка таблицами на верстку div-ами) без изменения функционала.
Добавлены стили CSS. Добавлена валидация(с помощью JS/JQuery) полей ввода веб-интерфейса.

Обзор клиники:
![CC0](https://github.com/Bikmop/Java_study/blob/master/Lesson15/DemoInterface/17_Clinic.jpg)
Поиск клиентов:
![CC0](https://github.com/Bikmop/Java_study/blob/master/Lesson15/DemoInterface/17_Clinic_search.jpg)


Добавление клиента:
![CC0](https://github.com/Bikmop/Java_study/blob/master/Lesson15/DemoInterface/17_add_client.jpg)
Валидация:
![CC0](https://github.com/Bikmop/Java_study/blob/master/Lesson15/DemoInterface/17_add_client_id_verify.jpg)

Редактирование клиента: 
![CC0](https://github.com/Bikmop/Java_study/blob/master/Lesson15/DemoInterface/17_edit_client.jpg)
Валидация:
![CC0](https://github.com/Bikmop/Java_study/blob/master/Lesson15/DemoInterface/17_add_pet_name_verify.jpg)
![CC0](https://github.com/Bikmop/Java_study/blob/master/Lesson15/DemoInterface/17_change_client_name_verify.jpg)

Lesson18:
------------------------------------
Создана PostgreSQL база данных "pet_clinic" для хранения информации о клинике.
Таблицы: clients - клиенты, pets - домашние животные, pet_types - типы животных.

Обзор БД:
![CC0](https://github.com/Bikmop/Java_study/blob/master/Lesson15/DemoInterface/18_PostgreSQL.jpg)

Lesson19:
------------------------------------
Создана клиника на основе базы данных PostgreSQL с использованием JDBC - JdbcClinic.
Функционал аналогичный клинике из пакета SE. Логика работы сервлетов не изменена (только мелкий рефакторинг).
Для этого добавлен интерфейс PetClinic, который должны реализовывать клиники использующиеся в веб-приложении.
Выбор реализации клиники между SeClinic и JdbcClinic осуществляется одним выражением в ClinicSingleton.

Отображение всех клиентов базы:
![CC0](https://github.com/Bikmop/Java_study/blob/master/Lesson15/DemoInterface/19_All_clients.jpg)

Фильтры поиска:
![CC0](https://github.com/Bikmop/Java_study/blob/master/Lesson15/DemoInterface/19_Search_filters.jpg)

Обзор клиента:
![CC0](https://github.com/Bikmop/Java_study/blob/master/Lesson15/DemoInterface/19_Edit_client.jpg)

------------------------------------
Также, переделыны тесты сервлетов (под любую реализацию клиники) и добавлены тесты новых классов.

Покритие в IDEA:
![CC0](https://github.com/Bikmop/Java_study/blob/master/Lesson15/DemoInterface/19_IDEA_coverage.jpg)

JaCoCo (непокрытый код тот же, но % сильно отличаются):
![CC0](https://github.com/Bikmop/Java_study/blob/master/Lesson15/DemoInterface/19_JaCoCo_coverage.jpg)
