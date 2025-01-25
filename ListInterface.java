package edu.aueb.ds.lab4;

import edu.aueb.ds.exceptions.EmptyListException;

import java.util.Comparator;

/**
 * Extends lab3.ListInterface to be able to sort items
 *
 * @param <T> The type of items in the list.
 */
public interface ListInterface<T> {
    /**
     * In order to sort the list we will use a comparator provided by the user
     * https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html
     */

    /**
     * Inserts the data at the front of the list
     *
     * @param data the inserted data
     */
    void insertAtFront(T data);

    /**
     * Inserts the data at the end of the list
     *
     * @param data the inserted item
     */
    void insertAtBack(T data);

    /**
     * Returns and removes the data from the list head
     *
     * @return the data contained in the list head
     * @throws EmptyListException if the list is empty
     */
    T removeFromFront() throws EmptyListException;

    /**
     * Returns and removes the data from the list tail
     *
     * @return the data contained in the list tail
     * @throws EmptyListException if the list is empty
     */
    T removeFromBack() throws EmptyListException;

    /**
     * Determine whether list is empty
     *
     * @return true if list is empty
     */
    boolean isEmpty();

    /**
     * Sorts the list
     */
    void sort(Comparator<T> comparator);
}
