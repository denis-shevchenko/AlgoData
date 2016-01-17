package Interfaces;

import examples.Position;

/**
 * Created by denis on 17/01/16.
 */
public interface BTree {

    public Position root();

    public int size();

    public Position parent(Position p);

    public Position leftChild(Position p);

    public Position rightChild(Position p);
}
