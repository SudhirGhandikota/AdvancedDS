package com.test.AVL;

/*
 * Node of an AVL Tree
 */
public class Node {
	Node leftChild;
	int info;
	Node rightChild;
	int balance;//stores the balance factor
	
	public Node(int i){
		info = i;
		balance = 0;
		leftChild = null;
		rightChild = null;
	}
}
