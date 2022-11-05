package pro.sky.java.course2.homework216.service;

import pro.sky.java.course2.homework216.exception.ElementNotFound;
import pro.sky.java.course2.homework216.exception.NullArgumentException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private Integer[] arr;
    private int size;
    private boolean sorted;

    public IntegerListImpl(int size) {
        arr = new Integer[size];
        this.size = 0;
        sorted = false;
    }

    @Override
    public void printList() {
        System.out.println(Arrays.toString(arr));
    }

    @Override
    public Integer add(Integer item) {

        validateInteger(item);
        checkArrayIsFull();

        arr[size++] = item;
        if (size > 1 && arr[size - 1] < arr[size - 2]) sorted = false;
        return arr[size - 1];

    }

    @Override
    public Integer add(int index, Integer item) {

        validateInteger(item);
        validateIndex(index);
        checkArrayIsFull();

        for (int i = size; i >= index; i--) {
            arr[i + 1] = arr[i];
        }
        arr[index] = item;
        size++;
        if ((index > 0 && arr[index] < arr[index - 1]) || (index < size - 1 && arr[index] > arr[index + 1]))
            sorted = false;
        return arr[index];
    }

    @Override
    public Integer set(int index, Integer item) {

        validateInteger(item);
        validateIndex(index);

        arr[index] = item;
        if ((index > 0 && arr[index] < arr[index - 1]) || (index < size - 1 && arr[index] > arr[index + 1]))
            sorted = false;
        return arr[index];
    }

    @Override
    public Integer remove(Integer item) {

        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFound();
        } else {
            for (int i = index; i < size; i++) {
                arr[i] = arr[i + 1];
            }
            size--;
            return item;
        }
    }

    @Override
    public Integer removeByIndex(int index) {

        validateIndex(index);

        Integer item = arr[index];
        for (int i = index; i < size; i++) {
            arr[i] = arr[i + 1];
        }
        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        return !(indexOf(item) == -1);
    }

    @Override
    public int indexOf(Integer item) {
        validateInteger(item);
        return binarySearch(item);
    }

    @Override
    public int lastIndexOf(Integer item) {

        validateInteger(item);

        for (int i = size - 1; i > 0; i--) {
            if (arr[i].equals(item)) return i;
        }
        return -1;
    }

    @Override
    public Integer get(int index) {

        validateIndex(index);

        return arr[index];

    }

    @Override
    public boolean equals(IntegerList otherList) {

        if (otherList == null) throw new NullArgumentException();
        if (size != otherList.size()) return false;
        for (int i = 0; i < size; i++) {
            if (!arr[i].equals(otherList.get(i))) return false;
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            arr[i] = null;
        }
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(arr, size);
    }

    private void grow() {
        Integer[] arrTemp = Arrays.copyOf(arr, size);
        arr = Arrays.copyOf(arrTemp, (int) (size * 1.5));
    }

    public void validateInteger(Integer item) {
        if (item == null) throw new NullArgumentException();
    }

    public void validateIndex(int index) {
        if (index > size - 1 || index < 0) throw new IndexOutOfBoundsException();
    }

    public void checkArrayIsFull() {
        if (size >= arr.length) grow();
    }

    private void quickSort(int begin, int end) {

        if (arr.length == 0 || begin >= end) return;

        int middle = (begin + end) / 2;
        int pivot = arr[middle];

        int i = begin, j = end;
        while (i <= j) {
            while (arr[i] < pivot) i++;
            while (arr[j] > pivot) j--;
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

        if (begin < j) quickSort(begin, j);
        if (end > i) quickSort(i, end);
    }

    private int binarySearch(Integer item) {

        if (!sorted) {
            quickSort(0, size - 1);
            sorted = true;
        }

        int min = 0;
        int max = size - 1;

        while (min <= max) {

            int mid = (min + max) / 2;

            if (arr[mid].equals(item)) {
                return mid;
            } else if (arr[mid] > item) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }

    public boolean isSorted() {
        return sorted;
    }
}
