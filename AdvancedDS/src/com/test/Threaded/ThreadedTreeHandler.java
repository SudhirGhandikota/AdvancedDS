package com.test.Threaded;

import java.util.Scanner;

public class ThreadedTreeHandler {

	public static void main(String[] args) {
		ThreadedBinaryTree tree = new ThreadedBinaryTree();
		int choice ,x;
		
		Scanner sc = new Scanner(System.in);
		
		while(true)
		{
			System.out.println("1. Insert a new node");
			System.out.println("2. Delete a node");
			System.out.println("3.Inorder Traversal");
			System.out.println("4. Exit");
			
			System.out.println("Enter your choice: ");
			choice = sc.nextInt();
			if(choice == 4)
				break;
			switch(choice)
			{
			case 1:
				System.out.println("Enter the key to be inserted:");
				x = sc.nextInt();
				tree.insert(x);
				break;
			case 2:
				System.out.println("Enter the key to be deleted:");
				x = sc.nextInt();
				tree.delete(x);
				break;
			case 3:
				tree.inorder();
				break;
			}
		}

	}

}
