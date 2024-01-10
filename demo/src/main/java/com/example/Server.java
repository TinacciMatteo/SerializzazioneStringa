package com.example;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server in attesa di connessione...");

            while (true) {
                try (Socket socket = serverSocket.accept();
                     ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                     ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {

                    // Ricevi oggetto Persona dal client
                    String xmlData = (String) inputStream.readObject();
                    Persona personaFromClient = Persona.fromXml(xmlData);

                    // Stampa l'oggetto ricevuto
                    System.out.println("Oggetto ricevuto dal client: " + personaFromClient);

                    // Invia l'oggetto Persona serializzato in XML al client
                    outputStream.writeObject(personaFromClient.toXml());
                    System.out.println("Oggetto inviato al client");

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


