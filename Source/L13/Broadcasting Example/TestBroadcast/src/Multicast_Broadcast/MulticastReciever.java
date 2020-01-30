/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multicast_Broadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author Alan.Ryan
 */
public class MulticastReciever {

    public static void main(String[] args) {

        MulticastSocket socket = null;
        DatagramPacket inPacket = null;
        byte[] inBuf = new byte[256];
        try {

            //Prepare to join multicast group
            socket = new MulticastSocket(8888);
            InetAddress address = InetAddress.getByName("224.2.2.3");
            socket.joinGroup(address);

            while (true) {

                inPacket = new DatagramPacket(inBuf, inBuf.length);

                socket.receive(inPacket);

                String msg = new String(inBuf, 0, inPacket.getLength());

                System.out.println("Date and time on " + inPacket.getAddress().getHostName() + " is " + msg + "\n");

            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

}
