import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by mario on 17/08/14.
 */
public class Conections {

    /**
     * Opens the client comunication and connection with the server
     * IP and the Port number received in the parameters.
     *
     * @param IP            Ip addres to stablish communication with Server
     * @param portNumber    Port Number to open the communication with the Server
     */
    public void clientCom(String IP, String portNumber){
        Socket client;
        Integer port = Integer.parseInt(portNumber);

        if(!IP.isEmpty()){
            try {
                InetAddress IPAddress = InetAddress.getByName(IP);
                client = new Socket(IPAddress, port);
            } catch (UnknownHostException ue) {
                System.err.println("ERROR: IP addres could not be determined. Exception caused by: " + ue);
            } catch (IOException ioe){
                System.err.println("ERROR: Socket connection failed. Exception caused by: " + ioe);
            }
        }
    }

    /**
     * Opens the Server communication and connection with the server
     * Port Number received in the parameter
     * @param portNumber    Port Number to open the communicacion with the Client.
     */
    public void serverCom(String portNumber){
        Integer port = portNumber.isEmpty()? 4502: Integer.parseInt(portNumber);
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            System.out.println("Conection Accepted");
        } catch (IOException ioe) {
            System.err.println("ERROR: Socket connection failed. Exception caused by: " + ioe);
        }

    }
}
