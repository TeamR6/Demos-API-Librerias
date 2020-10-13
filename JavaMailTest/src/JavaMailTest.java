
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Scanner;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lenovo
 */
public class JavaMailTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, MessagingException {
       File templateUser=new File(Constantes.dirTemplate);
        Scanner scannerUser=new Scanner(templateUser);
         String templateStr="";
 
        while(scannerUser.hasNext()){
            templateStr=templateStr+""+scannerUser.nextLine();
        }
        
  Properties propiedades=new Properties();
         propiedades.setProperty("mail.smtp.host","smtp.gmail.com");
           propiedades.setProperty("mail.smtp.starttls.enable", "true");
           propiedades.setProperty("mail.smtp.port", "587");
           propiedades.setProperty("mail.smtp.auth", "true");
           Session sesion=Session.getDefaultInstance(propiedades);
           
        String asunto="Se acerca halloween";
        
        MimeMessage mail=new MimeMessage(sesion);
           
           mail.setFrom(new InternetAddress(Constantes.cuentaSMTP));
           mail.addRecipient(Message.RecipientType.TO,new InternetAddress(Constantes.destinatario));
           mail.setSubject(asunto);
           mail.setContent(templateStr,"text/html");
           Transport transporte=sesion.getTransport("smtp");
           transporte.connect(Constantes.cuentaSMTP,Constantes.contraSTMP);
           transporte.sendMessage(mail,mail.getRecipients(Message.RecipientType.TO));
           transporte.close(); 
    }
    
}
