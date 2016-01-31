package Exams;

import Interfaces.Dir;
import Interfaces.File;

/**
 * Created by denis on 29/01/16.
 */

public class Directory implements Dir {

    @Override
    public File[] files() {
        return new File[0];
    }

    @Override
    public Dir[] subdirs() {
        return new Dir[0];
    }

    @Override
    public Dir parentDir() {
        return null;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public void remove(String name) {

    }

    public static int size = 0;
    public static int dirsCount = 0;
    public static int filesCount = 0;



    static void removeAll(Dir d) {
        delete(d);
        System.out.println("released totally "+size+" KBytes in "+filesCount+" files and "+dirsCount+" subdirectories");
    }

    static void delete(Dir d){
        Dir[] subdirs = d.subdirs();
        File[] files = d.files();

        if (subdirs.length > 0)
            for(int i = 0; i < subdirs.length; i++){
                delete(subdirs[i]);
                d.remove(subdirs[i].name());
                dirsCount++;
            }

        if(files.length > 0)
            for(int i = 0; i < files.length; i++){
                size += files[i].size();
                d.remove(files[i].name());
                filesCount++;
            }

    }

    public static void main(String[] args) {
        Dir directory = new Directory();

        removeAll(directory);
    }

}
