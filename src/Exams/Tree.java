package Exams;

import Interfaces.BTree;
import examples.Position;

/**
 * Created by denis on 17/01/16.
 */
public class Tree implements BTree {
    @Override
    public Position root() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Position parent(Position p) {
        return null;
    }

    @Override
    public Position leftChild(Position p) {
        return null;
    }

    @Override
    public Position rightChild(Position p) {
        return null;
    }

    @Override
    public boolean isExternal(Position p) {
        return false;
    }

    @Override
    public int height() {
        return 0;
    }
}
