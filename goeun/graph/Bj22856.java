package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj22856 {

    static int cnt = 0;

    static class Tree {
        Node root;

        public Tree() {
            this.root = null;
        }

        public void add (int data, int leftData, int rightData) {
            if (root == null) {
                root = new Node(data);
                root.left = leftData == -1 ? null : new Node(leftData);
                root.right = rightData == -1? null : new Node(rightData);
            } else {
                addChild(root, data, leftData, rightData);
            }
        }

        public void addChild (Node cur, int data, int leftData, int rightData) {
            if (cur == null) return;
            else if (cur.data == data) {
                cur.left = leftData == -1 ? null : new Node(leftData);
                cur.right = rightData == -1? null : new Node(rightData);
            } else {
                addChild(cur.left, data, leftData, rightData);
                addChild(cur.right, data, leftData, rightData);
            }
        }

        // 전위 탐색 : 현재 -> 왼쪽 -> 오른쪽
        public void preOrder(Node cur) {
            System.out.println(cur.data);
            if (cur.left != null) preOrder(cur.left);
            if (cur.right != null) preOrder(cur.right);
        }

        // 중위 탐색 : 왼쪽 -> 현재 -> 오른쪽
        public void inOrder(Node cur) {
            if (cur.left != null) inOrder(cur.left);
            System.out.println(cur.data);
            if (cur.right != null) inOrder(cur.right);
        }

        // 후위 탐색 : 왼쪽 -> 오른쪽 -> 현재
        public void postOrder(Node cur) {
            if (cur.left != null) inOrder(cur.left);
            if (cur.right != null) inOrder(cur.right);
            System.out.println(cur.data);
        }

        // 유사 중위 탐색 : 왼쪽 -> 현재 -> 오른쪽
        public void similarInOrder(Node cur) {
            if (cur == null) return;
            cnt++;
            similarInOrder(cur.left);
            similarInOrder(cur.right);
        }

    }

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Tree tree = new Tree();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int data = Integer.parseInt(st.nextToken());
            int leftData = Integer.parseInt(st.nextToken());
            int rightData = Integer.parseInt(st.nextToken());
            tree.add(data, leftData, rightData);
        }

        tree.similarInOrder(tree.root);

    }

}
