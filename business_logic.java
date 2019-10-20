package business_logic;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;

public class business_logic {
	static int port = 8080;
	static InetAddress messageip;
	static int messageport;
	static SocketAddress replytarget = new InetSocketAddress(messageip, messageport);
	static String serverIP="172.27.201.143";
	static int dataport=8099;
	static SocketAddress target = new InetSocketAddress(serverIP, dataport); 
	static String message;
	
	public static void recieve() throws Exception {
		DatagramSocket rec = new DatagramSocket(port);
		byte[]buf=new byte[1024];
		DatagramPacket packet = new DatagramPacket(buf,buf.length);
		while(true) {
			rec.receive(packet);
			message = new String(packet.getData(),0,packet.getLength());
			if(message.length() > 0) {
				if(message.indexOf("presentation:")>-1){
					System.out.println("message£ºipµØÖ·£º" + packet.getAddress() + "   port:" + packet.getPort() + "   message:" + message);
					System.out.println("logic_business recieved!");
					messageip = packet.getAddress();
					messageport = packet.getPort();
					send();
				}
				else if(message.indexOf("data_access:") > -1) {
					System.out.println("logic_business recieved reply!");
					System.out.println("message:" + message);
					DatagramSocket send = new DatagramSocket();
					byte[]data = message.toString().getBytes();
					SocketAddress datatarget = new InetSocketAddress(messageip, messageport); 
					DatagramPacket sendpacket=new DatagramPacket(data,data.length,datatarget);
					send.send(sendpacket);
					break;
				}
			}
		}
	}
	
	public static void send() throws Exception {
		DatagramSocket send = new DatagramSocket();
		byte[]data = message.toString().getBytes();
		DatagramPacket sendpacket=new DatagramPacket(data,data.length,target);
		send.send(sendpacket);
	}
	
	public static void main(String args[]) throws Exception {
		recieve();
	}
	
}