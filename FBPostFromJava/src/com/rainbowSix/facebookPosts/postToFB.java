/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rainbowSix.facebookPosts;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import static com.restfb.Version.VERSION_3_1;
import com.restfb.types.FacebookType;
import com.restfb.types.Group;
import java.util.List;
import java.util.Scanner;
     
/**
 *
 * @author Lenovo
 */
public class postToFB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
    FacebookClient fbClient= new DefaultFacebookClient(Constantes.token,VERSION_3_1);
      Scanner s= new Scanner(System.in);
    Connection<Group> result = fbClient.fetchConnection("me/groups",Group.class);
    for(List<Group> GroupPage :result){
        for(Group grupo: GroupPage){
            System.out.println("¿Desea publicar en el grupo "+grupo.getName()+"?");
             String ans=s.nextLine();
       if(ans.equalsIgnoreCase("Si") || ans.equalsIgnoreCase("Sí")){
            System.out.println("¿Qué desea postear?");
            String msg=s.nextLine();
            
        FacebookType response;
                response = fbClient.publish(grupo.getId()+"/feed",FacebookType.class,Parameter.with("message",msg));
                System.out.println("Se ha publicado el mensaje");
               break; 
               
        }
        }  
        
    }
  //  FacebookType response=fbClient.publish("me/feed",FacebookType.class,Parameter.with("message","Mensaje de prueba enviado desde Java"));
      //  System.out.println("fb.com/"+response.getId());
    }
   
}
