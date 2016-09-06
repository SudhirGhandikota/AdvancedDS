package com.eTree;

public class Node {
	Node leftChild;
	char info; //this values would either be an operator or operand
	Node rightChild;
	
	public Node(char c)
	{
		info  =c;
		leftChild = null;
		rightChild = null;
	}
}
