package com.example;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {

            // Crea un oggetto Persona
            Persona persona = new Persona("Mario", "Rossi", 0);

            // Stampa l'oggetto prima dell'invio
            System.out.println("Oggetto prima dell'invio al server: " + persona);

            // Invia l'oggetto Persona serializzato in XML al server
            outputStream.writeObject(persona.toXml());
            System.out.println("Oggetto inviato al server");

            // Ricevi l'oggetto Persona serializzato in XML dal server
            String xmlData = (String) inputStream.readObject();
            Persona personaFromServer = Persona.fromXml(xmlData);

            // Stampa l'oggetto ricevuto dal server
            System.out.println("Oggetto ricevuto dal server: " + personaFromServer);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}


