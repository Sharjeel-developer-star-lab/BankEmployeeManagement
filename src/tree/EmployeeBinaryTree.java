package tree;

import model.Employee;
import java.util.Queue;
import java.util.LinkedList;

public class EmployeeBinaryTree {

    private EmployeeNode root;

    // Insert an employee using level-order insertion
    public void insert(Employee employee) {

        EmployeeNode newNode = new EmployeeNode(employee);

        // If the tree is empty, create the root
        if (root == null) {
            root = newNode;
            return;
        }

        Queue<EmployeeNode> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {

            EmployeeNode current = queue.poll();

            // Fill the left child first
            if (current.left == null) {
                current.left = newNode;
                return;
            }

            queue.add(current.left);

            // Fill the right child next
            if (current.right == null) {
                current.right = newNode;
                return;
            }

            queue.add(current.right);
        }
    }

    // Display employees using level-order traversal
    public void displayLevelOrder() {

        if (root == null) {
            System.out.println("The tree is empty.");
            return;
        }

        Queue<EmployeeNode> queue = new LinkedList<>();

        queue.add(root);

        int level = 1;

        while (!queue.isEmpty()) {

            int levelSize = queue.size();

            System.out.println("\nLevel " + level + ":");

            for (int i = 0; i < levelSize; i++) {

                EmployeeNode current = queue.poll();

                System.out.println(current.employee);

                if (current.left != null) {
                    queue.add(current.left);
                }

                if (current.right != null) {
                    queue.add(current.right);
                }
            }

            level++;
        }

    }

    // Return the height of the binary tree
    public int getHeight() {
        return calculateHeight(root);
    }

    // Calculate height recursively
    private int calculateHeight(EmployeeNode node) {

        if (node == null) {
            return 0;
        }

        return 1 + Math.max(
                calculateHeight(node.left),
                calculateHeight(node.right));
    };

    // Return the total number of employee nodes
    public int getNodeCount() {
        return countNodes(root);
    }

    // Count all nodes recursively
    private int countNodes(EmployeeNode node) {

        if (node == null) {
            return 0;
        }

        return 1 + countNodes(node.left)
                + countNodes(node.right);
    }
}