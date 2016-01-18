package Exams;

import Interfaces.BTree;
import examples.Position;

/**
 * Created by denis on 17/01/16.
 */
public class TreeMethods {
    private static BTree tree;

    private static int height(Position node){
        if(node == null)
            return 0;

        int lHeight = 0;
        int rHeight = 0;

        if(tree.leftChild(node) != null)
            lHeight = height(tree.leftChild(node));

        if(tree.rightChild(node) != null)
            rHeight = height(tree.rightChild(node));

        return Math.max(lHeight, rHeight) + 1;
    }

    private static int getMax(int[] count){
        int max = count[0];

        for(int i = 1; i < count.length; i++){
            if (count[i] > max)
                max = count[i];
        }

        return max;
    }

    private static void getBreadthRecursively(Position node, int[] count, int level, BTree t){
        if(node != null){
            count[level]++;
            if(t.leftChild(node) != null) getBreadthRecursively(t.leftChild(node), count, level, t);
            if(t.rightChild(node) != null) getBreadthRecursively(t.rightChild(node), count, level, t);
        }
    }

    private static int distRecursive(BTree t, Position root, Position to, int level){

        if(root == to)
            return level;

        if(root != null){
            level++;
            if(t.leftChild(root) != null)
                distRecursive(t, t.leftChild(root), to, level++);
            if(t.rightChild(root) != null)
                distRecursive(t, t.rightChild(root), to, level++);
        }

        return level;
    }

    private static Position LCA(BTree tree, Position root, Position p1, Position p2){
        if(root == null)
            return null;

        if(root == p1 || root == p2)
            return root;

        Position leftElement = LCA(tree, tree.leftChild(root), p1, p2);
        Position rightElement = LCA(tree, tree.rightChild(root), p1, p2);

        if(leftElement != null && rightElement != null)
            return root;

        return (leftElement != null) ? leftElement : rightElement;
    }

    public static int breadth(BTree t){
        int level = 0;
        int[] count = new int[t.size()];

        getBreadthRecursively(t.root(), count, level, t);

        return getMax(count);
    }

    public static int dist(BTree t, Position from, Position to){
        int level = 0;
        Position lca = LCA(t, t.root(), from, to);

        int distPos1 = distRecursive(t, t.root(), from, level);
        int distPos2 = distRecursive(t, t.root(), to, level);
        int distLCA = distRecursive(t, t.root(), lca, level);

        return (distPos1 + distPos2) - 2*distLCA;
    }
}
