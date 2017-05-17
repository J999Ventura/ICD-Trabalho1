package Cliente.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteSimplesTCP {

    private Socket socket;
    private BufferedReader is;
    private PrintWriter os;

    public ClienteSimplesTCP() {
        this.socket = null;
        this.is = null;
        this.os = null;
    }

    public void openSocket(String host, int port){

        System.out.println("-> " + host + ":" + port );

        try {
            socket = new Socket(host, port);

            // Mostrar os parametros da ligação
            System.out.println("Ligação: " + socket);

            // Stream para escrita no socket
            os = new PrintWriter(socket.getOutputStream(), true);

            // Stream para leitura do socket
            is = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        }
        catch (IOException e) {
            System.err.println("Erro na ligação --> " + e.getMessage());
        }
    }

    public void closeSocket(){

        // Fechar os streams e o socket
        try {
            if (os != null) os.close();
            if (is != null) is.close();
            if (socket != null ) socket.close();
        }
        catch (IOException e) {
            System.err.println("Erro ao fechar o socket --> " + e.getMessage());
        }
    }

    public void writeSocket(String s){
        os.println(s);
        System.out.println("Cliente Enviou --> " + s);
    }

    public String readSocket(){
        try {
            return is.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

} // end ClienteSimplesTCP
