package com.test.AVL;

public class AVLTree {
	private Node root;
	
	static boolean taller; //used in insert case
	static boolean shorter;
	
	public AVLTree(){
		root = null;
	}
	
	public boolean isEmpty()
	{
		return (root==null);
	}
	public void display()
	{
		display(root,0);
		System.out.println();
	}
	private void display(Node p,int level)
	{
		int i;
		//exit criteria
		if(p==null)
			return;
		display(p.rightChild, level+1);
		System.out.println();
		for(i=0;i<level;i++)
			System.out.print(" ");
		System.out.print(p.info);
		display(p.leftChild,level+1);
	}
	public void inorder()
	{
		inorder(root);
		System.out.println();
	}
	private void inorder(Node p)
	{
		//exit criteria
		if(p==null)
			return;
		inorder(p.leftChild);
		System.out.print(p.info+" ");
		inorder(p.rightChild);
	}
	public void insert(int x)
	{
		root = insert(root,x);
	}
	/*
	 * Recursive method to insert a new node.
	 * This method also calls other methods to check the balance factors in the unwinding phase
	 */
	private Node insert(Node p, int x)
	{
		//case where actual node is inserted. The exit condition
		if(p==null)
		{
			p=new Node(x);
			taller=true;//taller flag set to true whenever new node is inserted. it is done so to keep checking balance factors
		}
		else if(x<p.info)
		{
			p.leftChild = insert(p.leftChild,x);
			//checking balance factors of the left subtree
			if(taller==true)
				p=insertionLeftSubtreeCheck(p);
		}
		else if(x>p.info)
		{
			p.rightChild = insert(p.rightChild,x);
			//checking the balance factors of the right subtree
			if(taller==true)
				p=insertionRightSubtreeCheck(p);
		}
		else
		{
			System.out.println(x+" already present in tree");
			taller=false;
		}
		return p;
	}
 /*
  * This method is called when the newly inserted node is in the left sub tree
  */
	private Node insertionLeftSubtreeCheck(Node p) {
		switch(p.balance)
		{
		case 0: //Case L_1: was balanced
			p.balance = 1; //now left heavy
			break;//keep continue checking balance factors
		case -1: //Case L_2: was right heavy
			p.balance = 0; //now balanced
			taller = false;//stop checking balance factors
			break;
		case 1://Case L_3: was left heavy
			p = insertLeftBalance(p); //performing left balancing
			taller = false;//stop checking balance factors after balancing is done
		}
		return p;
	}

	private Node insertLeftBalance(Node p) {
		Node a,b;
		
		a=p.leftChild;
		if(a.balance==1)// Case L_3A: Insertion in AL -> left-left imbalance
		{
			p.balance=0;
			a.balance=0;
			p=rotateRight(p); //rotate right around p
		}
		else // Case L_3B: Insertion in AR -> left-right imbalance
		{
			b = a.rightChild;
			//three cases of L_3B. Same rotations but different balance factors
			switch(b.balance)
			{
			case 1://Case L_3B1: Insertion in BL
				p.balance = -1;
				a.balance = 0;
				break;
			case -1: //Case L_3B2: Insertion in BR
				p.balance=0;
				a.balance=1;
				break;
			case 0: //Case L_3B3: B is the newly inserted node
				p.balance=0;
				a.balance=0;
			}
			b.balance=0;//update balance factor of new root 'b'
			p.leftChild=rotateLeft(a); //rotate left about A
			p = rotateRight(p);// and rotate right about P
					
		}
		return p;
	}

	private Node rotateLeft(Node p) {
		Node a = p.rightChild;
		p.rightChild = a.leftChild;
		a.leftChild = p;
		return a;//new root
	}

	private Node rotateRight(Node p) {
		Node a = p.leftChild;
		p.leftChild = a.rightChild;
		a.rightChild = p;
		return a; //new root
	}

	private Node insertionRightSubtreeCheck(Node p) {
		switch(p.balance)
		{
		case 0://Case R_1; was balanced
			p.balance = -1; //now right heavy
			break;//keep continue checking balance factors
		case 1://case R_2: was left heavy
			p.balance=0;//now balanced
			taller=false;//stop checking for balance factors
			break;
		case -1://case R_3: was right heavy
			p = insertRightBalance(p);//perform right balancing
			taller = false;//stop checking balance factors
		}
		return p;
	}

	private Node insertRightBalance(Node p) {
		Node a,b;
		a = p.rightChild;
		if(a.balance==-1)//case R_3A : Insertion in AR -> right-right imbalance
		{
			p.balance=0;
			a.balance=0;
			p=rotateLeft(p);
		}
		else //Case R_3B. Insertion AL -> right-left imbalance
		{
			b=a.leftChild;
			//all three cases of R_3B. Same rotations but different balance factors
			switch(b.balance)
			{
			case -1: //Case R_3B1:Insertion in BR
				p.balance=1;
				a.balance=0;
				break;
			case 1: //Case R_3B2: Insertion in BL
				p.balance=0;
				a.balance=-1;
				break;
			case 0: //Case R_3B3: B is the newly inserted node
				p.balance=0;
				a.balance=0;
			}
			b.balance=0;//setting the balance factor if new root to 0
			p.rightChild=rotateRight(a); //Rotate right about A
			p = rotateLeft(b); //rotate left about P
		}
		return p;
	}
	public void delete(int x)
	{
		root = delete(root,x);
	}
	private Node delete(Node p, int x)
	{
		Node ch,s;
		if(p==null)
		{
			System.out.println(x + "not found");
			shorter = false;
			return p;
		}
		if(x<p.info) //delete from the left subtree
		{
			p.leftChild = delete(p.leftChild,x);
			System.out.println("Deleted from left sub tree");
			if(shorter==true)
				p = deleteLeftSubTreeCheck(p);
		}
		else if(x>p.info)// delete from right subtree
		{
			p.rightChild = delete(p.rightChild,x);
			System.out.println("Deleted from Right sub tree");
			if(shorter==true)
				p = deleteRightSubTreeCheck(p);
		}
		else //key to be selected is found
		{
			if(p.leftChild!=null && p.rightChild!=null) //node to be deleted has 2 children
			{
				System.out.println("No child case called");
				//finding the successor for the node to be deleted. it is going to be the left most child in right subtree
				s=p.rightChild;
				while(s.leftChild!=null)
					s=s.leftChild;
				p.info = s.info; //swapping the left most node with the 
				p.rightChild=delete(p.rightChild,s.info); //deleting the new successor from the right subtree
				if(shorter==true)
					p = deleteRightSubTreeCheck(p);
			}
			else //case where 1 child or no child
			{
				System.out.println("No child case called");
				if(p.leftChild != null)//only left child
					ch=p.leftChild;
				else //only right child or no child
					ch=p.rightChild;
				p=ch; //removing the node to be deleted and replacing it with its child
				shorter = true;
			}
		}
		return p;
	}

	private Node deleteLeftSubTreeCheck(Node p) {
		switch(p.balance)
		{
		case 0://Case L_1: was balanced
			p.balance = -1; //now right heavy
			shorter=false;//no need to check for balance factors further
			break;
		case 1://Case L_2: was left heavy
			p.balance = 0;//now balanced
			//need to keep checking balance factors
			break;
		case -1: //Case L_3: was right heavy
			p = deletionRightBalance(p);//now unbalanced. Need Right balancing
		}
		return p;
	}

	private Node deletionRightBalance(Node p) {
		Node a,b;
		a = p.rightChild;//exploring the right subtree of p
		if(a.balance==0)//case L_3A
		{
			//no change in height in this case. So no change in balance factor of p in this case
			a.balance=1;
			shorter = false;//we can stop checking for balance factors
			p = rotateLeft(p);
		}
		else if(a.balance==-1)//Case L_3B
		{
			//height of the tree changes in this case. So balance factor of p changes
			p.balance=0;
			a.balance=0;
			p=rotateLeft(p);//need to keep checking for balance factors
		}
		else //Case L_3C
		{
			//it has 3 more cases based on balance factor of b(left child of a)
			b = a.leftChild;
			//all 3 cases have the same rotation but different resultant balance factors
			switch(b.balance)
			{
			case 0: //Case L_3C1
				p.balance = 0;
				a.balance = 0;
				break;
			case 1: //case L_3C2
				p.balance = 0;
				a.balance = -1;
				break;
			case -1://Case L_3C3
				p.balance = 1;
				a.balance = 0;
			}
			b.balance = 0;
			p.rightChild = rotateRight(a); //right rotation around a
			p = rotateLeft(p); //left rotation around p
		}
		return p;
	}

	private Node deleteRightSubTreeCheck(Node p) {
		switch(p.balance)
		{
		case 0://Case R_1 was balanced
			p.balance = 1;// Now left heavy
			shorter = false;//no need to check balance factors further
			break;
		case -1: //Case R_2 was right heavy
			p.balance = 0;// now balanced
			//need to keep checking for balance factors
			break;
		case 1: //was left heavy
			p = deletionLeftBalance(p);//now unbalanced. Need to perform left balancing
		}
		return p;
	}

	private Node deletionLeftBalance(Node p) {
		Node a,b;
		a = p.leftChild;
		if(a.balance==0)//case R_3A
		{
			//height remains the same in this case. So no change in balance factor of p
			a.balance = -1;
			shorter = false; //we can stop checking for balance factors further
			p = rotateRight(p);
		}
		else if(a.balance==1)//Case R_3B
		{
			//height of the tree reduces in this case, so does the balance factor of p
			p.balance = 0;
			a.balance = 0;
			p = rotateRight(p);
		}
		else //Case R_3C
		{
			//it has 3 more cases based on the balance factor of b(right child of a)
			b = a.rightChild;
			//all the 3 cases have the same rotations. Just differ in resultant balance factors
			switch(b.balance)
			{
				case 0: //case R_3C1
					p.balance = 0;
					a.balance = 0;
					break;
				case -1: //case R_3C2
					p.balance = 0;
					a.balance = 1;
					break;
				case 1: //case R_3C3
					p.balance = -1;
					a.balance = 0;
			}
			b.balance = 0;
			p.leftChild = rotateLeft(a); //rotate left about a;
			p = rotateRight(p);//rotate right about p
		}
		return p;
	}
}
