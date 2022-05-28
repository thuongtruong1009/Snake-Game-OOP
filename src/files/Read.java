package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import root.Root;

/**
 * @author https://www.github.com/thuongtruong1009/Snake-Game-OOP
 */
public class Read {
    public static final String fileURL = "./src/files/dataScore.txt";
    Root root = new Root();

    public void readInfor(String fileURL) {
        try {
            // create and link thread
            File f = new File(fileURL);
            FileReader fr = new FileReader(f);

            // write data
            BufferedReader br = new BufferedReader(fr);

            // write header
            String decorate = "----------";
            Write.writeInfor("\t\t\t" + decorate + root.frameTitle + " timeline" + decorate + "\n\n", Read.fileURL);

            // write body
            String line;
            while ((line = br.readLine()) != null) {
                Write.writeInfor(String.valueOf(line.replace(", ", "][").replace("]", "]\n")), Read.fileURL);
                System.out.println(line);
            }

            // close thread
            fr.close();
            br.close();
        } catch (

        IOException ex) {
            ex.printStackTrace();
            System.out.println("Error write file: " + ex.toString());
            System.out.println(ex.getMessage());
        }
    }
}