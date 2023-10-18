import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ServidorGlobalWay {
    static ServerSocket servidor = null;
    public static Socket socket_cliente = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            
                try {
            int cont=0;
           
            DataInputStream entrada;
            PrintStream salida;
           servidor = new ServerSocket(2020);
            while(true){
                try {
                    cont=0;
                System.out.println("Esperando cliente");
                 socket_cliente = servidor.accept();
                System.out.println("En espera de conexion");
                Hilos servidor = new Hilos(socket_cliente);
                 servidor.start();
                      
           } catch (IOException ex) {
              Logger.getLogger(Hilos.class.getName()).log(Level.SEVERE, null, ex);
        }        
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorGlobalWay.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
        });
    }

}

class Hilos extends Thread{
    
    public static String correo ="grupoprogramacion04@gmail.com";
    public static String contra = "Este1245";

    public Hilos(Socket s){
        
     
    }
    
    @Override
    public void run(){
        try {
           String linea_recibida;
           DataInputStream entrada;
           PrintStream salida;
           System.out.println("Listo");
           entrada = new DataInputStream(ServidorGlobalWay.socket_cliente.getInputStream());
           salida = new PrintStream(ServidorGlobalWay.socket_cliente.getOutputStream());
          
            linea_recibida = entrada.readLine();
            System.out.println("linea"+linea_recibida);
            String[] linea= linea_recibida.split(",");        
            
               if(linea[1].contains("gmail")){
                try
                {
            // Prop conexión
               Properties props = new Properties();
               props.setProperty("mail.smtp.host", "smtp.gmail.com");
               props.setProperty("mail.smtp.starttls.enable", "true");
               props.setProperty("mail.smtp.port", "587");
               props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            
                Session session = Session.getDefaultInstance(props);

            //  mensaje
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(correo));
                message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(linea[1]));
                    message.setSubject("Factura de GlobalWay para "+linea[0]);
                    message.setText(
                        "Buenas estimado usuario."
                        + "\nEsta es su factura:"
                        + "\n\n======================================================================================"
                        + "\nNombre del cliente: "+linea[0]+"\nMoneda utilizada: "+linea[2]
                        + "\nCantidad: "+linea[6]+"\nRetira en: "+linea[3]+"\nPagas con: "+linea[4]+"\nMonto total a pagar: "+linea[5]
                        + "\n======================================================================================");

            // Lo enviamos.
                  Transport t = session.getTransport("smtp");
                  t.connect(correo, contra);
                 t.sendMessage(message, message.getAllRecipients());

      
                    t.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
               }else{
                    try
                    {
                        
            
                    Properties props = new Properties();
                    props.setProperty("mail.transport.protocol", "smtp");
                    props.setProperty("mail.host", "smtp.live.com");
                    props.put("mail.smtp.starttls.enable", "true");
                    props.put("mail.smtp.auth", "true");
                    props.setProperty("mail.smtp.port", "25");
                    props.put("mail.smtp.ssi.trust", "smtp.live.com");

            
                     Session session = Session.getDefaultInstance(props);

            //  mensaje
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(correo));
                message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(linea[1]));
                    message.setSubject("Factura de GlobalWay para "+linea[0]);
                    message.setText(
                        "Buenas estimado usuario."
                        + "\nEsta es su factura:"
                        + "\n\n======================================================================================"
                        + "\nNombre del cliente: "+linea[0]+"\nMoneda utilizada: "+linea[2]
                        + "\nCantidad: "+linea[6]+"\nRetira en: "+linea[3]+"\nPagas con: "+linea[4]+"\nMonto total a pagar: "+linea[5]
                        + "\n======================================================================================");

            // Lo enviamos.
                  Transport t = session.getTransport("smtp");
                  t.connect(correo, contra);
                 t.sendMessage(message, message.getAllRecipients());
           
                        t.close();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                  
                }

            
            
            
        } catch (IOException ex) {
            Logger.getLogger(Hilos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
}