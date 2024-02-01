# <code>DSp6BST2</code> Binary Search Tree Assignment Part 2

## Overview
For this assignment you will be implementing the core of the Binary Search Tree ADT.
This project is based on the content of chapters 9 and 10 in the Java Software Structures book.

### Table of Contents
**[Files to complete](#files-to-complete)**<br>
**[Test files](#test-files)**<br>
**[Part One: Import Project](#part-one-import-project)**<br>
**[Part Two: Copy the BinaryTreeNode Interface](#part-two-copy-the-binarytreenode-interface)**<br>
**[Part Three: Implement the BinarySearchTree Interface](#part-three-implement-the-binarysearchtree-interface)**<br>
**[Part Four: Commit Project and Submit Pull Request](#part-four-commit-project-and-submit-pull-request)**


## Files to complete
You are expected to write an implementation for the `BinarySearchTree` interface in the `Configuration` class.
You can use your implementation of the `BinaryTreeNode` interface from part 1.

### Test files
In the test folder, you are provided with several JUnit test cases that will help you keep on track while completing
this assignment. It will help you to run the tests often and use them as a checklist of things to do next.
Please do not modify my test cases, but you may add your own JUnit classes to fill out the test suite.

## Part One: Import Project 
When you clone / download your project from GitHub Classroom, you will want to ensure that your project should have no errors and contain the following root items:

**src** - The source folder where all code you are submitting must go. You can change anything you want in this folder, you can add new files, etc...<br>
**test** - The test folder where all the public unit tests are available<br>
**support** - This folder contains support code for you to use. Be very careful if you choose to change files in this folder.<br>
**JUnit 5** - A library that is used to run the test programs<br>
**JRE System Library** - This is what allows java to run<br>

If you are missing any of the above or errors are present in the project, seek help immediately, so you can get started.


## Part Two: Copy the BinaryTreeNode Interface
Use your `BinaryTreeNode` implementation from part 1 and update the `Configuration` file.

## Part Three: Implement the BinarySearchTree Interface
The `BinarySearchTree` interface provides basic functions for implementing a binary search tree.

**Transformers** -- Add and remove are the main transformers of a BST.  These must add or remove elements
while maintaining the BST property.

**Observers** -- You will implement an `isEmpty()` method and a `size()` method.  In addition,
you will implement the `getMinimum()` and `getMaximum()` functions, which return the smallest
and largest values stored in the BST.


## Part Four: Commit Project and Submit Pull Request
When you have finished your solution and are ready to submit, make your final commit and push everything up to GitHub.
