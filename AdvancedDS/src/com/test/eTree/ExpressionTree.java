package com.test.eTree;

public class ExpressionTree {
	Node root;
	
	public ExpressionTree()
	{
		root = null;
	}
	static boolean isOperator(char c)
	{
		if(c=='+'||c=='-'||c=='*'||c=='/')
			return true;
		return false;
	}
	
	public void buildTree(String postfix)
	{
		StackNode stack = new StackNode(30);
		Node t;
		//scanning the postfix notation from left to right
		for(int i=0;i<postfix.length();i++)
		{
			if(isOperator(postfix.charAt(i)))//case where a character is encountered
			{
				t = new Node(postfix.charAt(i)); //creating a new tree node for the operator
				//popping last two trees from the queue. They become the right and left subtree of the new node
				t.rightChild = stack.pop();
				t.leftChild = stack.pop();
				
				stack.push(t);//reference of the new node(root of the newly created tree) pushed onto the stack
			}
			else//case where the character is the operand
			{
				t = new Node(postfix.charAt(i));
				stack.push(t);//push reference to the single node tree(operand) onto the stack
			}
		}
		root = stack.pop(); //after scanning all characters we pop the root of the expression tree from the stack
	}
	
	public void prefix()//returns the prefix notation of the tree
	{
		preorder(root);
		System.out.println();
	}
	private void preorder(Node p)
	{
		if(p==null)
			return;
		System.out.print(p.info);
		preorder(p.leftChild);
		preorder(p.rightChild);
	}
	public void postfix()//returns the postfix notation of the tree
	{
		postorder(root);
		System.out.println();
	}
	private void postorder(Node p)
	{
		if(p==null)
			return;
		postorder(p.leftChild);
		postorder(p.rightChild);
		System.out.print(p.info);
	}
	public void parenthesizedInfix()//returns the parenthesized infix notation
	{
		inorder(root);
		System.out.println();
	}
	private void inorder(Node p)
	{
		if(p==null)
			return;
		if(isOperator(p.info)) //printing a parenthesis when an operator is encountered(before traversal of corresponding operands)
			System.out.print("(");
		inorder(p.leftChild);
		System.out.print(p.info);
		inorder(p.rightChild);
		
		if(isOperator(p.info))//printing a parenthesis when an operator is encountered(after traversal of corresponding operands)
			System.out.print(")");
	}
	public void display()//displays the tree from right to left
	{
		display(root,0);
		System.out.println();
	}
	private void display(Node p, int level)
	{
		int i;
		if(p==null)
			return;
		display(p.rightChild,level+1);
		System.out.println();
		
		for(i=0;i<level;i++)
			System.out.print("	");
		System.out.print(p.info);
		display(p.leftChild, level+1);
	}
	public int evaluate()//evaluates the expression tree
	{
		if(root==null)
			return 0;
		return evaluate(root);
	}
	private int evaluate(Node p)
	{
		if(!isOperator(p.info))
			return Character.getNumericValue(p.info);
		//case where node is an operator
		int leftValue = evaluate(p.leftChild);
		int rightValue = evaluate(p.rightChild);
		
		if(p.info=='+')
			return leftValue + rightValue;
		else if(p.info=='-')
			return leftValue - rightValue;
		else if(p.info=='*')
			return leftValue*rightValue;
		else
			return leftValue/rightValue;
	}

}
