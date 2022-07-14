import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client {

    public static void main (String[] args) {

        String Local = "127.0.0.1";
        String IP = "192.168.137.135";
        int port = 5001;
        String serverIP = " "; 
        int opc;

        System.out.println("\tMenu\n\nTipo de conexion:\n1) Conectivo\n2) No conectivo\nSeleccione una opcion: ");
        Scanner select = new Scanner(System.in);
        opc = select.nextInt();

        switch(opc) {
            case 1:
                serverIP = IP;
                break;
            case 2:
                serverIP = Local;
                break;
            default: System.out.println("Opcion no validad");
        }

        try(Socket socketc= new Socket(serverIP, port)) {
            //a leer bufer del servidor
            PrintWriter writer = new PrintWriter(socketc.getOutputStream(), true);
            //a leer lo que manda el servidor
            BufferedReader reader = new BufferedReader ( new InputStreamReader(socketc.getInputStream()));

            //a crea la variable de lectura
            Scanner read = new Scanner (System.in);

	        String clientwrite = "";
            //a hasta que no se ingrese cadena
            while (true) {
                System.out.println("Escriba una cadena: ");
                clientwrite=read.nextLine();
                if ("". equalsIgnoreCase(clientwrite)){
                    break;
                }
                //a imprime lo que manda el cliente
                writer.println(clientwrite);
                //a imprime lo que manda el servidor
                String serverwrite = reader.readLine();
                System.out.println("Respuesta del server: mensaje(" + serverwrite + ") recivido");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
