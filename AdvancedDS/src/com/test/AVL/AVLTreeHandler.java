package com.test.AVL;
import java.util.Scanner;

public class AVLTreeHandler {

	public static void main(String[] args) {
		AVLTree tree = new AVLTree();
		int choice,x;
		
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			System.out.println("1. Display Tree");
			System.out.println("2. Insert a new node");
			System.out.println("3. Delete a node");
			System.out.println("4. Inorder traversal");
			System.out.println("5. Quit");
			System.out.println("Enter your Choice");
			choice = sc.nextInt();
			if (choice==5)
				break;
			switch(choice)
			{
			case 1:
				tree.display();
				break;
			case 2:
				System.out.print("enter the key to be inserted:");
				x = sc.nextInt();
				tree.insert(x);
				break;
			case 3:
				System.out.print("enter the key to be deleted:");
				x=sc.nextInt();
				tree.delete(x);
				break;
			case 4:
				tree.inorder();
				break;
			} //end of switch
		} //end of while
		sc.close();
	}
}
