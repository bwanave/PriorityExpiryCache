package com.example.list;

public class DoublyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    public void addFirst(Node<T> node) {
        if (head == null) this.head = this.tail = node;
        else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
    }

    public void remove(Node<T> node) {
        if (node == head && node == tail) this.head = this.tail = null;
        else if (node == head) head = head.next;
        else if (node == tail) tail = tail.prev;
        else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        node.next = null;
        node.prev = null;
        size--;
    }

    public Node<T> getLast() {
        return tail;
    }
}
