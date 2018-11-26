
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bst.BinarySearchTree;

public class BinarySearchTreeTest {
	private BinarySearchTree<Integer> tree;

	@Before
	public void setUp() throws Exception {
		tree = new BinarySearchTree<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		tree = null;
	}

	@Test
	public void testHeight() {
		tree.add(2);
		tree.add(3);
		assertEquals("", 2, tree.height());
	}

	@Test
	public void testAdd() {
		tree.add(2);
	}

	@Test
	public void testSize() {
		tree.add(2);
		tree.add(3);
		tree.add(1);
		assertTrue(tree.size() == 3);
	}

	@Test
	public void testAddDubble() {
		tree.add(2);
		assertTrue(tree.add(2) == false);
	}


	@Test
	public void testHeightEmpty() {
		assertTrue(tree.height() == 0);
	}

	@Test
	public void testSizeEmpty() {
		assertTrue(tree.size() == 0);
	}

}
