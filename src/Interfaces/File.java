package Interfaces;

/**
 * Created by denis on 29/01/16.
 */

public interface File {

    //returns the number of 1KByte blocks of this file
    public int size();

    //returns name of this instance
    public String name();
}