import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by mario on 17/08/14.
 */
public class Conections {

    private Socket clientTCPSocket, serverTCPSocket;
    private InputStream clientInputStream, serverInputStream;
    private OutputStream clientOutputStream, serverOutputStream;

    /**
     * Opens the client comunication and connection with the server
     * IP and the Port number received in the parameters.
     *
     * @param IP            Ip addres to stablish communication with Server
     * @param portNumber    Port Number to open the communication with the Server
     */
    public boolean clientCom(String IP, String portNumber){
        Integer port = Integer.parseInt(portNumber);
        if(!IP.isEmpty()){
            try {
                InetAddress IPAddress = InetAddress.getByName(IP);
                clientTCPSocket = new Socket(IPAddress, port);
                System.out.println("Success connection");
                return true;
            } catch (UnknownHostException ue) {
                System.err.println("ERROR: IP addres could not be determined. Exception caused by: " + ue);
            } catch (IOException ioe){
                System.err.println("ERROR: Socket connection failed. Exception caused by: " + ioe);
            }
        }
        return false;
    }

    /**
     * Opens the Server communication and connection with the server
     * Port Number received in the parameter
     * @param portNumber    Port Number to open the communicacion with the Client.
     */
    public boolean serverCom(String portNumber) {
        Integer port = portNumber.isEmpty() ? 4502 : Integer.parseInt(portNumber);
        boolean status = false;
        try {
            if (available(port)) {
                ServerSocket serverSocket = new ServerSocket(port);
                serverTCPSocket = serverSocket.accept();
                System.out.println("Conection Accepted");
                status = true;
            } else {
                System.err.println("Port " + port + " is used on another app");
                System.err.println("Please use another port");
            }
            return status;
        } catch (IOException ioe) {
            System.err.println("ERROR: Socket connection failed. Exception caused by: " + ioe);
        }
        return status;
    }

    /**
     * Checks if the port is opened from other applications.
     *
     * @param port
     * @return
     */
    public static boolean available(int port) {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(port);
            ss.setReuseAddress(true);
            return true;
        } catch (IOException e) {
        } finally {
            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                /* should not be thrown */
                }
            }
        }

        return false;
    }

    /**
     * Return an inputStream from the socket in was made the connection
     * @return InputStream of the client from the connexion.
     */
    public InputStream getClientInputStream() throws IOException{
        return clientTCPSocket.getInputStream();
    }

    /**
     * Return an outputStream from the socket in was made the connection
     * @return OutputStream of the client from the connexion.
     */
    public OutputStream getClientOutputStream() throws IOException{
        return clientTCPSocket.getOutputStream();
    }

    /**
     * Return an inputStream from the socket in was made the connection
     * @return InputStream of the server from the connexion.
     */
    public InputStream getServerInputStream() throws IOException{
        return serverTCPSocket.getInputStream();
    }

    /**
     * Return an outputStream from the socket in was made the connection
     * @return OutputStream of the server from the connexion.
     */
    public OutputStream getServerOutputStream() throws IOException{
        return serverTCPSocket.getOutputStream();
    }
}
