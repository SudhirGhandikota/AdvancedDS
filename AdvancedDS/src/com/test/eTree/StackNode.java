package com.test.eTree;

import java.util.EmptyStackException;

//stack of nodes
public class StackNode {
	private Node[] stackArray;
	private int top;
	
	public StackNode()
	{
		stackArray = new Node[10];
		top = -1;
	}
	public StackNode(int maxSize)
	{
		stackArray = new Node[maxSize];
		top = -1;
	}
	public int size()
	{
		return top+1;
	}
	public boolean isEmpty()
	{
		return (top==-1);
	}
	public boolean isFull()
	{
		return (top==stackArray.length - 1);
	}
	public void push(Node x)
	{
		if(isFull())
		{
			System.out.println("Stack Overflow\n");
			return;
		}
		top++;
		stackArray[top] = x;
	}
	public Node pop()
	{
		Node x;
		if(isEmpty())
		{
			System.out.println("Stack Undeflow\n");
			throw new EmptyStackException();
		}
		x = stackArray[top];
		top--;
		return x;
	}

}
