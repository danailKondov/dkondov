package ru.job4j.simpletree;

import java.util.*;

/**
 * Class tree.
 *
 * @since 30/08/2017
 * @version 1
 */
class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    /**
     * Contains elements.
     */
    private Node<E> container;

    /**
     * Counts elements in table.
     */
    private int size = 0;

    /**
     * Inner class is tree's node
     *
     * @param <E> type of value
     */
    class Node<E> {
        List<Node<E>> children;
        E value;

        public Node(E value) {
            this.value = value;
            children = new ArrayList<Node<E>>();
        }
    }

    /**
     * Comparator for node.
     */
    public Comparator<Node<E>> nodeComparator = new Comparator<Node<E>>() {
        @Override
        public int compare(Node<E> o1, Node<E> o2) {
            return o1.value.compareTo(o2.value);
        }
    };

    /**
     * Adds child element to parent element.
     *
     * @param parent parent.
     * @param child child.
     * @return true if operation is successful
     */
    @Override
    public boolean add(E parent, E child) {
        boolean result = false;

        // если дерево пустое, то создаем вершину
        if(container == null) {
            container = new Node<>(parent);
            container.children.add(new Node<>(child));
            size = 2;
            result = true;
        } else {

            // проверяем child на дублирование
            Node<E> childNode = new Node<>(child);
            Node<E> x = null;
            if ((x = searchNode(container, childNode)) == null) {

                // если не дубликат и дерево имеет ветви, то ищем parent
                Node<E> parentToFind = new Node<>(parent);
                Node<E> parentNode = searchNode(container, parentToFind);
                if (parentNode != null) {
                    parentNode.children.add(childNode); // добавляем согласно условию
                    size++;
                    result = true;
                } else {
                    result = false;
                }
            }
        }
        return result;
    }

    /**
     * Adds new element to binary tree.
     *
     * @param e new element
     * @return true if operation successful
     */
    public boolean add (E e) {

        // если дерево пустое, создаем вершину
        if (container == null) {
            container = new Node<>(e);
            container.children.add(null); // left element
            container.children.add(null); // right element
            size++;
            return true;
        } else {

            // если полное, то ищем нужную ветвь
            Node<E> current = container;
            Node<E> parent;
            Node<E> nodeToAdd = new Node<>(e);
            nodeToAdd.children.add(0, null); // left element
            nodeToAdd.children.add(1, null); // right element
            while(true) {
                parent = current;
                if(nodeComparator.compare(current, nodeToAdd) > 0) { // go left
                    current = current.children.get(0);
                    if (current == null) {
                        parent.children.set(0, nodeToAdd);
                        size++;
                        return true;
                    }
                } else if (nodeComparator.compare(current, nodeToAdd) < 0) { // go right
                    current = current.children.get(1);
                    if (current == null) {
                        parent.children.set(1, nodeToAdd);
                        size++;
                        return true;
                    }
                } else if (nodeComparator.compare(current, nodeToAdd) == 0) {
                    return false;
                }
            }
        }
    }

    /**
     * Tests if tree is binary.
     *
     * @return true if tree is binary
     */
    public boolean isBinary() {
        // рекурсивное решение
//        return isBinary(container);

        // решение с использованием цикла и очереди
        Queue<Node<E>> queue = new ArrayDeque<>();
        queue.add(container);
        while (queue.size() != 0) {
            Node<E> x = queue.remove();
            if(x.children.size() > 2) {
                return false;
            }
//            queue.addAll(x.children);
            for (Node<E> y : x.children) {
                if (y != null) {
                    queue.add(y);
                }
            }
        }
        return true;
    }

    /**
     * Tests if tree is binary.
     *
     * @param node to test
     * @return true if tree is binary
     */
    private boolean isBinary(Node<E> node) {
        if(node.children.size() > 2) {   // проверяем на бинарность
            return false;
        }
        if (node.children.size() > 0) {
            for (Node<E> childNode : node.children) { // проверяем на бинарность потомков
                if (!isBinary(childNode)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Searches node.
     *
     * @param container where to search
     * @param parent what to search
     * @return parent node or null if absent
     */
    private Node<E> searchNode(Node<E> container, Node<E> parent) {

        // если parent - это корневой нод
        if(nodeComparator.compare(container, parent) == 0) {
            return container;
        }

        // если parent - остальные ноды
        for (Node<E> node : container.children) {
            if (nodeComparator.compare(node, parent) == 0) { // проверка согласно условию
                return node;
            } else if (node.children.size() > 0) {
                Node<E> parentNode = null;
                if((parentNode = searchNode(node, parent)) != null) {    //рекурсия
                    return parentNode;
                }
            }
        }
        return null; // выходим из рекурсии (с null) если у нода нет parent и нет children
    }

    /**
     * Returns iterator for this tree.
     *
     * Реализация итератора строится на идее итератора итераторов:
     * поскольку все ноды содержат своих детей в списках, то мы собираем итераторы этих списков в
     * отдельный список с помощью метода getAllIterators() и по очереди итерируем их с помощью next().
     *
     * @return iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            int count = 0;
            Iterator<Node<E>> currentIterator;
            List<Iterator<Node<E>>> iterators;
            Iterator<Iterator<Node<E>>> iteratorOfIterators;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public E next() {
                E result;

                //если первый запрос, то возвращаем значение первого элемента
                if (count == 0) {
                    count++;
                    result = container.value;
                } else {

                    // если не первый, то создаем итератор итераторов
                    // и проходим по нему
                    if(iterators == null) {
                        initializeIterator();
                    }
                    if(currentIterator.hasNext()) {
                        count++;
                        result = currentIterator.next().value;
                    } else {
                        if(iteratorOfIterators.hasNext()) {
                            currentIterator = iteratorOfIterators.next();
                            count++;
                            result = currentIterator.next().value;
                        } else {
                            throw new NoSuchElementException();
                        }
                    }
                }
                return result;
            }

            private void initializeIterator() {
                iterators = new ArrayList<>();
                getAllIterators(container);
                iteratorOfIterators = iterators.iterator();
                currentIterator = iteratorOfIterators.next();
            }

            private void getAllIterators(Node<E> node) {
                if (node.children.size() > 0) {
                    iterators.add(node.children.iterator());
                    for (Node<E> eNode : node.children) {
                        getAllIterators(eNode);
                    };
                }
            }

        };
    }
}
