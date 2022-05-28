package files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import container.Frame;
import container.GamePanel;
import utils.DeviceInfo;

/**
 * @author https://www.github.com/thuongtruong1009/Snake-Game-OOP
 */
public class Write {
    private final static ArrayList<Item> items = new ArrayList<>();
    private static final DefaultListModel<String> listOfProduct = new DefaultListModel<String>();
    public static final String fileData = "./src/files/writeData.txt";

    public static void getData(int score, String time, String duration) {
        String scoreInt = String.valueOf(score);
        String timeText = time;
        String durationText = String.valueOf(duration);

        // add new item
        Item item = new Item(scoreInt, timeText, durationText);
        items.add(item);

        // add items to list model
        try {
            printProduct();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        writeInfor(String.valueOf(listOfProduct), Write.fileData);
    }

    public static void writeInfor(String listOfProduct, String fileURL) {
        try {
            // create and link thread
            File file = new File(fileURL);
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);

            // write data
            fileWriter.write(listOfProduct);

            // close thread
            fileWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Error write file: " + ex.toString());
            System.out.println(ex.getMessage());
        }
    }

    private static void printProduct() throws SocketException {
        listOfProduct.addElement(
                "Level: " + Frame.SPEED + "    |   " + "Score: " + items.get(listOfProduct.size()).getScore() + "  |   "
                        + "Duration: " + items.get(listOfProduct.size()).getDuration() + "s" + "  |   " + "Key pressed: "
                        + GamePanel.keyPress + "  |   " + "Date: " + items.get(listOfProduct.size()).getTime() + "   |   " + "Device: " + DeviceInfo.getSystemIP() + " ");
    }
}