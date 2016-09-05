package com.test.Threaded;

public class ThreadedBinaryTree {
	private Node root;
	
	public ThreadedBinaryTree()
	{
		root = null;
	}
	public boolean isEmpty()
	{
		return (root==null);
	}
	private Node inorderPredecessor(Node p)
	{
		if(p.leftThread==true)//indicates node p points to its inorder predecessor
			return p.left;
		else //case where node p has a child.
		{
			p=p.left;
			while(p.rightThread==false)//finding the inorder predecessor i.e the right most node in the left subtree
				p=p.right;
			return p;
		}
	}
	private Node inorderSuccessor(Node p)
	{
		if(p.rightThread==true)//indicates node p points to its inorder successor
			return p.right;
		else //case where node p has a child
		{
			p=p.right;
			while(p.leftThread==false)//finding the inorder successor i.e the left most node in the right subtree
				p=p.left;
			return p;
		}
	}
	//prints the inorder traversal of the node
	public void inorder()
	{
		if(root==null)
		{
			System.out.println("tree is empty");
			return;
		}
		// find the leftmost node of the tree
		Node p =root;
		while(p.leftThread==false)
			p=p.left;
		while(p!=null)//loop until we reach the right most node whose right link(rightThread) is null
		{
			System.out.print(p.info + " ");
			if(p.rightThread==true) // if 'right' is pointing to the inorder successor
				p = p.right;
			else // find the inorder successor i.e the left most node in the right sub tree
			{
				p = p.right;
				while(p.leftThread==false)
					p = p.left;
			}
			
		}
		System.out.println();
	}
	
	public void insert(int x)
	{
		Node p =root;
		Node par = null; //holds the parent
		while(p!=null)
		{
			par = p;
			if(x<p.info)
			{
				//looping till we reach the left most node with no child
				if(p.leftThread==false)
					p=p.left;
				else
					break;
			}
			else if(x>p.info)
			{
				//looping till we reach the left most node with no child
				if(p.rightThread==false)
					p=p.right;
				else
					break;
			}
			else
			{
				System.out.println(x + "already present in the tree");
				return;
			}
		}//the search terminates as we soon as reach a thread or a null check
		Node temp = new Node(x);
		if(par==null)
			root = temp;
		else if(x<par.info)// inserted as left child
		{
			temp.left = par.left; //assigning the parent's inorder predecessor to the new node
			temp.right = par; // and assigning the parent as the inorder successor of the new node
			par.leftThread = false;
			par.left = temp; //new node will be the inorder predecessor of the parent(since left child)
		}
		else //inserted as right child
		{
			temp.left = par; //assigning the parent as the new node's inorder predecessor(since right child)
			temp.right = par.right; //assigning the inorder successor of the parent to the new node
			par.rightThread = false;
			par.right = temp;//new node now becomes the inorder successor of the parent
		}
	}
	
	public void delete(int x)
	{
		Node p =root;
		Node par = null;
		//loop to search the node to be deleted
		while(p!=null)
		{
			if(x==p.info)
				break;
			par = p;//storing the parent of the deleted node(from previous iteration)
			if(x<p.info)//if deleted node is in the left subtree
			{
				if(p.leftThread==false)
					p = p.left;
				else
					break;
			}
			else //if deleted node is in right subtree
			{
				if(p.rightThread==false)
					p = p.right;
				else
					break;
			}
		}
		//if the node to be deleted not found
		if(p==null || p.info!=x)
		{
			System.out.println(x + " not found");
			return;
		}
		if(p.leftThread==false && p.rightThread==false)// case where node to be deleted has 2 children
		{
			//Finding inorder successor and its parent
			Node ps = p;
			Node s = p.right;
			//loop to find inorder successor(s) and its parent (ps)
			while(s.leftThread==false)
			{
				ps = s;
				s = s.left;
			}
			p.info = s.info; //copying the contents of the inorder successor to the current node
			p = s;
			par = ps; //parent of the inorder successor now becomes the new parent. The inorder successor is deleted in either of the two cases below
		}
		//case with no child - leaf node
		if(p.leftThread==true && p.rightThread==true)
		{
			if(par==null)//indicates node to be deleted is the only node in the tree
				root = null;
			else if(p==par.left)//node to be deleted is the left child
			{
				par.leftThread = true;
				par.left = p.left;//predecessor of the node to be deleted is now the predecessor of its parent
			}
			else //node to be deleted is the right child
			{
				par.rightThread = true;
				par.right = p.right; //successor of the node to be deleted is now the successor of its parent
			}
			return;
		}
		
		//case with 1 child
		Node ch;
		if(p.leftThread==false) //node to be deleted has left child
			ch = p.left;
		else//node to be deleted has right child
			ch = p.right;
		
		if(par==null)//node to be deleted is root node
			root = ch;
		else if(p==par.left)//node is left child of its parent
			par.left=ch;
		else//node is right child of its parent
			par.right = ch;
		
		Node pred = inorderPredecessor(p);
		Node succ = inorderSuccessor(p);
		
		if(p.leftThread==false)//if p has left child, right is a thread
			pred.right = succ;
		else // if p has right child, left is a thread
			succ.left = pred;
	}

}
