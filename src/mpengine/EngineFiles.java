package mpengine;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class EngineFiles {

    public static boolean writeBytes(File file, byte[] bytes) {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static byte[] readBytes(File file) {
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            return new byte[0];
        }
    }

    public static String[] readText(File file) {
        try {
            FileReader fileReader = new FileReader(file);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            List<String> lines = new ArrayList<String>();
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }

            bufferedReader.close();

            return lines.toArray(new String[lines.size()]);
        } catch (IOException e) {
            return new String[0];
        }
    }

    public static BufferedImage loadImage(String file) {
        try {
            return ImageIO.read(new File(file));
        } catch (IOException e) {
            return null;
        }
    }
}
