import java.net.*;
import java.util.Date;

public class UDPServer {
    public static void main(String[] args) {
        int port = 1250;

        try {
            DatagramSocket serverSocket = new DatagramSocket(port);

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                String dateAndTime = new Date().toString();
                byte[] sendData = dateAndTime.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);

                serverSocket.send(sendPacket);
                System.out.println("Réponse envoyée au client : " + dateAndTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
