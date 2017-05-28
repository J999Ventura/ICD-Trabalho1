package Servidor;


import Servidor.db.DbManager;

public class ServerController {

    private final static int DEFAULT_PORT = 5025;

    public static void main(String[] args) {

        DbManager dbManager = new DbManager();

        ServidorTCPConcorrente sv = new ServidorTCPConcorrente(DEFAULT_PORT, dbManager);
        new Thread(sv).start();

        try {
            Thread.sleep(20 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
