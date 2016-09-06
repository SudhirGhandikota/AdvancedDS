package com.eTree;

public class ExpressionTreeHandler {

	public static void main(String[] args) {
		ExpressionTree eTree = new ExpressionTree();
		String postfix = "45+3/7*42/-";
		
		eTree.buildTree(postfix);
		eTree.display();
		
		System.out.println("Prefix Expression:");
		eTree.prefix();
		
		System.out.println("Postfix Expression:");
		eTree.postfix();
		
		System.out.println("Parenthesized Infix Expression:");
		eTree.parenthesizedInfix();
		
		System.out.print("value of Tree: "+eTree.evaluate());

	}

}
