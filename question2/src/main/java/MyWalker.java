import org.apache.commons.cli.*;
//import jdk.jfr.internal.Options;
import org.apache.commons.io.DirectoryWalker;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MyWalker extends DirectoryWalker {

    private boolean hasA;
    private boolean hasB;
    private boolean hasC;
    private boolean hasD;
    private boolean hasE;
    private String targetExt;
    private File startingDir;

    private int numFile;
    private int numDir;
    private  Map<String, Integer> extentions = new HashMap<String, Integer>();

    public MyWalker(){
        super();
    }

    public void start(){
        try{
            walk(startingDir,null);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void printResults(){
        printNumFile();
        printNumDir();
        printNumUnique();
        printListFileExt();
        printFilesForEachExt(this.targetExt);
    }
    public void setHasA(boolean hasA) {
        this.hasA = hasA;
    }

    public void setHasB(boolean hasB) {
        this.hasB = hasB;
    }

    public void setHasC(boolean hasC) {
        this.hasC = hasC;
    }

    public void setHasD(boolean hasD) {
        this.hasD = hasD;
    }

    public void setHasE(boolean hasE) {
        this.hasE = hasE;
    }

    public void setTargetExt(String targetExt) {
        this.targetExt = targetExt;
    }
    public void setStartingDir(String startingDir) throws FileNotFoundException{
        this.startingDir = new File(startingDir);
        if(!this.startingDir.exists()|| !this.startingDir.isDirectory()){
            throw new FileNotFoundException("Unable to locate the directory");
        }
    }
    @Override
    protected boolean handleDirectory( File directory, int depth, Collection results){
        if(hasB){
            numDir++;
        }
        return  true;
    }

    @Override
    protected void handleFile(File file, final int depth, final Collection results){
        if(hasA){
            numFile++;
        }
        if(hasC || hasD || hasE){
            String ext = FilenameUtils.getExtension(file.getName());
            if(ext.equals("")) ext = "File";
            if(!extentions.containsKey(ext)){
                extentions.put(ext,1);
            }else{
                int val = extentions.get(ext);
                extentions.put(ext,val+1);
            }
        }
    }


    private void printFilesForEachExt(String targetExt) {
        if(hasE){
            System.out.println(targetExt + extentions.get(targetExt));
        }
    }

    private void printListFileExt() {
        if(hasD){
            for (String ext: extentions.keySet()){
                System.out.println(ext);
            }
        }
    }

    private void printNumUnique() {
        if(hasC){
            System.out.println("Total number of unique extensions: " + extentions.size());
        }

    }

    private void printNumDir() {
        if(hasB){
            System.out.println("Total number of directories: " + numDir);
        }
    }

    private void printNumFile() {
        if(hasA){
            System.out.println("Total number of files: " + numFile);
        }

    }

}
