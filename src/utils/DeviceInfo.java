package utils;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @author https://www.github.com/thuongtruong1009/Snake-Game-OOP
 */
public class DeviceInfo {
    private String socketIP;
    private static InetAddress systemIP;

    public void setSocketIP(String socketIP) {
        this.socketIP = socketIP;
    }

    public String getSocketIP() throws SocketException {
        // get host address of datagram UDP socket
        try (final DatagramSocket socket = new DatagramSocket()) {
            try {
                socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            socketIP = socket.getLocalAddress().getHostAddress().toString();
        }
        return socketIP;
    }

    public void setSocketIP(InetAddress socketIP) {
        DeviceInfo.systemIP = socketIP;
    }

    public static String getSystemIP() throws SocketException {
        // get system address
        try {
            systemIP = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        // validate addressIP string to device name
        String systemIPString = systemIP.toString();
        systemIPString = systemIPString.replaceAll("[0-9]", "").replace(".", "").replace("/", "");
        return systemIPString;
    }
}