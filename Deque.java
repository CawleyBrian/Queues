import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size = 0;

    // construct an empty deque
    public Deque() {
        // both pointers are empty nodes
        first = new Node();
        last = new Node();
    }

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first.item == null;
    }

    // return the number of items on the deque
    public int size() {
        return this.size;
    }

    // add the item to the front
    public void addFirst(Item item) {

        if (item == null) {
            throw new IllegalArgumentException();
        }

        // Create a new node to be added to the Deque
        Node newNode = new Node();
        newNode.item = item;

        // if Deque is currently empty
        // both pointers to new node without next / prev
        if (this.size == 0) {
            newNode.next = null;
            newNode.prev = null;
            // last will also be first as there will only be 1 item
            this.last = newNode;
        }
        // if 1 item has been added, update last and first pointer before replacing first with new node.
        else if (this.size == 1) {
            newNode.next = this.first;
            newNode.prev = null;
            // last will now point to a node as there are 2 nodes in the Deque
            this.last.prev = newNode;
        }
        // if multiple items already in Deque
        else {
            first.prev = newNode; // current first point to new node
            newNode.next = this.first;
        }
        // replace first with new node
        this.first = newNode;
        this.size++;
    }

    // add the item to the back
    public void addLast(Item item) {

        if (item == null) {
            throw new IllegalArgumentException();
        }

        // Create new node to be added to the Deque
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = null;

        // if Deque is currently empty
        if (this.size == 0) {
            newNode.prev = null;
            // first will also be last as there will only be 1 item
            this.first = newNode;
        }
        else if (this.size == 1) {
            // Replace last and set first pointer to new last
            this.first.next = newNode;
            newNode.prev = first;
        }
        else {
            newNode.prev = this.last;
            this.last.next = newNode;
        }


        // last node is updated to newNode;
        this.last = newNode;
        this.size++;

    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("No items to remove");
        }
        Item toBeReturned = first.item;
        // remove first and last if only 1 element
        if (size == 1) {
            first = null;
            last = null;
        }
        else {
            first = first.next;
            first.prev = null;
        }

        this.size--;
        return toBeReturned;

    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("No items to remove");
        }
        Item toBeReturned = last.item;

        if (size == 1) {
            first = null;
            last = null;
        }
        else {
            last = last.prev;
            last.next = null;
        }

        this.size--;
        return toBeReturned;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator<Item>();
    }

    private class ListIterator<I> implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (hasNext()) {
                Item item = current.item;
                current = current.next;
                return item;
            }
            else throw new NoSuchElementException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        // Create new deque, verify it is empty
        Deque<String> testDeque = new Deque<>();
        System.out.println("Deque should be empty: " + testDeque.isEmpty());
        // use both first and last
        String first = "one";
        testDeque.addFirst(first);
        String second = "two";
        testDeque.addLast(second);
        System.out.println(testDeque.first.item);
        System.out.println(testDeque.first.next.item);
        System.out.println(testDeque.first.prev);

        System.out.println(testDeque.last.item);
        System.out.println(testDeque.last.prev.item);
        System.out.println(testDeque.last.next);

        testDeque.addFirst("third");
        System.out.println("firsts");
        System.out.println(testDeque.first.item);
        System.out.println(testDeque.first.next.item);
        System.out.println(testDeque.first.prev);
        System.out.println("lasts");
        System.out.println(testDeque.last.item);
        System.out.println(testDeque.last.prev.item);
        System.out.println(testDeque.last.next);
        System.out.println(testDeque.size());

        System.out.println("Print list");
        for (String s : testDeque) {
            System.out.println(s);
        }

        String removedFirst = testDeque.removeFirst();
        System.out.println(removedFirst);
        System.out.println(testDeque.size());
        System.out.println(testDeque.first.item);
        System.out.println(testDeque.first.next.item);
        System.out.println(testDeque.first.prev);

        String removedfirst = testDeque.removeLast();
        System.out.println("removed " + removedfirst);
        System.out.println(testDeque.size());
        System.out.println(testDeque.first.item);
        System.out.println(testDeque.last.item);
        System.out.println(testDeque.first.next);
        System.out.println(testDeque.first.prev);
        System.out.println(testDeque.last.prev);
        System.out.println(testDeque.last.next);

        String lastItem = testDeque.removeLast();
        System.out.println("removed " + lastItem);
        System.out.println(testDeque.first);
        System.out.println(testDeque.last);
    }
}
