package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PriorityExpiryCacheTest {

    @Test
    void test() {
        PriorityExpiryCache priorityExpiryCache = new PriorityExpiryCache(5);
        priorityExpiryCache.put("A", "val1", 5, 100, 0);
        priorityExpiryCache.put("B", "val2", 15, 3, 0);
        priorityExpiryCache.put("C", "val3", 5, 10, 0);
        priorityExpiryCache.put("D", "val4", 1, 15, 0);
        priorityExpiryCache.put("E", "val5", 5, 150, 0);

        Assertions.assertEquals("val3", priorityExpiryCache.get("C"));
        Assertions.assertEquals("[A, B, C, D, E]", priorityExpiryCache.getKeys().toString());

        priorityExpiryCache.evict(5);
        Assertions.assertEquals("[A, C, D, E]", priorityExpiryCache.getKeys().toString());

        priorityExpiryCache.evict(5);
        Assertions.assertEquals("[A, C, E]", priorityExpiryCache.getKeys().toString());

        priorityExpiryCache.evict(5);
        Assertions.assertEquals("[C, E]", priorityExpiryCache.getKeys().toString());

        priorityExpiryCache.evict(5);
        Assertions.assertEquals("[C]", priorityExpiryCache.getKeys().toString());
    }
}