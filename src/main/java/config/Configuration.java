package config;

import structures.BinarySearchTree;
import structures.BinaryTreeNode;

/**
 * This class acts as a configuration file which tells the testing framework
 * which implementation you want us to use when we grade your assignment.
 * @author jcollard jddevaug
 */
public class Configuration {

    public static <T> BinaryTreeNode<T> createBinaryTreeNode(BinaryTreeNode<T> left, T elem, BinaryTreeNode<T> right){
        return null;
    }

    public static BinarySearchTree createBinarySearchTree(){
        return null;
    }
}
