import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;
import java.net.URL;

public class Downloader {

    //First way (probably the simplest)
    void apacheCommon(String fileURL, String saveAt) throws IOException {
        String saveFile = saveAt + "/" + "downloadedFile";
        FileUtils.copyURLToFile(new URL(fileURL),
                new File(saveFile));
        System.out.println("Download Complete. \n" +
                "File location: " + saveFile);
    }

    //Second way
    void HttpClient(String url, String location) {
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            int responseCode = response.getStatusLine().getStatusCode();

            System.out.println("Request Url: " + request.getURI());
            System.out.println("Response Code: " + responseCode);

            InputStream is = entity.getContent();
            String file = location + "/downloadedWay2";
            FileOutputStream fos = new FileOutputStream(new File(file));

            int inByte;
            while ((inByte = is.read()) != -1) {
                fos.write(inByte);
            }

            is.close();
            fos.close();

            client.close();
            System.out.println("Download Completed!!!\n" +
                    "File stored at: " + file);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Third way

        public void download(String url, String filePath){
            try (BufferedInputStream inputStream = new BufferedInputStream(new URL(url).openStream())) {
                try (FileOutputStream fileOS = new FileOutputStream(filePath +"/downloadedFile")) {

                    byte data[] = new byte[1024];
                    int byteContent;

                    while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
                        fileOS.write(data, 0, byteContent);
                    }

                    fileOS.close();
                    inputStream.close();

                    System.out.println("Download Completed");
                }
            } catch (IOException e) {
                System.out.println("Cannot perform download due to IOExeption. Please change the URL or download path.");
            }
    }

}
