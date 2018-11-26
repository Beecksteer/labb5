package bst;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root;
	public int size;

	// main-metoden.

	public static void main(String[] args) {
		BSTVisualizer bst = new BSTVisualizer("Testträdet", 500, 500);
		BinarySearchTree<Integer> btree1 = new BinarySearchTree<Integer>();

		// snett träd som sedan ska balanserad med rebuild.
		btree1.add(2);
		btree1.add(4);
		btree1.add(5);
		btree1.add(8);
		btree1.add(9);

		bst.drawTree(btree1);
		btree1.printTree();

		// balanserar upp det sneda trädet.
		btree1.rebuild();
		bst.drawTree(btree1);

	}

	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() {
		size = 0;
		root = null;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * 
	 * @param x
	 *            element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		if (root == null) {
			root = new BinaryNode<E>(x);
			size++;
			return true;
		} else {
			if (true == root.add(x)) {
				size++;
			}
			return root.add(x);
		}
	}

	/**
	 * Computes the height of tree.
	 * 
	 * @return the height of the tree
	 */

	public int height() {
		if (root == null) {
			return 0;
		} else {
			return root.height();
		}
	}

	/**
	 * Returns the number of elements in this tree.
	 * 
	 * @return the number of elements in this tree
	 */
	public int size() {
//		if (root == null) {
//			return 0;
//		}
		return size;
	}

	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		if (root == null) {
			return;
		} else {
			root.printTree();
		}

	}

	/**
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {

		if (root == null) {
			return;
		}

		E[] a = (E[]) new Comparable[size]; // gör så då vi ej kan skapa en vektor med element av parametriserad typ.
		toArray(root, a, 0); // startar vid index 0.

		root = buildTree(a, 0, size - 1); // firt och last är index a[0] ex
	}

	/*
	 * Adds all elements from the tree rooted at n in inorder to the array a
	 * starting at a[index]. Returns the index of the last inserted element + 1 (the
	 * first empty position in a).
	 */
	private int toArray(BinaryNode<E> n, E[] a, int index) {

		if (n.left != null) {
			index = toArray(n.left, a, index);
		}
		a[index] = n.element; // index är starten.
		index++;
		if (n.right != null) {
			index = toArray(n.right, a, index);
		}
		return index;
	}

	/*
	 * Builds a complete tree from the elements a[first]..a[last]. Elements in the
	 * array a are assumed to be in ascending order. Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {
		int middle = first + ((last - first) / 2); // beräknar mitten-indexet.
		BinaryNode<E> n = new BinaryNode<E>(a[middle]); // sparar i vektorn

		if (middle - first > 0) { // får bli vänstra delen av trädet
			n.left = buildTree(a, first, middle - 1);
		}

		if (last - middle > 0) {
			n.right = buildTree(a, middle + 1, last);
		}

		return n;
	}

	static class BinaryNode<E extends Comparable<? super E>> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}

		private int height() {
			int nleft = 0;
			if (left != null) {
				nleft = left.height();
			}
			if (right != null) {
				int n = right.height();
				if (n > nleft) {
					nleft = n;
				}
			}
			return nleft + 1; // plus 1 för att inte missa första roten?
		}

		// använder compareTo för att jömföra om elementen less, equal or greater.
		private boolean add(E x) {
			if (element.compareTo(x) > 0) {
				if (left == null) {
					left = new BinaryNode<E>(x);
					return true;
				} else {
					return left.add(x);
				}
			} else if (element.compareTo(x) < 0) {
				if (right == null) {
					right = new BinaryNode<E>(x);
					return true;
				} else {
					return right.add(x);
				}
			}
			return false;
		}

		private void printTree() {
			if (left != null) { // 1. //traversera vänster subträd
				left.printTree();
			}
			// besök roten
			System.out.println(element); // traversera höger subträd
			if (right != null) {
				right.printTree();
			}
		}

//		private int size() {
//			int n = 1;
//			if (left != null) {
//				n = n + left.size();
//			}
//			if (right != null) {
//				n = n + right.size();
//			}
//			return n;
//		}
	}
}
