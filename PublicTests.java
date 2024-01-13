// Tests for BinarySearchTree.java
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class PublicTests {
    // Test case for the 'get' method
    @Test
    public void testGet() {
        BinarySearchTree<String, String> tree = createSmallTree();
        // Check if values are retrieved correctly for existing keys
        assertEquals("a", tree.get("A"));
        assertEquals("b", tree.get("B"));
        assertEquals("c", tree.get("C"));
        // Check if null is returned for a non-existing key
        assertNull(tree.get("D"));
    }

    // Test case for the 'countLeaves' method
    @Test
    public void testCountLeaves() {
        BinarySearchTree<String, String> tree = createSmallTree();
        // Check if the correct number of leaves is counted
        assertEquals(2, tree.countLeaves());
    }

    // Test case for the 'getValueForSmallestKey' method
    @Test
    public void testGetValueForSmallestKey() {
        BinarySearchTree<Integer, Integer> tree = createMediumTree();
        // Check if the correct value for the smallest key is retrieved
        assertEquals(Integer.valueOf(1000), tree.getValueForSmallestKey());
    }

    // Test case for the 'reverseLookup' method
    @Test
    public void testReverseLookup() {
        BinarySearchTree<Integer, Integer> tree = createMediumTree();
        // Check if reverse lookup returns correct keys for values in the specified range
        for (int i = 1000; i < 1100; i++) {
            assertEquals(Integer.valueOf(i - 1000), tree.reverseLookup(i));
        }
        // Check if null is returned for a value outside the range
        assertNull(tree.reverseLookup(1200));
    }

    // Test case for the 'put' method
    @Test
    public void testPut() {
        BinarySearchTree<String, String> tree = createSmallTree();
        // Check if updating an existing key works
        assertFalse(tree.put("A", "new_a"));
        // Check if inserting a new key works
        assertTrue(tree.put("D", "d"));
        // Check if update and insert are successful by verifying the values
        assertEquals("new_a", tree.get("A"));
        assertEquals("d", tree.get("D"));
    }

    // Test case for the 'toString' method
    @Test
    public void testToString() {
        BinarySearchTree<String, String> tree = createSmallTree();
        // Check if the string representation is as expected
        assertEquals("{A:a}{B:b}{C:c}", tree.toString());
    }

    // Test case for the 'containsKey' method
    @Test
    public void testContainsKey() {
        BinarySearchTree<String, String> tree = createSmallTree();
        // Check if 'containsKey' returns true for existing key and false for non-existing key
        assertTrue(tree.containsKey("A"));
        assertFalse(tree.containsKey("D"));
    }

    // Test case for the 'containsValue' method
    @Test
    public void testContainsValue() {
        BinarySearchTree<String, String> tree = createSmallTree();
        // Check if 'containsValue' returns true for existing value and false for non-existing value
        assertTrue(tree.containsValue("a"));
        assertFalse(tree.containsValue("x"));
    }

    // Test case for the 'getHeight' method
    @Test
    public void testGetHeight() {
        BinarySearchTree<String, String> tree = createSmallTree();
        // Check if the correct height of the tree is returned
        assertEquals(2, tree.getHeight());
    }

    // Test case for the 'isBalanced' method
    @Test
    public void testIsBalanced() {
        BinarySearchTree<String, String> tree = createSmallTree();
        // Check if 'isBalanced' returns true for a balanced tree
        assertTrue(tree.isBalanced());
    }

    // Test case for the 'clear' method
    @Test
    public void testClear() {
        BinarySearchTree<String, String> tree = createSmallTree();
        // Check if 'clear' removes all elements from the tree
        tree.clear();
        assertNull(tree.get("A"));
        assertEquals(0, tree.countLeaves());
    }

    // Test case for the 'remove' method
    @Test
    public void testRemove() {
        BinarySearchTree<String, String> tree = createSmallTree();
        // Check if 'remove' removes the specified key and associated value
        tree.remove("B");
        assertNull(tree.get("B"));
        assertFalse(tree.containsKey("B"));
    }

    // Helper method for creating a small tree
    private static BinarySearchTree<String, String> createSmallTree() {
        BinarySearchTree<String, String> tree = new BinarySearchTree<String, String>();
        tree.put("B", "b");
        tree.put("C", "c");
        tree.put("A", "a");
        return tree;
    }

    // Helper method for creating a medium-sized tree
    private static BinarySearchTree<Integer, Integer> createMediumTree() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>();
        for (Integer x : list) {
            tree.put(x, x + 1000);   // 0 -> 1000, 1 -> 1001, etc.
        }
        return tree;
    }
}