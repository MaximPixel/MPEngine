package mpengine;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

public class EngineDownloader {

    public static BufferedInputStream downloadInputStream(URL url) throws IOException {
        URLConnection con = url.openConnection();
        return new BufferedInputStream(con.getInputStream());
    }

    public static byte[] downloadBytes(URL url) throws IOException {
        BufferedInputStream in = downloadInputStream(url);

        byte[] data = new byte[in.available()];
        in.read(data);

        in.close();
        return data;
    }

    public static String downloadString(URL url) throws IOException {
        return new String(downloadBytes(url));
    }

    public static BufferedImage downloadImage(URL url) throws IOException {
        return ImageIO.read(downloadInputStream(url));
    }
}
