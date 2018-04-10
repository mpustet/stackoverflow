package de.mle.stackoverflow.rbtree;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class RedBlackTreeTesterTest {
	private RedBlackTreeTester tester = new RedBlackTreeTester();

	@Test
	public void nullTree() {
		assertThat(tester.isRedBlackTree(null)).isFalse();
	}

	@Test
	public void emptyTree() {
		// given
		RedBlackTree tree = new RedBlackTree();

		// when && then
		assertThat(tester.isRedBlackTree(tree)).isFalse();
	}

	@Test
	public void simpleTree() {
		// given
		RedBlackTree tree = new RedBlackTree(new Node(3, Color.RED, null, null));

		// when && then
		assertThat(tester.isRedBlackTree(tree)).isTrue();
	}

	@Test
	public void simpleTreeRootWithoutColorAndValue() {
		// given
		RedBlackTree tree = new RedBlackTree(new Node(null, null, null, null));

		// when && then
		assertThat(tester.isRedBlackTree(tree)).isFalse();
	}

	@Test
	public void treeWithWrongOrdering() {
		// given
		Node root = new Node(3, Color.RED, new Node(4, Color.BLACK, null, null), new Node(5, Color.BLACK, null, null));
		RedBlackTree tree = new RedBlackTree(root);

		// when && then
		assertThat(tester.isRedBlackTree(tree)).isFalse();
	}

	@Test
	public void treeWithWrongColoring() {
		// given
		Node root = new Node(3, Color.RED, new Node(2, Color.RED, null, null), new Node(4, Color.RED, null, null));
		RedBlackTree tree = new RedBlackTree(root);

		// when && then
		assertThat(tester.isRedBlackTree(tree)).isFalse();
	}

	@Test
	public void treeWithWrongBlackDepth() {
		// given
		Node outmostLeft = new Node(1, Color.BLACK, null, null);
		Node leftOfRoot = new Node(2, Color.BLACK, outmostLeft, null);
		Node root = new Node(3, Color.RED, leftOfRoot, new Node(4, Color.BLACK, null, null));
		RedBlackTree tree = new RedBlackTree(root);

		// when && then
		assertThat(tester.isRedBlackTree(tree)).isFalse();
	}

	@Test
	public void complexCorrectRedBlackTree() {
		// given
		Node firstLeaf = new Node(1, Color.RED, null, null);
		Node secondLeaf = new Node(4, Color.RED, null, null);
		Node leftOfRoot = new Node(3, Color.BLACK, firstLeaf, secondLeaf);

		Node thirdLeaf = new Node(6, Color.RED, null, null);
		Node fourthLeaf = new Node(11, Color.RED, null, null);
		Node rightOfRoot = new Node(7, Color.BLACK, thirdLeaf, fourthLeaf);

		Node root = new Node(5, Color.RED, leftOfRoot, rightOfRoot);
		RedBlackTree tree = new RedBlackTree(root);

		// when && then
		assertThat(tester.isRedBlackTree(tree)).isFalse();
		assertThat(tester.getHeight(tree)).isEqualTo(3);
		assertThat(tester.getBlackDepth(tree)).isEqualTo(2);
	}
}
