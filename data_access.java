package data_access;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class data_access {
	static int port = 8099;
	static InetAddress messageip;
	static int messageport;
	static String message;
	static HashMap<String,Double> map = new HashMap<String,Double>();
	static String type;
	static String begintime;
	static String endtime;
	static Double data;
	static StringBuffer replymessage = new StringBuffer("data_access:");
	
	public static void recieve() throws Throwable {
		DatagramSocket rec = new DatagramSocket(port);
		byte[]buf=new byte[1024];
		DatagramPacket packet = new DatagramPacket(buf,buf.length);
		while(true) {
			rec.receive(packet);
			message = new String(packet.getData(),0,packet.getLength());
			if(message.length() > 0) {
				if(message.indexOf("presentation:")>-1){
					System.out.println("message：ip地址：" + packet.getAddress() + "   port:" + packet.getPort() + "   message:" + message);
					System.out.println("data_access recieved!");
					messageip = packet.getAddress();
					messageport = packet.getPort();
					break;
				}
			}
		}
	}
	
	public static void searchdata() {
		String realmessage = message.substring(message.indexOf(":")+1,message.length());
		System.out.println(realmessage);
		String[] udata=realmessage.split("#");
		type = udata[0];
		begintime = udata[1];
		endtime = udata[2];
		String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String dbURL="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=SAdata";
		String userName="sa";
		String userPwd="sa";
		Connection dbConn = null;
		 try
		{
		    Class.forName(driverName);
		}catch(Exception e){
		    e.printStackTrace();
		    System.out.println("加载驱动失败！");
		}
		try{
		    dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
		    System.out.println("数据库成功连接！");
		    PreparedStatement ps = dbConn.prepareStatement("select [" + type +"], [日期] from [实验数据：上证某公司股市数据]\r\n" + 
		    		"where 日期 between '" + begintime + "' and '" + endtime + "'");
		    // 执行SQL,获取结果集rs
		    ResultSet rs = ps.executeQuery();
		    while (rs.next()) {
	            data = Double.parseDouble(rs.getString(type));
	            String date = rs.getString("日期");
	            map.put(date, data);
	            System.out.println(date + ":" + data);
	            replymessage.append("#" + date + "," + data);
	            }
		    dbConn.close();
		}catch(Exception e)
		{
		    e.printStackTrace();
		    System.out.print("SQL Server连接失败！");
		}
	}
	
	public static void reply() throws IOException {
		DatagramSocket rep = new DatagramSocket();
		DatagramPacket sendpacket;
		int port=8080;
		//构造一个目标地址
		SocketAddress target = new InetSocketAddress(messageip, port); 
		byte[]data = replymessage.toString().getBytes();
		sendpacket=new DatagramPacket(data,data.length,target);
		rep.send(sendpacket);
	}
	
	public static void main(String args[]) throws Throwable {
		recieve();
		searchdata();
		reply();
	}
}
