package com.company;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class SocketHandler {
    public SocketHandler(String ip, int port) throws IOException
    {
        _listener = new ServerSocket(port, 256, InetAddress.getByName(ip));
        System.out.printf("Socket opened at %s:%d\n", ip, port);
        _clients = new ArrayList<>();
    }

    private ServerSocket _listener;
    public ArrayList<Socket> _clients;

    public void Tick() throws IOException
    {
        try {
            while (true) {
                new PeerHandler(_listener.accept()).start();
            }
        }
        finally {
            _listener.close();
            System.out.println("Socket closed");
        }
    }

}
