import org.apache.commons.io.DirectoryWalker;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MyWalker extends DirectoryWalker {
    private int countFiles;
    private int countDirectories;
    private Map<String, Integer> extensions = new HashMap<>();
    private String path = "/Users/kriangsak1997/Documents/MUIC/Term9/ICCS330_OOC/Assignments/hw1/q1/docs/";


    public MyWalker() {
        super();
        try {

            walk(new File(path), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void handleFile(File file, int depth, Collection results) {
        countFiles++;
        String ext = FilenameUtils.getExtension(String.valueOf(file));
        if (!extensions.containsKey(ext)) {
            extensions.put(ext, 1);
        } else {
            extensions.put(ext, extensions.get(ext) + 1);
        }
    }


    @Override
    protected boolean handleDirectory(File directory, int depth, Collection results) throws IOException {
        countDirectories++;
        return true;
    }

    private void printNumFiles() {
        System.out.println("Total number of files: " + countFiles);
    }

    private void printNumDirectories() {
        System.out.println("Total number of directories: " + countDirectories);
    }

    private void printNumUniques() {
        System.out.println("Total number of unique extensions: " + extensions.size());
    }

    private void printUnique() {
        System.out.println("Unique extensions and the respective counts");
        for (String ext : extensions.keySet()) {
            System.out.println(ext + " : " + extensions.get(ext));
        }
    }


    public void go() {

        printNumFiles();
        System.out.println(" ");


        printNumDirectories();

        System.out.println(" ");
        printNumUniques();

        System.out.println(" ");
        printUnique();
        System.out.println(" ");

    }

}
