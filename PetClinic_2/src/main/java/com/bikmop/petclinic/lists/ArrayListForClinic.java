package com.bikmop.petclinic.lists;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

/**
 * Реализация упрощенного динамического списка на основе массива для использования в клинике домашних животных
 * @param <E> Тип-параметр
 */
public class ArrayListForClinic<E> extends AbstractList<E> implements List<E>, RandomAccess {
    /**
     * Начальный размер массива
     */
    private final static int INITIAL_CAPACITY = 10;

    /**
     * Шаг увеличения размера массива
     */
    private final static int CAPACITY_INCREMENT = 10;

    /**
     * Формат сообщения для исключения при обращении к неправильному индексу
     */
    private final String OUT_OF_BOUND_EXCEPTION_FORMAT = "Error index: %d";

    /**
     * Количество элементов в списке
     */
    private int size;

    /**
     * Массив для хранения данных
     */
    private E[] mainArray;


    /**
     * Конструктор
     */
    public ArrayListForClinic() {
        init();
    }


    /**
     * Получить элемент списка по индексу
     * @param index Индекс
     * @return Элемент списка
     */
    @Override
    public E get(int index) {
        checkIndexForGet(index);
        return this.mainArray[index];
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
     * Проверка списка на отсутствие элементов
     * @return Пустой ли список
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Очистка списка
     */
    public void clear() {
        init();
    }

    /**
     * Вставка элемента на указанное место (индекс) в списке перед элементом с индексом index
     * @param index Индекс ячейки для добавления
     * @param element Элемент
     */
    public void add(int index, E element) {
        checkIndexForAdd(index);
        if (isNeedIncSize())
            increaseSize();
        System.arraycopy(this.mainArray, index, this.mainArray, index + 1, this.size - index);
        this.mainArray[index] = element;
        this.size++;
    }

    /**
     * Добавить элемент в конец списка
     * @param element Элемент
     * @return Возвращает успешность добавления элемента
     */
    public boolean add(E element) {
        if (isNeedIncSize())
            increaseSize();
        mainArray[this.size++] = element;
        return true;
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
     * Удалить объект из списка по заданному индексу
     * @param index Индекс удаляемого объекта
     * @return Значение удаленного объекта
     */
    public E remove(int index) {
        checkIndexForAdd(index);
        E oldValue = this.mainArray[index];

        int numberToMove = this.size - index - 1;
        if (numberToMove > 0)
            System.arraycopy(this.mainArray, index+1, this.mainArray, index, numberToMove);

        this.size--;
        this.mainArray[this.size] = null;

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
     * Поместить новый элемент на заданную ячейку списка
     * @param index Номер ячейки
     * @param element Элемент
     * @return Старое значение
     */
    public E set(int index, E element) {
        checkIndexForGet(index);

        E old = this.mainArray[index];
        this.mainArray[index] = element;

        return old;
    }

    /**
     * Проверка индекса для операции получения.
     * Должен быть меньше размера списка, иначе бросается IndexOutOfBoundsException
     * @param index Индекс
     */
    private void checkIndexForGet(int index) {
        if (index >= this.size)
            throw new IndexOutOfBoundsException(String.format(OUT_OF_BOUND_EXCEPTION_FORMAT, index));
    }

    /**
     * Проверка, удален ли элемент из списка
     * @param o Проверяемый объект
     * @return Успешность удаления
     */
    private boolean isElementRemoved(Object o) {
        boolean result = false;

        for (int i = 0; i < this.size; i++)
            if (o.equals(this.mainArray[i])) {
                remove(i);
                result = true;
                break;
            }

        return result;
    }

    /**
     * Проверка удаления null из списка
     * @return Успешность удаления
     */
    private boolean isNullRemoved() {
        boolean result = false;

        for (int i = 0; i < this.size; i++)
            if (this.mainArray[i] == null) {
                remove(i);
                result = true;
                break;
            }

        return result;
    }

    /**
     * Проверка присутствия элемента в списке
     * @param o Искомый объект
     * @return Присутствие
     */
    private boolean hasElement(Object o) {
        boolean result = false;

        for (int i = 0; i < this.size; i++)
            if (o.equals(this.mainArray[i])) {
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

        for (int i = 0; i < this.size; i++)
            if (this.mainArray[i] == null) {
                result = true;
                break;
            }

        return result;
    }

    /**
     * Проверка на необходимость увеличения размера внутреннего массива
     * @return Необходимость увеличения
     */
    private boolean isNeedIncSize() {
        return this.size + 1 > this.mainArray.length;
    }

    /**
     * Начальная инициализация списка
     * При создании или очищении
     */
    private void init() {
        this.mainArray = (E[])new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Увеличение внутреннего массива на заданное количество элементов
     */
    private void increaseSize() {
        E[] tmpArray = (E[])new Object[this.size + CAPACITY_INCREMENT];
        System.arraycopy(this.mainArray, 0, tmpArray, 0, this.size);
        this.mainArray = tmpArray;
    }

    /**
     * Проверка индекса для операции добавления
     * Должен быть не больше размера списка, иначе бросается IndexOutOfBoundsException
     * @param index Индекс
     */
    private void checkIndexForAdd(int index) {
        if (index > this.size || index < 0)
            throw new IndexOutOfBoundsException(String.format(OUT_OF_BOUND_EXCEPTION_FORMAT, index));
    }
}
