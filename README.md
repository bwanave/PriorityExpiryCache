# Priority Expiry Cache

The PriorityExpiryCache has the following methods that can be invoked:

###### get(String key)

###### put(String key, String value, int priority, int expiry)

###### evict(int currentTime)


### Rules:

1. If an expired item is available. Remove it. If multiple items have the same expiry, removing any one suffices.

2. If condition #1 can't be satisfied, remove an item with the least priority.

3. If more than one item satisfies condition #2, remove the least recently used one.
