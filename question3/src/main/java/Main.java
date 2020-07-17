
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String URL = "https://cs.brown.edu/courses/cs033/docs/guides/x64_cheatsheet.pdf";
        String saveAt = "/Users/kriangsak1997/Desktop";
        Downloader downloader = new Downloader();

//        downloader.apacheCommon(URL,saveAt);
//        downloader.HttpClient(URL,saveAt);
        downloader.download(URL,saveAt);
    }


}
