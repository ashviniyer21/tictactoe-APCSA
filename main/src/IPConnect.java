import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class IPConnect {
    private ServerSocket serverSocket;
    private Socket socket;
    public String getIP() throws UnknownHostException {
        return Inet4Address.getLocalHost().getHostAddress();
    }
    public boolean connectSocket(int port, String ip) {
        if(serverSocket != null){
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        try {
            System.out.println("Waiting to find connection");
            socket = new Socket(ip, port);
            System.out.println("Connected Successfully");
        } catch (IOException e) {
            System.out.println("Connection Failed");
            return false;
        }
        return true;
    }
    public boolean hostSocket(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Waiting to find connection");
            socket = serverSocket.accept();
            System.out.println("Connected Successfully");
        } catch (IOException e) {
            System.out.println("Connection Failed");
            return false;
        }
        return true;
    }

    public String getMessage() throws IOException {
        return new DataInputStream(new BufferedInputStream(socket.getInputStream())).readUTF();
    }

    public void sendMessage(String s) throws IOException {
        new DataOutputStream(socket.getOutputStream()).writeUTF(s);
    }

    public void killSocket() throws IOException {
        if(socket != null){
            socket.close();
        }
    }
}