package work2;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Data_Access {
	  String type = null;
	  static HashMap<String,Double> map = new HashMap<String,Double>();

	  public HashMap<String,Double> getHashMap() {
		  return Data_Access.map;
	  }
	  
	  public void settype(String str) {
		  this.type=str;
	  }
	  
	 public void DA()
	 {
	  String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	  String dbURL="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=SAdata";
	  String userName="sa";
	  String userPwd="sa";
	  double begin_price= 0.0;
	  double end_price =  0.0;
	  double max_price =  0.0;
	  double min_price =  0.0;
	  Connection dbConn = null;
	 try
	{
	    Class.forName(driverName);
	}catch(Exception e){
	    e.printStackTrace();
	    System.out.println("��������ʧ�ܣ�");
	}
	try{
	    dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
	    System.out.println("���ݿ�ɹ����ӣ�");
	    PreparedStatement ps = dbConn.prepareStatement("select [" + type +"], [����] from [ʵ�����ݣ���֤ĳ��˾��������]\r\n" + 
	    		"where ���� between '2000-01-01' and '2000-01-30'");
	    // ִ��SQL,��ȡ�����rs
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
            end_price = Double.parseDouble(rs.getString(type));
            String date = rs.getString("����");
            map.put(date, end_price);
            System.out.println(end_price);
            }
	    dbConn.close();
	}catch(Exception e)
	{
	    e.printStackTrace();
	    System.out.print("SQL Server����ʧ�ܣ�");
	}        
	}
}