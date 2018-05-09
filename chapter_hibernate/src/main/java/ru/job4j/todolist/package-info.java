/**
 * Создать "TO DO" list [#3786]
 *
 * необходимо создать простое приложение todolist.
 * 1. одна таблица в базе item. id, desc. created, done.
 * 2. веб приложение должно иметь одну страницу index.html.
 * 3. все данные на форму загружаються через ajax.
 * 4. в верху форма. добавить новое задание. описание.
 * 5. список всех заданий. и галка выполено или нет.
 * 6. вверху добавить галку. показать все. если включена. то показывать все. если нет. то только те что не выполены done = false.
 * 7. данные должны сохраняться через hibernate
 *
 * Схема БД:
 * create table todotasks (
 * id serial primary key,
 * description text,
 * create_date timestamp,
 * task_is_done boolean
 * );
 *
 * @author Kondov Danail (mailto:dkondov@yandex.ru)
 * @version $1$
 * @since 26.04.2018
 */
package ru.job4j.todolist;