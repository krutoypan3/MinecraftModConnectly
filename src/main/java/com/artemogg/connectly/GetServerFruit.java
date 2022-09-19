package com.artemogg.connectly;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class GetServerFruit {

    private static final String IpAddress = "127.0.0.1";
    private static final int ServerPort = 8090;
    private static InetAddress ServerIp;
    public static boolean isConnect = true;


    public static void startConnection(PlayerEntity player) {
        try {
            Thread connectionThread = new Thread(() -> {
                try {
                    InputStream sis;

                    try {
                        ServerIp = InetAddress.getByName(IpAddress);
                    } catch (UnknownHostException e2) {
                        System.out.println("Unable to connect! Invalid ip!");
                        return;
                    }
                    System.out.println(ServerIp + ":" + ServerPort);
                    Socket socket = new Socket(ServerIp, ServerPort);
                    System.out.println("Establishing connection...");
                    sis = socket.getInputStream();

                    System.out.println("Connection successful!");

                    while (isConnect) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(sis));
                        String line = reader.readLine();
                        player.sendMessage(new StringTextComponent(line), player.getUniqueID());
                        System.out.println("Получено: " + line);
                    }
                } catch (SocketException e) {
                    System.out.println("Ошибка подключения к серверу");
                } catch (IOException e) {
                    System.out.println("Unable to connect! No server found!");
                } catch (NumberFormatException ex) {
                    System.out.println("Unable to connect! Invalid ip!");
                }
            });
            connectionThread.start();
        } catch (Exception ignored) {
        }
    }
}
