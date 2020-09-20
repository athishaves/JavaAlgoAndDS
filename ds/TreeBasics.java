package ds;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TreeBasics {

    public class Node {
        int data;
        Node left, right;
        Node(int data) { this.data = data; }
    }


    // Height of a binary tree
    public static int heightOfTree(Node root) {
        if(root==null) return 0;
        return Math.max(heightOfTree(root.left), heightOfTree(root.right)) + 1;
    }


    // No of leaves in a binary tree
    public static int noOfLeaves(Node root) {
        if(root==null) return 0;
        if(root.left==null && root.right==null) return 1;
        return noOfLeaves(root.left) + noOfLeaves(root.right);
    }


    // No of non-leaf nodes in a binary tree
    public static int noOfNonLeafNodes(Node root) {
        if(root==null) return 0;
        if(root.left==null && root.right==null) return 0;
        return noOfNonLeafNodes(root.left) + noOfNonLeafNodes(root.right) + 1;
    }


    // No of nodes
    public static int noOfNodes(Node root) {
        if(root==null) return 0;
        return noOfNodes(root.left) + noOfNodes(root.right) + 1;
    }


    // Minimum element in Binary Search Tree
    public static int minElement(Node root) {
        if(root.left==null) return root.data;
        return minElement(root.left);
    }


    // Maximum element in Binary Search Tree
    public static int maxElement(Node root) {
        if(root.right==null) return root.data;
        return minElement(root.right);
    }


    // Inorder traversal
    public static void inorder(Node root) {
        if(root==null) return;
        inorder(root.left);
        System.out.println(root.data);
        inorder(root.right);
    }


    // Preorder traversal
    public static void preorder(Node root) {
        if(root==null) return;
        System.out.println(root.data);
        preorder(root.left);
        preorder(root.right);
    }


    // Postorder traversal
    public static void postorder(Node root) {
        if(root==null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.println(root.data);
    }


    // Sum of binary tree
    public static int sumOfBinaryTree(Node root) {
        if(root==null) return 0;
        return sumOfBinaryTree(root.left) + sumOfBinaryTree(root.right) + root.data;
    }


    // Height of a heap
    public static int heightOfHeap(int n) {
        return (int) Math.ceil(Math.log(n+1) / Math.log(2)) - 1;
    }

}
