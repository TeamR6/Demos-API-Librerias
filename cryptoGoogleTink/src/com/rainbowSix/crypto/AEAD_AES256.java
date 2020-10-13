/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rainbowSix.crypto;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.aead.AeadConfig;
import com.google.crypto.tink.aead.AeadFactory;
import com.google.crypto.tink.aead.AeadKeyTemplates;
import java.security.GeneralSecurityException;

/**
 *
 * @author Lenovo
 */
public class AEAD_AES256 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws GeneralSecurityException {
   AeadConfig.register();
 //Genera una llave aes256  
KeysetHandle keysetHandle = KeysetHandle.generateNew(
  AeadKeyTemplates.AES256_GCM);

String plaintext = "Politicians were mostly people who'd had too little morals and ethics to stay lawyers.";
String associatedData = "Tink";
System.out.println("Texto plano: "+ plaintext);
 
Aead aead = AeadFactory.getPrimitive(keysetHandle); 
byte[] ciphertext = aead.encrypt(plaintext.getBytes(), associatedData.getBytes());

String encriptado=new String(ciphertext);
System.out.println("Mensaje encriptado:"+ encriptado +" (Algunos caracteres no son soportados por la consola)");
String decrypted = new String(aead.decrypt(ciphertext, associatedData.getBytes()));

System.out.println("Mensaje desencriptado: "+decrypted);

    }
    
}
