package edu.finplatjavacourse.distributeddbprototype.processing.searchservice.idsearch;

public class AVLTree {
    public Node root;

    int height(Node N) {
        if (N == null)
            return 0;
        return N.getHeight();
    }
    private Node searchHelper(Node root, int id) {
        if (root == null || root.getId() == id)
            return root;
        if (id < root.getId())
            return searchHelper(root.left, id);
        return searchHelper(root.right, id);
    }
    public Long search(int id){
        Node findNode=this.searchHelper(this.root,id);
        if(findNode!=null)
            return findNode.getStrNumber();
        else
            return Long.valueOf(-1);
    }

    public Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        x.setHeight(Math.max(height(x.left), height(x.right)) + 1);
        y.setHeight(Math.max(height(y.left), height(y.right)) + 1) ;

        return x;
    }

    public Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.setHeight(Math.max(height(x.left), height(x.right)) + 1);
        y.setHeight(Math.max(height(y.left), height(y.right)) + 1);

        return y;
    }

    public int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }


    public Node insert(Node node, Long key, Long StrNumber) {
        if (node == null)
            return (new Node(key,StrNumber));

        if (key < node.getId())
            node.left = insert(node.left, key,StrNumber);
        else if (key > node.getId())
            node.right = insert(node.right, key,StrNumber);
        else
            return node;

        node.setHeight(1 +  Math.max(height(node.left),height(node.right)));

        int balance = getBalance(node);

        if (balance > 1 && key < node.left.getId())
            return rightRotate(node);

        if (balance < -1 && key > node.right.getId())
            return leftRotate(node);

        if (balance > 1 && key > node.left.getId()) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && key < node.right.getId()) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.getId() + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

}
