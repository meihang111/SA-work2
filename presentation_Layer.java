package work2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;

import org.jfree.chart.ChartPanel;

import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import javax.swing.JToggleButton;
import javax.swing.DefaultComboBoxModel;

public class presentation_Layer {

	private JFrame frame;
	private JFrame frame2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					presentation_Layer window = new presentation_Layer();
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
	public presentation_Layer() {
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
		
		JButton button = new JButton("\u786E\u8BA4");
		button.setBounds(303, 201, 97, 23);
		frame.getContentPane().add(button);
		
		JLabel label = new JLabel("\u9009\u62E9\u67E5\u8BE2\u7684\u4FE1\u606F");
		label.setBounds(41, 30, 89, 15);
		frame.getContentPane().add(label);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u5F00\u76D8\u4EF7", "\u6536\u76D8\u4EF7", "\u6700\u9AD8\u4EF7", "\u6700\u4F4E\u4EF7"}));
		comboBox.setBounds(127, 28, 136, 19);
		frame.getContentPane().add(comboBox);
		
		JLabel label_1 = new JLabel("\u5F00\u59CB\u65F6\u95F4");
		label_1.setBounds(41, 84, 48, 15);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u7ED3\u675F\u65F6\u95F4");
		label_2.setBounds(41, 135, 58, 15);
		frame.getContentPane().add(label_2);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016"}));
		comboBox_1.setBounds(111, 80, 64, 23);
		frame.getContentPane().add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016"}));
		comboBox_2.setBounds(109, 131, 66, 23);
		frame.getContentPane().add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		comboBox_3.setBounds(171, 80, 48, 23);
		frame.getContentPane().add(comboBox_3);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBox_4.setBounds(215, 80, 48, 23);
		frame.getContentPane().add(comboBox_4);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		comboBox_5.setBounds(171, 131, 48, 23);
		frame.getContentPane().add(comboBox_5);
		
		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBox_6.setBounds(215, 131, 48, 23);
		frame.getContentPane().add(comboBox_6);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("开始时间："+comboBox_1.getSelectedItem()+'-'+comboBox_3.getSelectedItem()+'-'+comboBox_4.getSelectedItem());
				System.out.println("结束时间："+comboBox_2.getSelectedItem()+'-'+comboBox_5.getSelectedItem()+'-'+comboBox_6.getSelectedItem());
				String business = (String)comboBox.getSelectedItem();
				String begin_year = (String)comboBox_1.getSelectedItem();
				String begin_month = (String)comboBox_3.getSelectedItem();
				String begin_day = (String)comboBox_4.getSelectedItem();
				String end_year = (String)comboBox_2.getSelectedItem();
				String end_month = (String)comboBox_5.getSelectedItem();
				String end_day = (String)comboBox_6.getSelectedItem();
				Business_Logic BL = new Business_Logic();
				BL.setdata(business, begin_year, begin_month, begin_day, end_year, end_month, end_day);
				BL.getHashMap();
				frame2 = new JFrame();
				frame2.setBounds(50, 50, 800, 600);
				ChartPanel CP = BL.getChartPanel();
				CP.setBounds(50, 50, 800, 600);
				frame2.getContentPane().add(CP);
				frame2.setVisible(true);
			}
		});
	}
}
