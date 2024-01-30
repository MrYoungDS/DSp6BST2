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

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import config.Configuration;

public class BinaryTreeUtilityTest {

	private BinaryTreeNode<Integer> root1, root2, root3, root4, root5;
	private BinaryTreeUtility utility;

	// This tree has over 1 million nodes
	private final BinaryTreeNode<Integer> largeTree = initLargeTree(20);

	private static final <T> BinaryTreeNode<T> node(BinaryTreeNode<T> left, T elem, BinaryTreeNode<T> right){
		return Configuration.createBinaryTreeNode(left, elem, right);
	}
	
	@BeforeEach
	public void setUp() {
		utility = Configuration.createBinaryTreeUtility();
		assertNotNull(utility,
				"It looks like you have not set your implementation of BinaryTreeUtility in the Configuration file.");
		root1 = initRoot1();
		root2 = initRoot2();
		root3 = initRoot3();
		root4 = initRoot4();
		root5 = initRoot5();
		assertNotNull(root1, "It looks like your configuration file isn't set for BinaryTreeNode.");
		assertNotNull(root2, "It looks like your configuration file isn't set for BinaryTreeNode.");
		assertNotNull(root3, "It looks like your configuration file isn't set for BinaryTreeNode.");
		assertNotNull(root4, "It looks like your configuration file isn't set for BinaryTreeNode.");
		assertNotNull(root5, "It looks like your configuration file isn't set for BinaryTreeNode.");
	}
	
	// Creates a tree with n levels
	private BinaryTreeNode<Integer> initLargeTree(int n){
		if(n < 0) return null;
		return node(initLargeTree(n-1), n, initLargeTree(n-1));
	}
	
	private BinaryTreeNode<Integer> initRoot1(){
		return node(null, 5, null);
	}
	
	private BinaryTreeNode<Integer> initRoot2(){
		//       5
		//        \
		//         7
		return node(null, 5, node(null, 7, null));
	}
	
	private BinaryTreeNode<Integer> initRoot3(){
		//                  5
		//              /       \
		//             3         19
		//            / \       /
		//           6   7     1
		//                    /
		//                   4
		return 
		node(
			node(
				node(null, 6, null), 3, node(null, 7, null)),
			5,
			node(
				node(
					node(null, 4, null), 1, null), 19, null));
	}
	
	private BinaryTreeNode<Integer> initRoot4(){
		//                  5
		//              /       \
		//             3         19
		//            / \       /
		//           1   2     1
		//                    /
		//                   4
		return
				node(
						node(
							node(null, 1, null), 3, node(null, 2, null)),
						5,
						node(
							node(
								node(null, 4, null), 1, null), 19, null));
	}
	
	private BinaryTreeNode<Integer> initRoot5(){
		//                  5
		//              /       \
		//             3         19
		//            / \       /  \
		//           1   4     7    25
		return
				node(
						node(
							node(null, 1, null), 3, node(null, 4, null)),
						5,
						node(
								node(null, 7, null), 19, node(null, 25, null)));
	}

	private void assertIteratorContains(Iterator<Integer> itr, Integer ... elems){
		List<Integer> found = new LinkedList<>();
		for(Integer e : elems){
			assertTrue(itr.hasNext(),
					"Expected iterator to produce " + Arrays.toString(elems) + " but produced " + found);
			Integer test = itr.next();
			found.add(test);
			assertEquals(test, e,
					"Expected iterator to produce " + Arrays.toString(elems) + " but start of iterator produced " + found);
		}
		
		if(itr.hasNext()){
			while(itr.hasNext())
				found.add(itr.next());
			fail("Expected iterator to produce " + Arrays.toString(elems) + " but produced " + found);
		}
		
	}
	
	@Test
	public void testGetPreOrderIterator() {
		Iterator<Integer> preorder1 = utility.getPreOrderIterator(root1);
		assertIteratorContains(preorder1, 5);
		
		Iterator<Integer> preorder2 = utility.getPreOrderIterator(root2);
		assertIteratorContains(preorder2, 5, 7);

		//                  5
		//              /       \
		//             3         19
		//            / \       /
		//           6   7     1
		//                    /
		//                   4
		Iterator<Integer> preorder3 = utility.getPreOrderIterator(root3);
		assertIteratorContains(preorder3, 5, 3, 6, 7, 19, 1, 4);

		//                  5
		//              /       \
		//             3         19
		//            / \       /
		//           1   2     1
		//                    /
		//                   4
		Iterator<Integer> preorder4 = utility.getPreOrderIterator(root4);
		assertIteratorContains(preorder4, 5, 3, 1, 2, 19, 1, 4);

		//                  5
		//              /       \
		//             3         19
		//            / \       /  \
		//           1   4     7    25
		Iterator<Integer> preorder5 = utility.getPreOrderIterator(root5);
		assertIteratorContains(preorder5, 5, 3, 1, 4, 19, 7, 25);
	}
	
	@Test
	public void testPreOrderException() {
		assertThrows(NullPointerException.class,
				() -> utility.getPreOrderIterator(null));
	}
	
	@Test
	@Timeout(value = 50, unit = TimeUnit.MICROSECONDS)
	public void testPreOrderIsOof1(){
		// largeTree contains over 1 million elements. getPreOrderIterator
		// should return immediately. Calls to next should also be pretty fast
		// (you shouldn't precompute the traversal).
		Iterator<Integer> itr = utility.getPreOrderIterator(largeTree);
		assertEquals(Integer.valueOf(20), itr.next());
		assertEquals(Integer.valueOf(19), itr.next());
		assertEquals(Integer.valueOf(18), itr.next());
		assertEquals(Integer.valueOf(17), itr.next());
		assertEquals(Integer.valueOf(16), itr.next());
		assertEquals(Integer.valueOf(15), itr.next());
		assertEquals(Integer.valueOf(14), itr.next());
		assertEquals(Integer.valueOf(13), itr.next());
		assertEquals(Integer.valueOf(12), itr.next());
		assertEquals(Integer.valueOf(11), itr.next());
		assertEquals(Integer.valueOf(10), itr.next());
	}
	
	@Test
	public void testGetInOrderIterator() {
		Iterator<Integer> itr = utility.getInOrderIterator(root1);
		assertIteratorContains(itr, 5);
		
		Iterator<Integer> itr2 = utility.getInOrderIterator(root2);
		assertIteratorContains(itr2, 5, 7);

		//                  5
		//              /       \
		//             3         19
		//            / \       /
		//           6   7     1
		//                    /
		//                   4
		Iterator<Integer> itr3 = utility.getInOrderIterator(root3);
		assertIteratorContains(itr3, 6, 3, 7, 5, 4, 1, 19);

		//                  5
		//              /       \
		//             3         19
		//            / \       /
		//           1   2     1
		//                    /
		//                   4
		Iterator<Integer> itr4 = utility.getInOrderIterator(root4);
		assertIteratorContains(itr4, 1, 3, 2, 5, 4, 1, 19);

		//                  5
		//              /       \
		//             3         19
		//            / \       /  \
		//           1   4     7    25
		Iterator<Integer> itr5 = utility.getInOrderIterator(root5);
		assertIteratorContains(itr5, 1, 3, 4, 5, 7, 19, 25);
	}
	
	@Test
	public void testInOrderException(){
		assertThrows(NullPointerException.class,
				() -> utility.getInOrderIterator(null));
	}
	
	@Test
	@Timeout(value = 50, unit = TimeUnit.MICROSECONDS)
	public void testInOrderIsOof1(){
		// largeTree contains over 1 million elements. getPreOrderIterator
		// should return immediately. Calls to next should also be pretty fast
		// (you shouldn't precompute the traversal).
		Iterator<Integer> itr = utility.getInOrderIterator(largeTree);
		assertEquals(Integer.valueOf(0), itr.next());
		assertEquals(Integer.valueOf(1), itr.next());
		assertEquals(Integer.valueOf(0), itr.next());
		assertEquals(Integer.valueOf(2), itr.next());
		assertEquals(Integer.valueOf(0), itr.next());
		assertEquals(Integer.valueOf(1), itr.next());
		assertEquals(Integer.valueOf(0), itr.next());
		assertEquals(Integer.valueOf(3), itr.next());
		assertEquals(Integer.valueOf(0), itr.next());
		assertEquals(Integer.valueOf(1), itr.next());
		assertEquals(Integer.valueOf(0), itr.next());
	}
	
	@Test
	public void testGetPostOrderIterator() {
		Iterator<Integer> itr = utility.getPostOrderIterator(root1);
		assertIteratorContains(itr, 5);
		
		Iterator<Integer> itr2 = utility.getPostOrderIterator(root2);
		assertIteratorContains(itr2, 7, 5);

		//                  5
		//              /       \
		//             3         19
		//            / \       /
		//           6   7     1
		//                    /
		//                   4
		Iterator<Integer> itr3 = utility.getPostOrderIterator(root3);
		assertIteratorContains(itr3, 6, 7, 3, 4, 1, 19, 5);

		//                  5
		//              /       \
		//             3         19
		//            / \       /
		//           1   2     1
		//                    /
		//                   4
		Iterator<Integer> itr4 = utility.getPostOrderIterator(root4);
		assertIteratorContains(itr4, 1, 2, 3, 4, 1, 19, 5);

		//                  5
		//              /       \
		//             3         19
		//            / \       /  \
		//           1   4     7    25
		Iterator<Integer> itr5 = utility.getPostOrderIterator(root5);
		assertIteratorContains(itr5, 1, 4, 3, 7, 25, 19, 5);
	}
	
	@Test
	public void testPostOrderException(){
		assertThrows(NullPointerException.class,
				() -> utility.getPostOrderIterator(null));
	}
	
	@Test
	@Timeout(value = 50, unit = TimeUnit.MICROSECONDS)
	public void testPostOrderIsOof1(){
		// largeTree contains over 1 million elements. getPreOrderIterator
		// should return immediately. Calls to next should also be pretty fast
		// (you shouldn't precompute the traversal).
		Iterator<Integer> itr = utility.getPostOrderIterator(largeTree);
		assertEquals(Integer.valueOf(0), itr.next());
		assertEquals(Integer.valueOf(0), itr.next());
		assertEquals(Integer.valueOf(1), itr.next());
		assertEquals(Integer.valueOf(0), itr.next());
		assertEquals(Integer.valueOf(0), itr.next());
		assertEquals(Integer.valueOf(1), itr.next());
		assertEquals(Integer.valueOf(2), itr.next());
		assertEquals(Integer.valueOf(0), itr.next());
		assertEquals(Integer.valueOf(0), itr.next());
		assertEquals(Integer.valueOf(1), itr.next());
		assertEquals(Integer.valueOf(0), itr.next());
	}
	
	@Test //(timeout = 5000)
	@Timeout(5)
	public void testDepth(){
		assertEquals(0, utility.getDepth(root1));
		assertEquals(1, utility.getDepth(root2));

		//                  5
		//              /       \
		//             3         19
		//            / \       /
		//           6   7     1
		//                    /
		//                   4
		assertEquals(3, utility.getDepth(root3));

		//                  5
		//              /       \
		//             3         19
		//            / \       /
		//           1   2     1
		//                    /
		//                   4
		assertEquals(3, utility.getDepth(root4));

		//                  5
		//              /       \
		//             3         19
		//            / \       /  \
		//           1   4     7    25
		assertEquals(2, utility.getDepth(root5));
		
		assertEquals(20, utility.getDepth(largeTree));
	}
	
	@Test
	public void testDepthException() {
		assertThrows(NullPointerException.class,
				() -> utility.getDepth(null));
	}
	
	@Test //(timeout = 5000)
	@Timeout(5)
	public void testIsBalanced(){
		assertTrue(utility.isBalanced(root1, 0));
		assertTrue(utility.isBalanced(root1, 1));
		assertTrue(utility.isBalanced(root1, 2));
		
		assertFalse(utility.isBalanced(root2, 0));
		assertTrue(utility.isBalanced(root2, 1));
		assertTrue(utility.isBalanced(root2, 2));

		//                  5
		//              /       \
		//             3         19
		//            / \       /
		//           6   7     1
		//                    /
		//                   4
		assertFalse(utility.isBalanced(root3, 0));
		assertFalse(utility.isBalanced(root3, 1));
		assertTrue(utility.isBalanced(root3, 2));

		//                  5
		//              /       \
		//             3         19
		//            / \       /
		//           1   2     1
		//                    /
		//                   4
		assertFalse(utility.isBalanced(root4, 0));
		assertFalse(utility.isBalanced(root4, 1));
		assertTrue(utility.isBalanced(root4, 2));

		//                  5
		//              /       \
		//             3         19
		//            / \       /  \
		//           1   4     7    25
		assertTrue(utility.isBalanced(root5, 0));
		assertTrue(utility.isBalanced(root5, 1));
		assertTrue(utility.isBalanced(root5, 2));
		
		assertTrue(utility.isBalanced(largeTree, 0));
		assertTrue(utility.isBalanced(largeTree, 1));
		assertTrue(utility.isBalanced(largeTree, 2));
	}
	
	@Test
	public void testIsBalancedNull(){
		assertThrows(NullPointerException.class,
				() -> utility.isBalanced(null, 1));
	}
	
	@Test
	public void testIsBalancedIllegal(){
		assertThrows(IllegalArgumentException.class,
				() -> utility.isBalanced(root1, -1));
	}
	
	@Test
	public void testIsBST(){
		assertTrue(utility.isBST(root1));
		assertTrue(utility.isBST(root2));

		//                  5
		//              /       \
		//             3         19
		//            / \       /
		//           6   7     1
		//                    /
		//                   4
		assertFalse(utility.isBST(root3));

		//                  5
		//              /       \
		//             3         19
		//            / \       /
		//           1   2     1
		//                    /
		//                   4
		assertFalse(utility.isBST(root4));

		//                  5
		//              /       \
		//             3         19
		//            / \       /  \
		//           1   4     7    25
		assertTrue(utility.isBST(root5));
	}
	
	@Test
	public void testIsBSTException(){
		assertThrows(NullPointerException.class,
				() -> utility.isBST(null));
	}
}
