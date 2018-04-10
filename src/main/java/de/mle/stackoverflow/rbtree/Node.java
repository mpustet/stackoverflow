package de.mle.stackoverflow.rbtree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Node {
	private Integer value;
	private Color color;
	private Node left;
	private Node right;
}
