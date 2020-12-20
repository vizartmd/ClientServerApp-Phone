package com.unifun;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import java.io.*;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class MyClient {
  static Logger logger = Logger.getLogger(MyClient.class);

  public static void main(String[] args) throws JsonProcessingException {

    Phone phone1 = new Phone("Xiaomi", "Turquoise", 5.6, 180);
    Phone phone2 = new Phone("Nokia", "Black", 5.2, 120);
    Phone phone3 = new Phone("Samsung", "Red", 6.4, 240);
    Phone phone4 = new Phone("iPhone", "Silver", 7.0, 280);
    Set<Phone> phones = new HashSet<>();
    phones.add(phone1);
    phones.add(phone2);
    phones.add(phone3);
    phones.add(phone4);
    ObjectMapper objectMapper = new ObjectMapper();
    final String phonesString = objectMapper.writeValueAsString(phones);
    logger.info("Output message: " + phonesString);
    System.out.println(phonesString);
    {
      try {
        Socket socket = new Socket("localhost", 1234);
        final boolean connected = socket.isConnected();
        logger.info("Socket is connected: " + connected);
        final OutputStream out = socket.getOutputStream();
        DataOutputStream dout = new DataOutputStream(out);
        dout.writeUTF(String.valueOf(phonesString));
        logger.info("The object was successfully serialized!");
        dout.flush();
        socket.close();

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
