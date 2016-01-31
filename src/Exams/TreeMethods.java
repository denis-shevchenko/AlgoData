package Exams;

import Interfaces.BTree;
import examples.Locator;
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

    private static Position[] bTreeArray;

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
            if(t.leftChild(node) != null)
                getBreadthRecursively(t.leftChild(node), count, level+1, t);
            if(t.rightChild(node) != null)
                getBreadthRecursively(t.rightChild(node), count, level+1, t);
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
        if(root != null){
            if(root == p1 || root == p2)
                return root;

            Position leftElement = LCA(tree, tree.leftChild(root), p1, p2);
            Position rightElement = LCA(tree, tree.rightChild(root), p1, p2);

            if(leftElement != null && rightElement != null)
                return root;

            return (leftElement != null) ? leftElement : rightElement;
        }
        return null;
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

    public static void printCodeTabel(BTree t){

    }

    private static void printCodeTabel(BTree t, Position p, String string){
        if(t.isExternal(p)){
            System.out.println(string);
        }else {
            printCodeTabel(t, t.leftChild(p), string + "0");
            printCodeTabel(t, t.rightChild(p), string + "1");
        }
    }

    private static int[] internalNodes;

    public static int[] getDepthCounts(BTree t){
        int depth = 0;
        internalNodes = new int[t.size()];
        depthCounts(t, t.root(),depth);
        return internalNodes;
    }

    private static void depthCounts(BTree t, Position root, int depth) {
        if(!t.isExternal(root)){
            internalNodes[depth] = depth;
            depthCounts(t, t.leftChild(root), depth++);
            depthCounts(t, t.rightChild(root), depth++);
        }
    }

    public static Position[] toBArray(BTree t){
        bTreeArray = new Position[2^t.height()-1];
        bTreeArray[0] = null;
        toBArray(t, 1, t.root());

        return bTreeArray;
    }

    private static void toBArray(BTree tree, int pos, Position node){
        bTreeArray[pos] = node;
        if(!tree.isExternal(node)){
            toBArray(tree, 2*pos, tree.leftChild(node));
            toBArray(tree, 2*pos+1, tree.rightChild(node));
        }
    }
}
