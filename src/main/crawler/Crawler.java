package crawler;

import main.config.*;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;


public class Crawler {
    private String url = Config.getInstance().init("core").getProperty("parseUrl");
    private String downloadFolder = System.getProperty("user.dir") + "/download/";
    private Integer totalPages = 0;

    public void run() {
        for (int page = 1; page <= 6; page = page + 1) {
            try {
                this.downloadByStream(url + page, downloadFolder + page + ".html");
                Thread.sleep(2000);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Boolean hasContent() {
        return false;
    }

    private void downloadByStream(String urlStr, String fileName) throws IOException {
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(fileName);
        byte[] buffer = new byte[1024];
        int count = 0;
        while ((count = bis.read(buffer, 0, 1024)) != -1) {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }
}

