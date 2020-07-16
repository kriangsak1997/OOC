
import org.apache.commons.cli.*;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        String[] args2 = {"-f", "/Users/kriangsak1997/Documents/MUIC/Term9/ICCS330_OOC/Assignments/homework1/question1/docs/", "-b", "-a", "-c", "-d", "-e", "java"};
        Options options = getOptions();
        CommandLine cmd =null;
        try{
            CommandLineParser parser = new DefaultParser();
            cmd = parser.parse(options,args2);

        }catch (ParseException e){
            System.out.println(e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("Walker", options);
            System.exit(1);
        }

        if (cmd.hasOption("h")){
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("Walker", options);
            System.exit(0);
        }

        MyWalker myWalker = new MyWalker();
        try{
            myWalker.setStartingDir(cmd.getOptionValue("f"));
        }catch (FileNotFoundException e){
            System.out.println("Path required");
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("Walker", options);
            System.exit(1);
        }
        myWalker.setHasA(cmd.hasOption("a"));
        myWalker.setHasB(cmd.hasOption("b"));
        myWalker.setHasC(cmd.hasOption("c"));
        myWalker.setHasD(cmd.hasOption("d"));
        myWalker.setHasE(cmd.hasOption("e"));
        myWalker.setTargetExt(cmd.getOptionValue("e"));

        myWalker.start();

        myWalker.printResults();

    }

    private static Options getOptions() {
        Options options = new Options();
        options.addOption("a", "--total-num-files ", false, "The total number of files");
        options.addOption("b", "--total-num-dirs ", false, "Total number of directory");
        options.addOption("c", "--total-unique-exts", false, " Total number of unique file extensions.");
        options.addOption("d", "--list-exts", false, "List all unique file extensions. Do not list duplicates.");
        options.addOption("e","ext",true,"List total number of file for specified extension EXT.");
        options.addOption("f","path",true,"Path to the documentation folder. This is a required argument.");
        return options;
    }
}