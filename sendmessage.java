package work2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.HashMap;
import org.jfree.chart.ChartPanel;

public class sendmessage {
	private DatagramSocket soc;
	static String search;
	String begintime;
	String endtime;
	static HashMap<String,Double> map = new HashMap<String,Double>();
	ChartPanel frame;
	
	public sendmessage(DatagramSocket soc, String str1, String str2, String str3) {
		this.soc = soc;
		this.search = str1;
		this.begintime = str2;
		this.endtime = str3;
	}
	
	public void send() throws Exception {
		DatagramPacket sendpacket;
		String serverIP = "172.27.201.143";
		int port=8080;
		//构造一个目标地址
		SocketAddress target = new InetSocketAddress(serverIP, port); 
		StringBuffer message = new StringBuffer("presentation:");
		message.append(search + "#" + begintime + "#" + endtime);
		byte[]data = message.toString().getBytes();
		sendpacket=new DatagramPacket(data,data.length,target);
		soc.send(sendpacket);
	}
	
	public void show() throws IOException {
		byte[]buf=new byte[1024];
		DatagramPacket packet = new DatagramPacket(buf,buf.length);
		while(true) {
			soc.receive(packet);
			String message = new String(packet.getData(),0,packet.getLength());
			System.out.println("presentation recieved!");
			System.out.println("message:" + message);
			String thedata=message.substring(message.indexOf("#")+1,message.length());
			String[] datas = thedata.split("#");
			for(String u:datas){
				if(u!=null&&u.length()>0){
					String date = u.split(",")[0];
					Double data = Double.parseDouble(u.split(",")[1]);
					System.out.println(date + ":" + data);
					map.put(date, data);
				}
			}
			break;
		}
	}
	
	public ChartPanel getChartPanel(){
	    draw BL = new draw(map, search);
	    ChartPanel CP = BL.getChartPanel();
	    return CP;
    }
}
