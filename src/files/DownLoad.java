package files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import root.Root;

public class DownLoad {
    public static long relativeDownload(String url, String fileName) throws IOException, IllegalArgumentException {
        // url must be relative, such as internal directly
        File f = new File(url);
        File relativeTo = new File("");
        String temp = new File(relativeTo.toPath().resolve(f.toPath()).toUri()).toString().replace("\\", "/")
                .replace(" ", "%20");
        temp = "file:///" + temp.substring(0, 2) + "/" + temp.substring(3);

        try (InputStream in = URI.create(temp).toURL().openStream()) {
            return Files.copy(in, Paths.get(fileName));
        }
    }

    // suitable for using http link on broswer, then you must replace URL root path
    public static long absoluteDownload(String url, String fileName) throws IOException, IllegalArgumentException {
        // url must be absolute link, such as http link
        try (InputStream in = URI.create(url).toURL().openStream()) {
            return Files.copy(in, Paths.get(fileName));
        }
    }

    public static void downloadSuccess() throws IOException {
        Root root = new Root();
        JOptionPane.showMessageDialog(null, root.downloadDisc, root.downloadTitle, JOptionPane.INFORMATION_MESSAGE,
                root.download);
        relativeDownload(root.downloadURL, root.downloadAddress);
    }
}