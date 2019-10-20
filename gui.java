package work2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.jfree.chart.ChartPanel;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.awt.event.ActionEvent;

public class gui {

	private JFrame frame;
	private JFrame frame2;
	public static String search = null;
	public static String begintime = null;
	public static String endtime = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui window = new gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u67E5\u8BE2\u9879\u76EE\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		label.setBounds(60, 37, 80, 19);
		frame.getContentPane().add(label);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("宋体", Font.PLAIN, 16));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"开盘价","最高价","最低价","收盘价","成交量","成交金额","涨跌","涨跌幅","均价","转手率"}));
		comboBox.setBounds(139, 36, 197, 23);
		frame.getContentPane().add(comboBox);
		
		JLabel label_1 = new JLabel("\u5F00\u59CB\u65F6\u95F4\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 16));
		label_1.setBounds(60, 86, 80, 19);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u7ED3\u675F\u65F6\u95F4\uFF1A");
		label_2.setFont(new Font("宋体", Font.PLAIN, 16));
		label_2.setBounds(60, 131, 80, 19);
		frame.getContentPane().add(label_2);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("宋体", Font.PLAIN, 16));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016"}));
		comboBox_1.setBounds(139, 85, 80, 23);
		frame.getContentPane().add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		comboBox_2.setFont(new Font("宋体", Font.PLAIN, 16));
		comboBox_2.setBounds(217, 85, 64, 23);
		frame.getContentPane().add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBox_3.setFont(new Font("宋体", Font.PLAIN, 16));
		comboBox_3.setBounds(280, 85, 56, 23);
		frame.getContentPane().add(comboBox_3);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999"}));
		comboBox_4.setFont(new Font("宋体", Font.PLAIN, 16));
		comboBox_4.setBounds(139, 130, 80, 23);
		frame.getContentPane().add(comboBox_4);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		comboBox_5.setFont(new Font("宋体", Font.PLAIN, 16));
		comboBox_5.setBounds(217, 130, 64, 23);
		frame.getContentPane().add(comboBox_5);
		
		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBox_6.setFont(new Font("宋体", Font.PLAIN, 16));
		comboBox_6.setBounds(280, 130, 56, 23);
		frame.getContentPane().add(comboBox_6);
		
		JButton button = new JButton("\u5F00\u59CB\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatagramSocket sendsocket = null;
				try {
					sendsocket = new DatagramSocket();
				} catch (SocketException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				search = (String)comboBox.getSelectedItem();
				begintime = (String)comboBox_1.getSelectedItem() + "-" + (String)comboBox_2.getSelectedItem() + "-" + (String)comboBox_3.getSelectedItem();
				endtime = (String)comboBox_4.getSelectedItem() + "-" + (String)comboBox_5.getSelectedItem() + "-" + (String)comboBox_6.getSelectedItem();
				System.out.println("查询项目：" + search);
				System.out.println("开始时间：" + begintime);
				System.out.println("结束时间：" + endtime);
				sendmessage send = new sendmessage(sendsocket, search, begintime, endtime);
				try {
					send.send();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					send.show();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame2 = new JFrame();
				frame2.setBounds(50, 50, 800, 600);
				ChartPanel CP = send.getChartPanel();
				CP.setBounds(50, 50, 800, 600);
				frame2.getContentPane().add(CP);
				frame2.setVisible(true);
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 16));
		button.setBounds(280, 197, 115, 35);
		frame.getContentPane().add(button);
	}
}
