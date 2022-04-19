import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private int capacity;
    private Item[] items;

    // array for storing items
    // random num for index
    // swap random index with last index in array (size-1 = last index
    // remove last (now the random num)
    // construct an empty randomized queue
    public RandomizedQueue() {
        this.size = 0;
        this.capacity = 2;
        this.items = (Item[]) new Object[2];
    }

    private void resize(int newCapacity) {
        Item[] copy = (Item[]) new Object[newCapacity];
        for (int i = 0; i < size; i++)
            copy[i] = items[i];
        items = copy;
        capacity = newCapacity;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;

    }

    // return the number of items on the randomized queue
    public int size() {
        return this.size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (size == capacity) {
            resize(size * 2);
        }

        items[size] = item;
        size++;

    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int i = StdRandom.uniform(size);
        // move the last item in array to the random index;
        // store random index's item
        Item toBeReturned = items[i];
        // move the last item to the randomly selected index
        items[i] = items[size - 1];
        // remove last item from array
        items[size - 1] = null;
        size--;

        // If array is quarter full then resize (And size is greater than 1)
        if (size == capacity / 4 && size > 1) {
            // resize capacity to double current size;

            resize(size * 2);

        }

        return toBeReturned;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return items[StdRandom.uniform(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator<Item>();

    }

    private class RandomIterator<I> implements Iterator<Item> {
        private int i;
        private final int[] randomIndices;

        // creates an array of random indices
        public RandomIterator() {
            i = 0;
            randomIndices = new int[size];
            for (int j = 0; j < size; j++) {
                randomIndices[j] = j;
            }
            StdRandom.shuffle(randomIndices);
        }

        @Override
        public boolean hasNext() {
            return i < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return items[randomIndices[i++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {


        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        StdOut.println("size: " + queue.size());
        queue.enqueue(4);

        StdOut.println("size: " + queue.size());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.isEmpty());
        StdOut.println("size: " + queue.size());
        queue.enqueue(2);



        RandomizedQueue<String> randomQueue = new RandomizedQueue<String>();

        System.out.println(randomQueue.isEmpty());

        randomQueue.enqueue("first");
        randomQueue.enqueue("second");
        randomQueue.enqueue("third");

        System.out.println(randomQueue.isEmpty());

        System.out.println(1);
        for (Object s : randomQueue) {
            System.out.println(s);
        }

        Object removed0 = randomQueue.dequeue();
        System.out.println("removed: " + removed0);

        for (Object s : randomQueue) {
            System.out.println(s);
        }

        System.out.println(randomQueue.sample());


        System.out.println(randomQueue.size());

        Object removed1 = randomQueue.dequeue();
        System.out.println("removed: " + removed1);
        Object removed2 = randomQueue.dequeue();
        System.out.println("removed: " + removed2);

        System.out.println(randomQueue.isEmpty());

        System.out.println(randomQueue.size());

    }

}
