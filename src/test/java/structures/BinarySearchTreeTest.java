package structures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import config.Configuration;

public class BinarySearchTreeTest {

    private BinarySearchTree<Integer> tree;
    private static final int SPEED_TEST = 1 << 3;

    @BeforeEach
    public void setUp() {
        tree = Configuration.createBinarySearchTree();
        assertNotNull(tree, "It looks like you did not set createBinarySearchTree in your configuration file.");
    }

    @Test
    public void testSimpleAddSizeAndIsEmpty(){
        assertTrue(tree.isEmpty(), "Fresh tree should be empty.");
        assertEquals(0, tree.size(), "Fresh tree should have size 0.");
        assertEquals(tree, tree.add(1), "Add should return tree for convenience.");
        assertFalse(tree.isEmpty(), "Tree should now be non-empty.");
        assertEquals(1, tree.size(), "Size should now be 1.");
        assertEquals(tree, tree.add(1), "Add should return tree for convenience.");
        assertFalse(tree.isEmpty(), "Tree should now be non-empty.");
        assertEquals(2, tree.size(), "Size should now be 2.");
        assertEquals(tree, tree.add(1), "Add should return tree for convenience.");
        assertFalse(tree.isEmpty(), "Tree should now be non-empty.");
        assertEquals(3, tree.size(), "Size should now be 3.");
        assertEquals(tree, tree.add(2), "Add should return tree for convenience.");
        assertFalse(tree.isEmpty(), "Tree should now be non-empty.");
        assertEquals(4, tree.size(), "Size should now be 4.");
    }

    @Test
    public void testSimpleAddAndContains() {
        assertFalse(tree.contains(1), "Tree should not contain anything.");
        assertEquals(tree, tree.add(1), "Add should return tree for convenience.");
        assertTrue(tree.contains(1), "After add, contains should return true.");

        assertFalse(tree.contains(5), "Tree should not contain 5.");
        assertEquals(tree, tree.add(5), "Add should return tree for convenience.");
        assertTrue(tree.contains(5), "After add, contains should return true.");
    }

    @Test
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    public void testRandomAddContains() {
        Random r = new Random(42);
        Set<Integer> valuesAdded = new HashSet<>();
        for(int i = 0; i < SPEED_TEST; i++){
            assertEquals(i, tree.size(), "Tree should have i elements in it.");
            int next = r.nextInt();

            if(!valuesAdded.contains(next)){
                assertFalse(tree.contains(next), "Tree should not contain this value yet.");
                valuesAdded.add(next);
            }

            assertEquals(tree, tree.add(next), "Add should return tree for convenience.");
            assertTrue(tree.contains(next), "After add, contains should return true.");
        }
    }

    @Test
    public void testAddNullPointer(){
        assertThrows(NullPointerException.class,
                () -> tree.add(null));
    }

    @Test
    public void testContainsNullPointer(){
        assertThrows(NullPointerException.class,
                () -> tree.contains(null));
    }

    @Test
    public void testSimpleAddRemoveAndSize() {
        assertEquals(tree, tree.add(1), "Add should return tree for convenience.");
        assertEquals(tree, tree.add(5), "Add should return tree for convenience.");
        assertEquals(tree, tree.add(5), "Add should return tree for convenience.");
        assertEquals(tree, tree.add(5), "Add should return tree for convenience.");

        assertEquals(4, tree.size());
        assertTrue(tree.remove(1));
        assertEquals(3, tree.size());
        assertFalse(tree.remove(1));
        assertEquals(3, tree.size());

        assertTrue(tree.remove(5));
        assertEquals(2, tree.size());
        assertTrue(tree.remove(5));
        assertEquals(1, tree.size());
        assertTrue(tree.remove(5));
        assertEquals(0, tree.size());
        assertFalse(tree.remove(5));
        assertTrue(tree.isEmpty());
    }

    @Test
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    public void testRandomAddRemoveAndSize() {
        Random r = new Random(42);
        List<Integer> valuesAdded = new LinkedList<>();

        for(int i = 0; i < SPEED_TEST; i++){
            assertEquals(i, tree.size(), "Tree should have i elements in it.");
            int next = r.nextInt(SPEED_TEST);
            valuesAdded.add(next);
            assertEquals(tree, tree.add(next), "Add should return tree for convenience.");
            assertTrue(tree.contains(next), "After add, contains should return true.");
        }
        assertEquals(SPEED_TEST, tree.size());
        for(Integer i : valuesAdded){
            assertTrue(tree.remove(i), "Could not remove previously added node.");
        }
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
    }

    @Test
    public void testSimpleGetMinAndGetMax(){
        tree.add(4).add(2).add(1).add(3).add(5).add(6).add(7);
        assertEquals(Integer.valueOf(1), tree.getMinimum());
        assertEquals(Integer.valueOf(7), tree.getMaximum());
    }

    @Test
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    public void testRandomGetMinAndGetMax() {
        Random r = new Random(42);
        LinkedList<Integer> values = new LinkedList<>();
        int currentMin = Integer.MAX_VALUE;
        int currentMax = Integer.MIN_VALUE;
        for(int i = 0; i < SPEED_TEST; i++){
            int next = r.nextInt();
            currentMin = Math.min(currentMin, next);
            currentMax = Math.max(currentMax, next);
            values.add(next);
            tree.add(next);
        }

        assertEquals(Integer.valueOf(currentMin), tree.getMinimum());
        assertEquals(Integer.valueOf(currentMax), tree.getMaximum());
    }

    @Test
    public void testIllegalStateGetMin(){
        assertThrows(IllegalStateException.class,
                () -> tree.getMinimum());
    }

    @Test
    public void testIllegalStateGetMax(){
        assertThrows(IllegalStateException.class,
                () -> tree.getMaximum());
    }
}
