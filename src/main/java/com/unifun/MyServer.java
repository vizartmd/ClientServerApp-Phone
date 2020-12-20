package com.unifun;

import org.apache.log4j.Logger;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
  static Logger logger = Logger.getLogger(MyServer.class);

  public static void main(String[] args) {
    try {
      ServerSocket serverSocket = new ServerSocket(1234);
      Socket socket = serverSocket.accept();
      final boolean connected = socket.isConnected();
      logger.info("Socket is connected: " + connected);
      DataInputStream dis = new DataInputStream(socket.getInputStream());
      String str = dis.readUTF();
      logger.info("Deserialization was successful!\n" +
              "Input message: " + str);
      socket.close();

    } catch (Exception e) {
      System.out.println(e);
    }

  }
}
