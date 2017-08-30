/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voicechat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.TargetDataLine;

/**
 *
 * @author shubham
 */
public class reecorder_thread  extends Thread{
 public  TargetDataLine aud_in=null;
 public DatagramSocket dout;
    byte[] byte_buff=new byte[512];
    public InetAddress server_ip;
    public int server_port;
    public void run()
    {
        int i=0;
        while(client_voice.calling)
        {
            aud_in.read(byte_buff, 0,byte_buff.length);
            DatagramPacket datagramPacket=new DatagramPacket(byte_buff, byte_buff.length, server_ip, server_port);
            System.err.println("send"+i++);
            try {
                dout.send(datagramPacket);
            } catch (IOException ex) {
                Logger.getLogger(reecorder_thread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        aud_in.close();
        aud_in.drain(); 
        System.out.println("thread stop");
        
    }

}
    
        
     
    
    

