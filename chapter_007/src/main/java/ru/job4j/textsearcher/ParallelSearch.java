package ru.job4j.textsearcher;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for multithreaded text search and saving
 * file path with matched text.
 *
 * @since 02/10/2017
 * @version 1
 */
@ThreadSafe
public class ParallelSearch {

    /**
     * Start folder for search.
     */
    private final String root;

    /**
     * Test for search in files.
     */
    private final String text;

    /**
     * File extensions for search.
     */
    private final List<String> exts;

    /**
     * Contains paths to files with needed text.
     */
    @GuardedBy("this")
    private final List<String> results = new ArrayList<>();

    /**
     * Thread pool for search tasks.
     */
    private final ExecutorService executorService;

    /**
     * Constructor.
     *
     * @param root start folder for search
     * @param text for search in files
     * @param exts file extensions for search
     */
    public ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
        executorService = Executors.newCachedThreadPool();
    }

    /**
     * Initializes search.
     */
    public void go() {
        search(new File(root));
    }

    /**
     * Searches files with matched text and extensions
     * and adds their names to result list.
     *
     * @param rootFile start point for search.
     */
    private void search(File rootFile) {

        // попробовал рекурсивный обход дерева вложенных файлов и папок,
        // но он не подходит т.к. невозможно определить конец
        // поиска чтобы вызвать shutdown() для executorService
        //
//        if (rootFile.isFile()) {
//            executorService.submit(new SearchTask(rootFile));
//        } else if (rootFile.isDirectory()) {
//            for (File file : rootFile.listFiles()) {
//                search(file);
//            }
//        }

        // ...поэтому будем использовать очередь и нерекурсивное решение,
        // тогда размер очереди (== 0) будет указывать на конец
        // поиска и можно вызывать shutdown(), а в вызове
        // результатов сделать блокировку на isTerminated()

        Queue<File> queue = new ArrayDeque<>();
        queue.add(rootFile);
        while(queue.size() != 0) {
            File file = queue.poll();
            if (file.isFile()) {
                executorService.submit(new SearchTask(file));
            } else if (file.isDirectory()) {
                for (File xFile : file.listFiles()) {
                    queue.add(xFile);
                }
            }
        }
        executorService.shutdown();
    }

    /**
     * Class is search task for threads.
     */
    private class SearchTask implements Runnable {

        /**
         * File for search.
         */
        private File file;

        public SearchTask(File file) {
            this.file = file;
        }

        @Override
        public void run() {
            searchText(file);
        }

        /**
         * Tests file extension and searches text in it.
         *
         * @param file to search text in.
         * @return true if file contains text
         */
        private boolean searchText(File file) {
            boolean result = false;

            // получаем расширение
            String fileExt = getFileExtension(file);

            // проверяем расширение
            if (exts.contains(fileExt)) {

                // читаем содержимое файла...
                Path filePath = file.toPath();
                StringBuilder sb = null;
                try (BufferedReader bf = Files.newBufferedReader(filePath)) {
                    sb = new StringBuilder();
                    String s;
                    while((s = bf.readLine()) != null) {
                        sb.append(s);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Can't read file: " + file.toString());
                }

                // ...и проверяем на совпадение с заданным текстом
                if (sb != null) {
                    Pattern p = Pattern.compile(".*" + text + ".*");
                    Matcher m = p.matcher(sb);
                    result = m.matches();
                    if (result) {
                        addToResults(file.getPath());
                    }
                }
            }
            return result;
        }

        /**
         * Gets file extension.
         *
         * @param file ext to find
         * @return extension
         */
        private String getFileExtension(File file) {
            String fileName = file.getName();
            String result = null;
            int index = fileName.lastIndexOf(".");
            if (index != -1 && index != 0) {
                result = fileName.substring(index + 1);
            }
            return result;
        }
    }

    /**
     * Adds file path to results in thread safe mode.
     * @param filePath to add
     */
    private synchronized void addToResults(String filePath) {
        results.add(filePath);
    }

    /**
     * Returns list of results of search.
     * @return results of search
     */
    public List<String> result() {

        // ждем, пока все потоки не отработают,
        // чтобы вернуть результат
        while(!executorService.isTerminated()) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}
