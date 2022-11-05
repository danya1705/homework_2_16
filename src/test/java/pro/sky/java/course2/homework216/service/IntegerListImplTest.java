package pro.sky.java.course2.homework216.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.java.course2.homework216.exception.ElementNotFound;
import pro.sky.java.course2.homework216.exception.NullArgumentException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class IntegerListImplTest {

    public static final int TEST_ARRAY_SIZE = 10;

    IntegerListImpl integerList = new IntegerListImpl(TEST_ARRAY_SIZE);

    @BeforeEach
    void checkArrayIsClear() {
        assertThat(integerList.size()).isEqualTo(0);
    }

    @AfterEach
    void clearStringList() {
        integerList.clear();
    }

    @Test
    void shouldReturnNullArgumentExceptionWhenValidateInteger() {
        assertThatExceptionOfType(NullArgumentException.class).isThrownBy(() -> integerList.validateInteger(null));
    }

    @Test
    void shouldReturnIndexOutOfBoundsExceptionWhenValidateIndex() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> integerList.validateIndex(-1));
        assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> integerList.validateIndex(integerList.size()));
    }

    @Test
    void shouldReturnElementNotFoundWhenRemove() {

        integerList.add(666);
        assertThat(integerList.size()).isEqualTo(1);
        assertThat(integerList.get(0)).isEqualTo(666);

        assertThatExceptionOfType(ElementNotFound.class).isThrownBy(() -> integerList.remove(999));
    }

    @Test
    void addPositiveTest() {
        integerList.add(666);
        assertThat(integerList.size()).isEqualTo(1);
        assertThat(integerList.get(0)).isEqualTo(666);
    }

    @Test
    void addToPositionPositiveTest() {
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        assertThat(integerList.size()).isEqualTo(3);
        integerList.add(2, 10);
        assertThat(integerList.size()).isEqualTo(4);
        Integer[] expected = new Integer[]{1, 2, 10, 3};
        assertThat(integerList.toArray()).isEqualTo(expected);
    }

    @Test
    void setPositiveTest() {
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        assertThat(integerList.size()).isEqualTo(3);
        integerList.set(1, 22);
        assertThat(integerList.size()).isEqualTo(3);
        Integer[] expected = new Integer[]{1, 22, 3};
        assertThat(integerList.toArray()).isEqualTo(expected);
    }

    @Test
    void removePositiveTest() {
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        assertThat(integerList.size()).isEqualTo(3);
        integerList.remove(2);
        assertThat(integerList.size()).isEqualTo(2);
        Integer[] expected = new Integer[]{1, 3};
        assertThat(integerList.toArray()).isEqualTo(expected);
    }

    @Test
    void RemoveByIndexPositiveTest() {
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        assertThat(integerList.size()).isEqualTo(3);
        integerList.removeByIndex(1);
        assertThat(integerList.size()).isEqualTo(2);
        Integer[] expected = new Integer[]{1, 3};
        assertThat(integerList.toArray()).isEqualTo(expected);
    }

    @Test
    void containsPositiveTest() {
        integerList.add(666);
        assertThat(integerList.contains(666)).isEqualTo(true);
    }

    @Test
    void indexOfPositiveTest() {
        integerList.add(666);
        integerList.add(666);
        assertThat(integerList.indexOf(666)).isEqualTo(0);
        assertThat(integerList.indexOf(999)).isEqualTo(-1);
    }

    @Test
    void lastIndexOfPositiveTest() {
        integerList.add(666);
        integerList.add(666);
        assertThat(integerList.lastIndexOf(666)).isEqualTo(1);
        assertThat(integerList.lastIndexOf(999)).isEqualTo(-1);
    }

    @Test
    void getPositiveTest() {
        integerList.add(666);
        assertThat(integerList.get(0)).isEqualTo(666);
    }

    @Test
    void testEqualsPositiveTest() {
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);

        IntegerList expected = new IntegerListImpl(3);
        expected.add(1);
        expected.add(2);
        expected.add(3);
        assertThat(integerList.equals(expected)).isEqualTo(true);

        expected.set(0, 10);
        assertThat(integerList.equals(expected)).isEqualTo(false);
    }

    @Test
    void sizePositiveTest() {
        integerList.add(666);
        assertThat(integerList.size()).isEqualTo(1);
        integerList.add(666);
        integerList.add(666);
        assertThat(integerList.size()).isEqualTo(3);
    }

    @Test
    void isEmptyPositiveTest() {
        assertThat(integerList.isEmpty()).isEqualTo(true);
        integerList.add(666);
        assertThat(integerList.isEmpty()).isEqualTo(false);
    }

    @Test
    void clearPositiveTest() {
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        assertThat(integerList.toArray()).isNotEmpty();
        integerList.clear();
        assertThat(integerList.toArray()).isEmpty();
        assertThat(integerList.size()).isEqualTo(0);
    }

    @Test
    void toArrayPositiveTest() {
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        Integer[] expected = new Integer[]{1, 2, 3};
        assertThat(integerList.toArray()).isEqualTo(expected);
    }

    @Test
    void growTest() {

        IntegerList growingList = new IntegerListImpl(TEST_ARRAY_SIZE);

        for (int i = 0; i < TEST_ARRAY_SIZE; i++) {
            growingList.add(666);
        }
        growingList.add(666);
        assertThat(growingList.size()).isGreaterThan(TEST_ARRAY_SIZE);
    }

//    @Test
//    void quickSortTest() {
//        integerList.add(11);
//        integerList.add(32);
//        integerList.add(365);
//        integerList.add(28);
//        integerList.add(3);
//        integerList.quickSort(0, integerList.size() - 1);
//        Integer[] expected = new Integer[]{3, 11, 28, 32, 365};
//        assertThat(integerList.toArray()).isEqualTo(expected);
//    }

    @Test
    void checkSortedFlagWhenAdd() {

        integerList.add(1);
        integerList.add(3);
        integerList.add(5);
        integerList.indexOf(3);
        assertThat(integerList.isSorted()).isEqualTo(true);

        integerList.add(6);
        assertThat(integerList.isSorted()).isEqualTo(true);

        integerList.add(4);
        assertThat(integerList.isSorted()).isEqualTo(false);
    }

    @Test
    void checkSortedFlagWhenAddToPosition() {

        integerList.add(1);
        integerList.add(3);
        integerList.add(5);
        integerList.indexOf(3);
        assertThat(integerList.isSorted()).isEqualTo(true);

        integerList.add(0, 0);
        integerList.add(2, 2);
        assertThat(integerList.isSorted()).isEqualTo(true);

        integerList.add(1, 10);
        assertThat(integerList.isSorted()).isEqualTo(false);
    }

    @Test
    void checkSortedFlagWhenSet() {

        integerList.add(1);
        integerList.add(3);
        integerList.add(5);
        integerList.indexOf(3);
        assertThat(integerList.isSorted()).isEqualTo(true);

        integerList.set(0, 0);
        integerList.set(1, 2);
        integerList.set(2, 4);
        assertThat(integerList.isSorted()).isEqualTo(true);

        integerList.set(1, 10);
        assertThat(integerList.isSorted()).isEqualTo(false);
    }
}