package de.mle.stackoverflow.rbtree;

public class RedBlackTreeTester {
	/**
	 * Tests if the given tree is a red black tree with respect to the following conditions: <pre>
	 * 1. It is a binary search tree: https://de.wikipedia.org/wiki/Bin%C3%A4rer_Suchbaum
	 * 2. All leafs (null-nodes) are black (let's assume our null-leafs are all black).
	 * 3. A red node has two black children.
	 * 4. All paths have the same black-depth.
	 * </pre>
	 * 
	 * @param tree
	 *            The red black tree under test.
	 * @return true, if the given tree is a red black tree.
	 */
	public boolean isRedBlackTree(RedBlackTree tree) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns the height of the given red black tree. Black null-leafs do not count for the height.
	 * 
	 * @param tree
	 *            The red black tree under test.
	 * @return the height of the given red black tree.
	 */
	public int getHeight(RedBlackTree tree) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns the black-depth of the given red black tree. Black null-leafs do not count for the black-depth.
	 * 
	 * @param tree
	 *            The red black tree under test.
	 * @return the height of the given red black tree.
	 */
	public int getBlackDepth(RedBlackTree tree) {
		throw new UnsupportedOperationException();
	}
}
