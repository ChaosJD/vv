package de.fhr.inf.vv.sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by be on 02.05.2018.
 */
public class EchoServver {

    public static void main(String[] args) throws Exception {
        Executor exe = Executors.newFixedThreadPool(10);
        ServerSocket s = new ServerSocket(10013);
        while (true) {
            final Socket hoerer = s.accept();
            processClient(exe, hoerer);
        }
    }

    private static void processClient(Executor exe,
                                      Socket hoerer) throws IOException {
        Runnable r = () -> {
            try {
                InputStream huhu = hoerer.getInputStream();
                Scanner scan = new Scanner(huhu);
                OutputStream out = hoerer.getOutputStream();
                while (true) {
                    String tach = scan.nextLine();
                    System.out.println(tach);
                    out.write(("Antwort:" + tach + "\n")
                            .getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        exe.execute(r);
    }
}
