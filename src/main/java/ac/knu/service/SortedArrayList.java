package ac.knu.service;

public class SortedArrayList<T extends Comparable<T>>
{
    private T[] items;
    private int size;
    private int maxSize;

    public SortedArrayList()
    {
        size = 0;
        maxSize = 10;
        items = (T[]) new Comparable[maxSize];
    }
    public boolean isEmpty()
    {
        return size == 0;
    }
    public boolean isFull()
    {
        return size == maxSize;
    }
    public void insert(T item)
    {
        if (isFull())
        {
            return;
        }
        int i, j;
        for (i = 0; i < size; i++)
        {
            if (items[i].compareTo(item) > 0)
                break;
        }
        for (j = size - 1; j >= i; j--)
        {
            items[j+1] = items[j];
        }
        items[i] = item;
        size++;
    }
    public boolean remove(T item)
    {
        if (isEmpty())
        {
            throw new java.util.NoSuchElementException("remove(): list empty");
        }
        int i, j;
        for (i = 0; i < size; i++)
        {
            if (items[i].compareTo(item) == 0)
            {
                for (j = i + 1; j < size; j++)
                {
                    items[j-1] = items[j];
                }
                size--;
                return true;
            }
            else if (items[i].compareTo(item) > 0)
            {
                return false;
            }
        }
        return false;
    }
    public boolean search(T data)
    {
        int mid, low = 0, high = size - 1;
        while (low <= high)
        {
            mid = (low + high) / 2;
            if (items[mid].compareTo(data) > 0)
            {
                high = mid - 1;
            }
            else if (items[mid].compareTo(data) < 0)
            {
                low = mid + 1;
            }
            else
                return true;
        }
        return false;
    }
    public String print()
    {
        return items[size].toString();
    }
}