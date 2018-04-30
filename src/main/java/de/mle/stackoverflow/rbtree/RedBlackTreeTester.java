package de.mle.stackoverflow.rbtree;

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

        return valuesAndColorsAreWellOrdered(root) && allPathsHaveSameBlackDepth(root);
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

        return  getBlackDepthOfNode(root, blackDepth);
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

    private int getBlackDepthOfNode(Node node, int blackDepth){
        if (node == null)
            return blackDepth;

        if (node.getColor() == Color.BLACK) {
            blackDepth = blackDepth + 1;
        }

        int blackDepthLeft = getBlackDepthOfNode(node.getLeft(), blackDepth);
        int blackDepthRight = getBlackDepthOfNode(node.getRight(), blackDepth);

        if (blackDepthLeft != blackDepthRight) {
            log.warn("Black depths are not the same on all paths!");
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

    private boolean allPathsHaveSameBlackDepth(Node root){

        int blackDepthLeft = getBlackDepthOfNode(root.getLeft(), 0);
        int blackDepthRight = getBlackDepthOfNode(root.getRight(), 0);

        return blackDepthLeft == blackDepthRight;
    }

}
