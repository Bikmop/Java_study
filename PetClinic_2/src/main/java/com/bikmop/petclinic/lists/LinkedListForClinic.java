package com.bikmop.petclinic.lists;

import java.util.*;

/**
 * Реализация упрощенного динамического списка на основе связанного списка
 * для использования в клинике домашних животных
 * @param <E> Тип-параметр
 */
public class LinkedListForClinic<E> extends AbstractSequentialList<E> implements List<E> {
    /**
     * Формат сообщения для исключения при обращении к неправильному индексу
     */
    private final String OUT_OF_BOUND_EXCEPTION_FORMAT = "Error index: %d";

    /**
     * Количество элементов в списке
     */
    private int size;

    /**
     * Первый элемент списка
     */
    private Node<E> first;

    /**
     * Последний элемент списка
     */
    private Node<E> last;


    /**
     * Добавить элемент в конец списка
     * @param element Элемент
     * @return Возвращает успешность добавления элемента
     */
    public boolean add(E element) {
        addToEnd(element);
        return true;
    }

    /**
     * Вставка элемента на указанное место (индекс) в списке перед элементом с индексом index
     * @param index Индекс ячейки для добавления
     * @param element Элемент
     */
    public void add(int index, E element) {
        checkIndexAdd(index);

        if (index == this.size)
            addToEnd(element);
        else
            addBefore(element, getNode(index));
    }

    /**
     * Проверка списка на наличие объекта
     * @param o Объект поиска
     * @return Наличие объекта
     */
    public boolean contains(Object o) {
        boolean result;

        if (o == null) {
            result = hasNull();
        } else {
            result = hasElement(o);
        }

        return result;
    }

    /**
     * Очистка списка
     */
    public void clear() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    /**
     * Поместить новый элемент на заданную ячейку списка
     * @param index Номер ячейки
     * @param element Элемент
     * @return Старое значение
     */
    public E set(int index, E element) {
        checkIndexGet(index);

        Node<E> current = getNode(index);
        E oldValue = current.element;
        current.element = element;

        return oldValue;
    }

    /**
     * Удалить объект из списка
     * @param o Объект
     * @return Успешность удаления(наличия)
     */
    public boolean remove(Object o) {
        boolean result;

        if (o == null) {
            result = isNullRemoved();
        } else {
            result = isElementRemoved(o);
        }

        return result;
    }

    /**
     * Удалить объект из списка по заданному индексу
     * @param index Индекс удаляемого объекта
     * @return Значение удаленного объекта
     */
    public E remove(int index) {
        checkIndexGet(index);
        return removeNode(getNode(index));
    }

    /**
     * Получить итератор для списка
     * @return Итератор
     */
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    /**
     * !!! ЛистИтератор не реализовывал
     * @param index Индекс
     * @return null
     */
    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    /**
     * Получить размер списка
     * @return Размер списка
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Получить элемент списка по индексу
     * @param index Индекс
     * @return Элемент списка
     */
    public E get(int index) {
        checkIndexGet(index);
        return getNode(index).element;
    }


    /**
     * Реализация узла-ячейки для двусвязного списка
     * @param <E> Тип-параметр
     */
    private static class Node<E> {
        /** Значение элемента */
        E element;
        /** Следующий узел */
        Node<E> next;
        /** Предыдущий узел */
        Node<E> prev;

        /**
         * Конструктор
         * @param prev Предыдущий узел
         * @param element Значение элемента
         * @param next Следующий узел
         */
        Node(Node<E> prev, E element, Node<E> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * Получить узел по индексу
     * @param index Индекс
     * @return Узел
     */
    private Node<E> getNode(int index) {
        Node<E> current;

        if (index < this.size/2) {
            current = this.first;
            for (int i = 0; i < index; i++)
                current = current.next;
        } else {
            current = this.last;
            for (int i = this.size - 1; i > index; i--)
                current = current.prev;
        }

        return current;
    }

    /**
     * Проверка индекса для операции получения.
     * Должен быть меньше размера списка, иначе бросается IndexOutOfBoundsException
     * @param index Индекс
     */
    private void checkIndexGet(int index) {
        if (index >= this.size || index < 0)
            throw new IndexOutOfBoundsException(String.format(OUT_OF_BOUND_EXCEPTION_FORMAT, index));
    }

    /**
     * Проверка индекса для операции добавления
     * Должен быть не больше размера списка, иначе бросается IndexOutOfBoundsException
     * @param index Индекс
     */
    private void checkIndexAdd(int index) {
        if (index > this.size || index < 0)
            throw new IndexOutOfBoundsException(String.format(OUT_OF_BOUND_EXCEPTION_FORMAT, index));
    }

    /**
     * Реализация простого итератора
     */
    private class LinkedListIterator implements Iterator<E> {
        /**
         * Следующий узел
         */
        private Node<E> next;
        /**
         * Индекс следующего узла
         */
        private int nextIndex;

        /**
         * Конструктор
         */
        public LinkedListIterator() {
            this.next = first;
            this.nextIndex = 0;
        }

        /**
         * Проверка наличия следующего элемента
         * @return Наличие следующего элемента
         */
        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        /**
         * Получить элемент и перейти на следующий
         * @return Элемент
         */
        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();

            Node<E> tmp = next;
            nextIndex++;
            next = next.next;

            return tmp.element;
        }
    }

    /**
     * Добавить элемент перед другим
     * @param element Элемент
     * @param beforeThis Вставка перед этим элементом
     */
    private void addBefore(E element, Node<E> beforeThis) {
        if (beforeThis.prev == null) {
            this.first = new Node<>(null, element, beforeThis);
            beforeThis.prev = this.first;
        } else {
            beforeThis.prev.next = new Node<>(beforeThis.prev, element, beforeThis);
            beforeThis.prev = beforeThis.prev.next;
        }
        this.size++;
    }

    /**
     * Проверка присутствия элемента в списке
     * @param o Искомый объект
     * @return Присутствие
     */
    private boolean hasElement(Object o) {
        boolean result = false;

        for (Node<E> current = this.first; current != null; current = current.next)
            if (o.equals(current.element)) {
                result = true;
                break;
            }

        return result;
    }

    /**
     * Проверка присутствия null в списке
     * @return Присутствие
     */
    private boolean hasNull() {
        boolean result = false;

        for (Node<E> current = this.first; current != null; current = current.next)
            if (current.element == null) {
                result = true;
                break;
            }

        return result;
    }

    /**
     * Добавить элемент в конец списка
     * @param element Элемент
     */
    private void addToEnd(E element) {
        if (this.last == null) {
            this.first = new Node<>(null, element, null);
            this.last = this.first;
        } else {
            this.last.next = new Node<>(this.last, element, null);
            this.last = this.last.next;
        }
        this.size++;
    }

    /**
     * Проверка, удален ли элемент из списка
     * @param o Проверяемый объект
     * @return Успешность удаления
     */
    private boolean isElementRemoved(Object o) {
        boolean result = false;

        for (Node<E> current = this.first; current != null; current = current.next) {
            if (o.equals(current.element)) {
                removeNode(current);
                result = true;
                break;
            }
        }

        return result;
    }

    /**
     * Проверка удаления null из списка
     * @return Успешность удаления
     */
    private boolean isNullRemoved() {
        boolean result = false;

        for (Node<E> current = this.first; current != null; current = current.next) {
            if (current.element == null) {
                removeNode(current);
                result = true;
                break;
            }
        }

        return result;
    }

    /**
     * Удалить заданный узел
     * @param node Узел
     * @return Значение до удаления
     */
    private E removeNode(Node<E> node) {
        E element = node.element;
        Node<E> next = node.next;
        Node<E> prev = node.prev;

        if (prev == null) {
            this.first = next;
        }
        else {
            prev.next = next;
            node.prev = null;
        }

        if (next == null) {
            this.last = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }

        node.element = null;
        this.size--;

        return element;
    }

}
