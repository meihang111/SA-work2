package work2;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.RenderingHints;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;


public class draw {
	static String business = null;
	
	static HashMap<String,Double> map=new HashMap<String,Double>();
		
	public draw(HashMap<String,Double> map,String str) {
		this.map = map;
		this.business = str;
	}
    
	ChartPanel frame1;
	public void BL(){
		XYDataset xydataset = createDataset();
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("����ͼ", "����", "�۸�",xydataset, true, true, true);
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        dateaxis.setDateFormatOverride(new SimpleDateFormat("DD-MM-yyyy"));
        frame1=new ChartPanel(jfreechart,true);
        dateaxis.setLabelFont(new Font("����",Font.BOLD,14));         //ˮƽ�ײ�����
        dateaxis.setTickLabelFont(new Font("����",Font.BOLD,12));  //��ֱ����
        ValueAxis rangeAxis=xyplot.getRangeAxis();//��ȡ��״
        rangeAxis.setLabelFont(new Font("����",Font.BOLD,15));
        jfreechart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));
        jfreechart.getTitle().setFont(new Font("����",Font.BOLD,20));//���ñ�������
        System.out.println("����д��");

	} 
	 private static XYDataset createDataset() {
	        @SuppressWarnings("deprecation")
			TimeSeries timeseries = new TimeSeries(business,org.jfree.data.time.Day.class);
	        Iterator iter = map.keySet().iterator();
	        while (iter.hasNext()) {
	        String key = (String)iter.next();
	        Double val = (Double)map.get(key);
	        int year = Integer.parseInt(key.substring(0, 4));  
            int month = Integer.parseInt(key.substring(5, 7));  
            int day = Integer.parseInt(key.substring(8,10));
            System.out.println(":"+year+":"+month+":"+day);
            timeseries.add(new Day(day,month,year),val);
	        }
	        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
	        timeseriescollection.addSeries(timeseries);
	        return timeseriescollection;
	    }
	  public ChartPanel getChartPanel(){
		    BL();
		    System.out.println("BL()�ѵ���");
		    frame1.setVisible(true);
	    	return frame1;
	    }
}
