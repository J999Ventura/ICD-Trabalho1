package Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//import java.util.Scanner;

public final class ServidorTCPConcorrente {

    //implementacao singleton
    private static final ServidorTCPConcorrente INSTANCE = new ServidorTCPConcorrente();

    private final static int DEFAULT_PORT = 5025;

    private ServerSocket serverSocket;
    private Socket newSock;
    private int port;

    public ServidorTCPConcorrente() {
        this.serverSocket = null;
        this.newSock = null;
        this.port = DEFAULT_PORT;
    }

    public static ServidorTCPConcorrente getInstance() {
        return INSTANCE;
    }

    private void initServerSocket(){
        try {
            serverSocket = new ServerSocket(port);

            for( ; ; ) {
                System.out.println("Servidor TCP concorrente aguarda ligacao no porto " + port + "..." );

                // Espera connect do cliente
                newSock = serverSocket.accept();

                Thread th = new HandleConnectionThread(newSock);
                th.start();
            }
        }
        catch (IOException e) {
            System.err.println("Excepção no servidor: " + e);
        }
    }

    public static void main(String[] args)
    {
        ServidorTCPConcorrente servTCP = new ServidorTCPConcorrente();
        servTCP.initServerSocket();

    } // end main

} // end ServidorTCP



class HandleConnectionThread extends Thread {

    private Socket connection;
    private BufferedReader is;
    private PrintWriter os;

    HandleConnectionThread(Socket connection) {
        this.connection = connection;
        this.is = null;
        this.os = null;
    }

    public void run() {
        openSocket();
        readSocket();
        closeSocket();

    } // end run

    private void openSocket(){

        try {
            // circuito virtual estabelecido: socket cliente na variavel newSock
            System.out.println("Thread " + this.getId() + ": " + connection.getRemoteSocketAddress());

            is = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            os = new PrintWriter(connection.getOutputStream(), true);

        }
        catch (IOException e) {
            System.err.println("erro na liga�ao " + connection + ": " + e.getMessage());
        }
    }

    private String readSocket(){
        try {
            String msg = is.readLine();
            System.out.println("servidor recebeu --> " + msg);
            return msg;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void writeSocket(String s){
        os.println(s);
        System.out.println("Servidor Enviou --> " + s);
    }


    private void closeSocket(){

        // Fechar os streams e o socket
        try {
            if (os != null) os.close();
            if (is != null) is.close();
            if (connection != null ) connection.close();
        }
        catch (IOException e) {
            System.err.println("Erro ao fechar o socket --> " + e.getMessage());
        }
    }


} // end HandleConnectionThread
