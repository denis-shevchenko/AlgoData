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

    public static int breadth(BTree t){
        int level = 0;
        int[] count = new int[t.size()];

        getBreadthRecursively(t.root(), count, level, t);

        return getMax(count);
    }
}
