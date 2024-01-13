// Binary Search Tree (BST) custom implementation with special functionality for projects
public class BinarySearchTree<K extends Comparable<K>, V> {
    
    // Node class for representing individual elements in the binary search tree
    private class Node {
        private K key;
        private V value;
        private Node left, right;

        private Node(K key, V data) {
            this.key = key;
            this.value = data;
        }
    }

    // Root of the binary search tree
    private Node root;

    /**
     * Search for the Node with the key equal to the parameter.
     * If found, return the corresponding data value.
     * @param key The key to search for
     * @return The corresponding data value or null if not found
     */
    public V get(K key) {
        if (root == null) {
            return null;
        }
        return getHelper(root, key);
    }

    // Helper method for recursive search
    private V getHelper(Node localRoot, K key) {
        if (localRoot == null) {
            return null;
        }
        int compNum = localRoot.key.compareTo(key);
        if (compNum == 0) {
            return localRoot.value;
        }
        if (compNum < 0) {
            return getHelper(localRoot.right, key);
        }
        return getHelper(localRoot.left, key);
    }

    /**
     * Returns the number of leaves in the tree.
     * @return The number of leaves in the tree
     */
    public int countLeaves() {
        if (root == null) {
            return 0;
        }
        return countLeavesHelper(root);
    }

    // Helper method for recursive counting of leaves
    private int countLeavesHelper(Node localRoot) {
        if (localRoot == null) {
            return 0;
        }
        if (localRoot.left == null && localRoot.right == null) {
            return 1;  // Node is a leaf
        }
        return countLeavesHelper(localRoot.left) + countLeavesHelper(localRoot.right);
    }

    /**
     * Find the Node with the smallest key, and return its value.
     * @return The value of the Node with the smallest key
     */
    public V getValueForSmallestKey() {
        if (root == null) {
            return null;
        }
        return getSmallestHelper(root);
    }

    // Helper method for recursive search of smallest key
    private V getSmallestHelper(Node localRoot) {
        if (localRoot.left == null) {
            return localRoot.value;
        }
        return getSmallestHelper(localRoot.left);
    }

    /**
     * Find any key that is mapped to the given value.
     * @param value The value to search for
     * @return Any key that maps to the given value or null if not found
     */
    public K reverseLookup(V value) {
        if (root == null) {
            return null;
        }
        return reverseLookupHelper(root, value);
    }

    // Helper method for recursive reverse lookup
    private K reverseLookupHelper(Node localRoot, V value) {
        if (localRoot == null) {
            return null;
        }
        if (localRoot.value.equals(value)) {
            return localRoot.key;
        }
        K leftFound = reverseLookupHelper(localRoot.left, value);
        if (leftFound != null) {
            return leftFound;
        }
        K rightFound = reverseLookupHelper(localRoot.right, value);
        if (rightFound != null) {
            return rightFound;
        }
        return null;
    }

    /**
     * Inserts a key-value pair into the binary search tree.
     * @param key The key to insert
     * @param data The corresponding data value
     * @return true if the key is inserted, false if the key already exists (update data)
     */
    public boolean put(K key, V data) {
        if (root == null) {
            root = new Node(key, data);
            return true;
        } else {
            return putHelper(key, data, root);
        }
    }

    // Helper method for recursive insertion
    private boolean putHelper(K key, V data, Node localRoot) {
        int comparison = key.compareTo(localRoot.key);
        if (comparison == 0) {
            localRoot.value = data;
            return false;
        } else if (comparison < 0) {
            if (localRoot.left == null) {
                localRoot.left = new Node(key, data);
                return true;
            } else {
                return putHelper(key, data, localRoot.left);
            }
        } else {
            if (localRoot.right == null) {
                localRoot.right = new Node(key, data);
                return true;
            } else {
                return putHelper(key, data, localRoot.right);
            }
        }
    }

    /**
     * Returns a string representation of the binary search tree.
     * @return String representation of the binary search tree
     */
    public String toString() {
        return toStringAux(root);
    }

    // Helper method for recursive string representation
    private String toStringAux(Node rootAux) {
        String answer = "";
        if (rootAux == null) {
            return answer;
        }
        answer = toStringAux(rootAux.left);
        answer += "{" + rootAux.key + ":" + rootAux.value + "}";
        answer += toStringAux(rootAux.right);
        return answer;
    }
    
    /** Check if the tree contains a specific key. */
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /** Check if the tree contains a specific value. */
    public boolean containsValue(V value) {
        return containsValueHelper(root, value);
    }

    private boolean containsValueHelper(Node localRoot, V value) {
        if (localRoot == null) {
            return false;
        }
        if (localRoot.value.equals(value)) {
            return true;
        }
        return containsValueHelper(localRoot.left, value) || containsValueHelper(localRoot.right, value);
    }

    /** Get the height of the tree. */
    public int getHeight() {
        return getHeightHelper(root);
    }

    private int getHeightHelper(Node localRoot) {
        if (localRoot == null) {
            return 0;
        }
        int leftHeight = getHeightHelper(localRoot.left);
        int rightHeight = getHeightHelper(localRoot.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /** Check if the tree is balanced. */
    public boolean isBalanced() {
        return isBalancedHelper(root);
    }

    private boolean isBalancedHelper(Node localRoot) {
        if (localRoot == null) {
            return true;
        }
        int leftHeight = getHeightHelper(localRoot.left);
        int rightHeight = getHeightHelper(localRoot.right);
        int balanceFactor = Math.abs(leftHeight - rightHeight);
        return balanceFactor <= 1 && isBalancedHelper(localRoot.left) && isBalancedHelper(localRoot.right);
    }

    /** Clear the entire tree. */
    public void clear() {
        root = null;
    }
    
    /** Remove a node with the given key from the tree. */
    public void remove(K key) {
        root = removeHelper(root, key);
    }

    private Node removeHelper(Node localRoot, K key) {
        if (localRoot == null) {
            return null; // Key not found
        }

        int compNum = key.compareTo(localRoot.key);
        if (compNum < 0) {
            localRoot.left = removeHelper(localRoot.left, key);
        } else if (compNum > 0) {
            localRoot.right = removeHelper(localRoot.right, key);
        } else {
            // Node with key found

            // Case 1: Node with only one child or no child
            if (localRoot.left == null) {
                return localRoot.right;
            } else if (localRoot.right == null) {
                return localRoot.left;
            }

            // Case 2: Node with two children
            localRoot.key = minValue(localRoot.right);
            localRoot.right = removeHelper(localRoot.right, localRoot.key);
        }

        return localRoot;
    }

    private K minValue(Node root) {
        K minValue = root.key;
        while (root.left != null) {
            minValue = root.left.key;
            root = root.left;
        }
        return minValue;
    }
    
}