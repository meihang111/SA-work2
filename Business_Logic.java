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


public class Business_Logic {
	static String business = null;
	String begin_year = null;
	String begin_month = null;
	String begin_day = null;
	String end_year = null;
	String end_month = null;
	String end_day = null;
	
	static HashMap<String,Double> map=new HashMap<String,Double>();
	
	public void getHashMap() {
		Data_Access DA = new Data_Access();
		DA.settype(business);
		DA.DA();
		this.map=DA.getHashMap();
	}
    
	ChartPanel frame1;
	public void BL(){
		XYDataset xydataset = createDataset();
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("折线图", "日期", "价格",xydataset, true, true, true);
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        dateaxis.setDateFormatOverride(new SimpleDateFormat("DD-MM-yyyy"));
        frame1=new ChartPanel(jfreechart,true);
        dateaxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题
        dateaxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //垂直标题
        ValueAxis rangeAxis=xyplot.getRangeAxis();//获取柱状
        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
        jfreechart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        jfreechart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体
        System.out.println("数据写入");

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
		    System.out.println("BL()已调用");
		    frame1.setVisible(true);
	    	return frame1;
	    }
    
	public void setdata(String bus,String by,String bm,String bd,String ey,String em,String ed) {
		this.business=bus;
		this.begin_year = by;
		this.begin_month = bm;
		this.begin_day = bd;
		this.end_year = ey;
		this.end_month = em;
		this.end_day = ed;
	}
}
