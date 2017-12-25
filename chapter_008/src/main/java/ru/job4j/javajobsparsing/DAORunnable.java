package ru.job4j.javajobsparsing;

import java.util.concurrent.BlockingQueue;

/**
 * Class is task for thread, which starts 'addVacanciesToDB' method.
 *
 * @since 29/11/2017
 * @version 1
 */
public class DAORunnable implements Runnable {
    private ParserDAO dao;
    private BlockingQueue<Vacancy> vacancies;

    public DAORunnable(ParserDAO dao, BlockingQueue<Vacancy> vacancies) {
        this.dao = dao;
        this.vacancies = vacancies;
    }

    @Override
    public void run() {
        dao.addVacanciesToDB(vacancies);
    }
}

