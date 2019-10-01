/**
* JAGSAT MISSION 2019
* Written by Adam Frank, with references to contributors at GitHub and Stack Overflow
*  Updates at GitHub.com/adamf59
*/
package adamf59.SystemHostController.System;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.channels.DatagramChannel;

import adamf59.Core.Subsystem;
import adamf59.SystemHostController.SystemHost;
import adamf59.SystemHostController.System.Console;

public class NetworkingService extends Subsystem {


    public static final String IP_ADDR = "224.0.0.1";
    public static final int PORT = 4545;
    InetAddress group;
    MulticastSocket s;

    public NetworkingService() {
        super("NETWORKING_SUBSYSTEM", 10);

    }

    @Override
    public void init() {
        Console.printInfo("Initializing Network Service Subsystem");
        try {
            group = InetAddress.getByName(IP_ADDR);
            s = new MulticastSocket(PORT);
            s.joinGroup(group);
            sendMulticastPacket("<%NETWORKING_SERVICE_DRONE.ATTACHMULTICAST%>");
            Console.printOk("NetworkingService: My IP Address is " + InetAddress.getLocalHost().getHostAddress().trim());
            Console.printOk("NetworkingService: Using Multicast address " + IP_ADDR  + " and port " + PORT);

            Console.printOk("Reached Target: NetworkService Start");

        } catch (Exception e) {
            Console.printErr("Failed to Reach Target: NetworkService Start | Reason: " + e.getMessage());

        }
        

    }


    @Override
    public void execute() {


            byte[] buffer = new byte[10*1024];
            DatagramPacket dx = new DatagramPacket(buffer, buffer.length);
                try {
                    s.receive(dx);
                    Console.printInfo("NetworkingService: RX: "+ 
                       (new String(buffer, 0, dx.getLength())));
                } catch(Exception e) {
                    Console.printErr("Failed to recieve message on NetworkService | Reason: " + e.getMessage());
                }
            

            }
        
    

    @Override
    public void executeAlways() {
        
    }


    protected void sendMulticastPacket(String packetData) {
         try {
            
    
            DatagramPacket data = new DatagramPacket(
                packetData.getBytes(), packetData.length(), group, PORT);
             s.send(data);
         } catch(Exception e) {
            Console.printErr("Failed to multicast message | Reason: " + e.getMessage());
         }
    }
    
    @Override
    public void onDestroy() {
        s.close();
    }

}