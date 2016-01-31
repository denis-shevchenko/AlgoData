package Interfaces;

/**
 * Created by denis on 29/01/16.
 */

public interface Dir {

    public File[] files();

    public Dir[] subdirs();

    public Dir parentDir();

    public String name();

    public void remove(String name);
}
