package de.fhr.inf.vv.sockets;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by be on 09.05.2018.
 */
public class Client {
    public static void main(String[] args) throws Exception {
        Socket server = new Socket(
                InetAddress.getLocalHost(), 10013);
        server.setSoTimeout(5000);
        OutputStream out = server.getOutputStream();
        out.write("Hallloooooo\n".getBytes());
        InputStream in = server.getInputStream();
        Scanner scan = new Scanner(in);
        String zeile = scan.nextLine();
        System.out.println("Server sagt:" + zeile);
    }
}
