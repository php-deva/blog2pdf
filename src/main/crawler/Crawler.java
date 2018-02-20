package main.crawler;

import main.config.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.net.URL;

public class Crawler {
    private String url = Config.getInstance().init("core").getProperty("parseUrl");
    private String downloadFolder = System.getProperty("user.dir") + "/download/";
    private HTMLParser parser;

    public void start() {
        parser = new HTMLParser();
        this.downloadPages();
    }

    public void downloadPages() {
        Integer pageNumber = 0;
        String fileContent = null;
        while(parser.hasPostLinks(fileContent) || pageNumber == 0) {
            pageNumber++;
            fileContent = this.download(pageNumber + "", pageNumber + ".html");
        }
    }


    public Boolean hasContent() {
        return false;
    }

    /**
     * Wrapper for @method downloadByStream
     * @param  relPath relative URL for download
     * @param  fileName  page + ".html"
     * @return content of downloaded file
     */
    public String download(String relPath, String fileName) {
        String fileContent = null;
        try {
            fileContent = this.downloadByStream(url + relPath, downloadFolder + fileName);
            Thread.sleep(2000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return fileContent;
    }

    /**
     * Download file by url, save file to local folder
     * @param  urlStr absolute URL for download
     * @param  path file path
     * @return content of downloaded file
     */
    private String downloadByStream(String urlStr, String path) throws IOException {
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(path);
        /*
        BufferedReader br = new BufferedReader(
                new InputStreamReader(bis, StandardCharsets.UTF_8));
                */
        byte[] buffer = new byte[1024];
        String fileContent = null;
        String buffContent = null;
        int count = 0;

        while ((count = bis.read(buffer, 0, 1024)) != -1) {
            buffContent = new String(buffer);
            fileContent += buffContent;
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
        return fileContent;
    }
}

