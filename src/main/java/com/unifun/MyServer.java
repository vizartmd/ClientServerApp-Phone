package com.unifun;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyServer {
  static Logger logger = Logger.getLogger(MyServer.class);

  public static void main(String[] args) {
    Set<Phone> phones = new HashSet<>();
    List<Phone> myPhones;

    try {
      ServerSocket serverSocket = new ServerSocket(1234);
      Socket socket = serverSocket.accept();
      final boolean connected = socket.isConnected();
      logger.info("Socket is connected: " + connected);
      DataInputStream dis = new DataInputStream(socket.getInputStream());
      String str = dis.readUTF();
      logger.info("Deserialization was successful!\n" +
              "Input message: " + str);
      ObjectMapper objectMapper = new ObjectMapper();
      myPhones = objectMapper.readValue(str, new TypeReference<>(){});

      socket.close();

    } catch (Exception e) {
      System.out.println(e);
    }
    for (Phone phone : phones) {
      System.out.println(phone.toString());
    }

  }
}