package de.mle.stackoverflow.rbtree;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
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
		RedBlackTree tree = new RedBlackTree(new Node(3, Color.RED, null, null, null));

		// when && then
		assertThat(tester.isRedBlackTree(tree)).isTrue();
	}

	@Test
	public void simpleTreeRootWithoutColorAndValueAndParent() {
		// given
		RedBlackTree tree = new RedBlackTree(new Node(null, null, null, null, null));

		// when && then
		assertThat(tester.isRedBlackTree(tree)).isFalse();
	}

	@Test
	public void treeWithWrongOrdering() {
		// given
		Node root = null;
		Node leftLeaf = new Node(4, Color.BLACK, root, null, null);
		Node rightLeaf = new Node(5, Color.BLACK, root, null, null);
		root = new Node(3, Color.RED, null, leftLeaf, rightLeaf);
		RedBlackTree tree = new RedBlackTree(root);

		// when && then
		assertThat(tester.isRedBlackTree(tree)).isFalse();
	}

	@Test
	public void treeWithWrongColoring() {
		// given
		Node root = null;
		Node leftLeaf = new Node(2, Color.RED, root, null, null);
		Node rightLeaf = new Node(4, Color.RED, root, null, null);
		root = new Node(3, Color.RED, null, leftLeaf, rightLeaf);
		RedBlackTree tree = new RedBlackTree(root);

		// when && then
		assertThat(tester.isRedBlackTree(tree)).isFalse();
	}

	@Test
	public void treeWithWrongBlackDepth() {
		// given
		Node root = null;
		Node leftOfRoot = null;
		Node outmostLeft = new Node(1, Color.BLACK, leftOfRoot, null, null);
		leftOfRoot = new Node(2, Color.BLACK, root, outmostLeft, null);
		Node right = new Node(4, Color.BLACK, root, null, null);
		root = new Node(3, Color.RED, null, leftOfRoot, right);
		RedBlackTree tree = new RedBlackTree(root);

		// when && then
		assertThat(tester.isRedBlackTree(tree)).isFalse();
	}

	@Test
	public void complexCorrectRedBlackTree() {
		// given
		Node root = null;

		Node leftOfRoot = null;

		Node firstLeaf = new Node(1, Color.RED, leftOfRoot, null, null);
		Node secondLeaf = new Node(4, Color.RED, leftOfRoot, null, null);
		leftOfRoot = new Node(3, Color.BLACK, root, firstLeaf, secondLeaf);

		Node rightOfRoot = null;

		Node thirdLeaf = new Node(6, Color.RED, rightOfRoot, null, null);
		Node fourthLeaf = new Node(11, Color.RED, rightOfRoot, null, null);
		rightOfRoot = new Node(7, Color.BLACK, root, thirdLeaf, fourthLeaf);

		root = new Node(5, Color.RED, null, leftOfRoot, rightOfRoot);
		RedBlackTree tree = new RedBlackTree(root);

		// when && then
		assertThat(tester.isRedBlackTree(tree)).isFalse();
		assertThat(tester.getHeight(tree)).isEqualTo(3);
		assertThat(tester.getBlackDepth(tree)).isEqualTo(2);
	}
}
