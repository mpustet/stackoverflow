package de.mle.stackoverflow.rbtree;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
        if (tree == null) {
            return false;
        }

        Node root = tree.getRoot();
        if (root == null || root.getValue() == null) {
            return false;
        }

        return valuesAndColorsAreWellOrdered(root) && getBlackDepthOfNode(root, 0).isPresent();
    }

    /**
     * Returns the height of the given red black tree. Black null-leafs do not count for the height.
     *
     * @param tree
     *            The red black tree under test.
     * @return the height of the given red black tree.
     */
    public int getHeight(RedBlackTree tree) {
        Node root = tree.getRoot();
        return  getHeightOfNodes(root, 1);
    }

    /**
     * Returns the black-depth of the given red black tree. Black null-leafs do not count for the black-depth.
     *
     * @param tree
     *            The red black tree under test.
     * @return the height of the given red black tree.
     */
    public int getBlackDepth(RedBlackTree tree) {

        Node root = tree.getRoot();
        int blackDepth = 0;

        return  getBlackDepthOfNode(root, blackDepth).orElse(0);
    }


    private boolean valuesAndColorsAreWellOrdered(Node parent) {
        if (parent == null) return false;

        Node leftChild = parent.getLeft();
        Node rightChild = parent.getRight();
        Color parentColor = parent.getColor();
        Integer parentValue = parent.getValue();

        if (leftChild != null) {
            if (leftChild.getValue() > parentValue || parentColor == leftChild.getColor())
                return false;
            valuesAndColorsAreWellOrdered(leftChild);
        }
        if (rightChild != null) {
            if (rightChild.getValue() < parentValue || parentColor == rightChild.getColor())
                return false;
            valuesAndColorsAreWellOrdered(rightChild);
        }

        return true;
    }

    private Optional<Integer> getBlackDepthOfNode(Node node, int blackDepth){
        if (node == null)
            return Optional.of(blackDepth);

        if (node.getColor() == Color.BLACK) {
            blackDepth = blackDepth + 1;
        }

        Optional<Integer> blackDepthLeft = getBlackDepthOfNode(node.getLeft(), blackDepth);
        Optional<Integer> blackDepthRight = getBlackDepthOfNode(node.getRight(), blackDepth);

        if (!blackDepthLeft.equals(blackDepthRight)) {
            log.warn("Black depths are not the same on all paths!");
            return Optional.empty();
        }
        return blackDepthLeft;
    }

    private int getHeightOfNodes(Node node, int height){
        if (node == null)
            return height;

        if ((node.getLeft() == null) && (node.getRight() == null)) {
            return height;
        }

        height = height + 1;

        int heightLeft = getHeightOfNodes(node.getLeft(), height);
        int heightRight = getHeightOfNodes(node.getRight(), height);

        if (heightLeft >= heightRight) return heightLeft;
        else return heightRight;
    }
}
