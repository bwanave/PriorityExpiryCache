package com.example;

import com.example.dtos.Item;
import com.example.list.DoublyLinkedList;
import com.example.list.Node;

import java.util.*;

public class PriorityExpiryCache {
    public final int size;
    private final Map<String, Node<Item>> keyToItemMap;
    private final PriorityQueue<Node<Item>> minExpiryHeap;
    private final PriorityQueue<Node<Item>> minPriorityHeap;
    private final Map<Integer, DoublyLinkedList<Item>> priorityToListMap;

    public PriorityExpiryCache(int size) {
        keyToItemMap = new HashMap<>();
        minExpiryHeap = new PriorityQueue<>(Comparator.comparingInt(node -> node.data.getExpiryTime()));
        minPriorityHeap = new PriorityQueue<>(Comparator.comparingInt(node -> node.data.getPriority()));
        priorityToListMap = new HashMap<>();
        this.size = size;
    }

    public String get(String key) {
        Node<Item> node = keyToItemMap.get(key);
        if (node == null) return null;
        DoublyLinkedList<Item> linkedList = priorityToListMap.get(node.data.getPriority());
        linkedList.remove(node);
        linkedList.addFirst(node);
        return node.data.getValue();
    }

    public void put(String key, String value, int priority, int expiryTime, int currentTime) {
        if (keyToItemMap.size() == size) evict(currentTime);
        Item item = new Item(key, value, priority, expiryTime);
        Node<Item> node = new Node<>(item);
        if (keyToItemMap.containsKey(key)) {
            Node<Item> oldNode = keyToItemMap.get(key);
            remove(oldNode);
        }
        keyToItemMap.put(key, node);
        minExpiryHeap.add(node);
        minPriorityHeap.add(node);
        priorityToListMap.putIfAbsent(priority, new DoublyLinkedList<>());
        priorityToListMap.get(priority).addFirst(node);
    }

    public void evict(int currentTime) {
        if (keyToItemMap.isEmpty()) return;
        Node<Item> node = minExpiryHeap.peek();
        if (currentTime > node.data.getExpiryTime()) {
            remove(node);
            return;
        }
        node = minPriorityHeap.peek();
        node = priorityToListMap.get(node.data.getPriority()).getLast();
        remove(node);
    }

    public Set<String> getKeys() {
        return keyToItemMap.keySet();
    }

    private void remove(Node<Item> node) {
        keyToItemMap.remove(node.data.getKey());
        minExpiryHeap.remove(node);
        minPriorityHeap.remove(node);
        priorityToListMap.get(node.data.getPriority()).remove(node);
    }
}