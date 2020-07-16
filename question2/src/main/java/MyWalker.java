import org.apache.commons.cli.*;
import jdk.jfr.internal.Options;
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
    Options options = new Options();

    private CommandLine commandLine;
    private CommandLineParser commandLineParser;

    private Boolean hasA = false;
    private Boolean hasB = false;
    private Boolean hasC = false;
    private Boolean hasD = false;
    private Boolean hasExt = false;
    private Boolean hasF = false;
    private Boolean hasH = false;

    public  MyWalker(){
        super();
        this.commandLineParser = new DefaultParser();
        this.options.addOption("a", "total-num-files", false, "The total number of files.");
        this.options.addOption("b", "total-num-dirs", false, "Total number of directory.");
        this.options.addOption("c", "total-unique-exts", false, "Total number of unique file extensions.");
        this.options.addOption("d", "list-exts", false, "List all unique file extensions.");
        this.options.addOption("h", "help", false, "List all available arguments.");

        Option extensionNumOption = Option.builder("e")
                .longOpt("num-ext").argName("EXT").hasArg().desc("List total number of file for specified extension EXT.")
                .valueSeparator('=').build();

        Option pathOption = Option.builder("f")
                .valueSeparator('=')
                .argName("path-to-folder").hasArg().desc("Path to the documentation folder. This is a required argument.")
                .build();

        this.options.addOption(extensionNumOption);
        this.options.addOption(pathOption);
    }

    public void run(String[] args){
        takeArguments(args);
        MyActualWalker();
        printer();
    }

    private void takeArguments(String[] args){
        try {
            commandLine = commandLineParser.parse(options,args);
        } catch (ParseException e) {
            System.out.println("Incorrect arguments.");
            printer();
            System.exit(1);
        }
        if(commandLine.hasOption("a")) hasA = true;
        if(commandLine.hasOption("b")) hasB = true;
        if(commandLine.hasOption("c")) hasC = true;
        if(commandLine.hasOption("d")) hasD = true;
        if(commandLine.hasOption("e")) hasExt = true;
        if(commandLine.hasOption("f")) hasF = true;
        if(commandLine.hasOption("h")) hasH = true;
        if(hasH){
            printer();
            System.exit(0);
        }
    }

    public void MyActualWalker() {
        if(hasF){
            try{
                //Hard coded the path
                walk(new File(commandLine.getOptionValue("f")), null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Given path is wrong, please retype your arguments and try again.");
            printer();
            System.exit(1);
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
    private void printer(){
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("WalkerCLI",options);
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
