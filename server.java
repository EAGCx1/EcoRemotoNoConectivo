
import java.io.*;
import java.net.*;
import java.util.Date;

public class server {

    public static void main (String[] args) {

        int port = 5001;
        String mjs;
        Date date_of_today = new Date();

        //Crear el socket
        try (ServerSocket socket= new ServerSocket(port)){
            try(Socket socketc  = socket.accept()) {
                //a. leer el bufer cliente
                BufferedReader reader = new BufferedReader ( new InputStreamReader(socketc.getInputStream()));
                //a manda el mensaje a traves del socket client
                PrintWriter writer = new PrintWriter(socketc.getOutputStream(), true);
                //a almacena el mensaje del cliente
                while((mjs= reader.readLine()) !=null){
                    System.out.println("\nMensaje("  + mjs + ") recivido del cliente a las " + date_of_today.toString());
                    writer.println(""+ mjs);
                }
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

//Solucion a "La dirección ya se está usando (Bind failed)"
//sudo kill -9 $(sudo lsof -t -i:5001)
//El puerto cambia segun el establecido