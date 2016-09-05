package com.test.Threaded;

public class Node {
	public Node left;
	public boolean leftThread; //indicates if 'left' refers to a child or inorder predecessor
	public int info;
	public boolean rightThread; // indicates if 'right' refers to a child or inorder successor
	public Node right;
	
	public Node(int i)
	{
		info =i;
		left = null;
		right = null;
		leftThread = true;
		rightThread = true;
	}

}
