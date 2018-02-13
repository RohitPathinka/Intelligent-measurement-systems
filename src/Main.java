import java.awt.BasicStroke;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Data> details = getDatafromCSV("wifigdansk.csv");
		
		Collections.sort(details, new Comparator<Data>() 
		{
			public int compare(Data d1,Data d2)
			{
				return Double.valueOf(d1.getLatitude()).compareTo(d2.getLatitude());
			}
		});
		
		createSubList(details);
				
			
	}
	
	private static List<Data> createSubList(List<Data> details){
		
		List<Data> Region1 = details.subList(0, 20);
		List<Data> Region2 = details.subList(20,75);
		List<Data> Region3 = details.subList(75, 100);
		
		System.out.println("REGION 1 LOCATIONS");
		for(Data d : Region1) {
			System.out.println(d.getLocation());
				}
		
		System.out.println("REGION 2 LOCATIONS");
		for(Data d : Region2) {
			System.out.println(d.getLocation());
				}
		
		System.out.println("REGION 3 LOCATIONS");
		for(Data d : Region3) {
			System.out.println(d.getLocation());
		}
		
		
		double y[] = {Region1.size(), Region2.size(),Region3.size()};
		double average = (Region1.size()+ Region2.size()+Region3.size())/y.length;
		
		return (drawPlot(average,y));
		
	}
	
	private static List<Data> drawPlot(double average,double y[]) {
		
		double meanvalue = average;
		double[] yaxis = new double [y.length];
		for(int i = 0; i < y.length;++i ) {
			yaxis[i]= y[i];
		}
		
		
		DefaultCategoryDataset dataset = new  DefaultCategoryDataset();
		dataset.setValue(yaxis[0], "", "Region 1");
		dataset.setValue(yaxis[1], "", "Region 2");
		dataset.setValue(yaxis[2], "", "Region 3");
		
		JFreeChart chart = ChartFactory.createBarChart("HotSpots", "", "Average value of the zones", dataset,
				PlotOrientation.HORIZONTAL, false, true, false);
		
		
		chart.setBackgroundPaint(Color.green);
		
		chart.getTitle().setPaint(Color.orange);
		CategoryPlot p = chart.getCategoryPlot();
		p.setRangeGridlinePaint(Color.WHITE);
		BarRenderer renderer = (BarRenderer) p.getRenderer();
		renderer.setSeriesPaint(0, Color.BLUE);
		ValueMarker vm = new ValueMarker(meanvalue, Color.RED, new BasicStroke(2.0F));
		p.addRangeMarker(vm);
		ChartFrame frame1 = new ChartFrame("NUMBER OF HOTSPOTS IN DIFFERENT REGIONS", chart);
		frame1.setVisible(true);
		frame1.setSize(600,600);
		
		return null;
	}
	
	
	private static List<Data> getDatafromCSV(String fileName){
		List<Data> details = new ArrayList<>();
		
		String filename = "wifigdansk.csv";
		File file = new File(filename);
		Scanner inputStream;
		
		try  {
			inputStream = new Scanner(file);
			inputStream.next();
			
			while (inputStream.hasNext()) {
				String data = inputStream.next();
				String[] field = data.split(",");
				Data gdanskData = createDataSet(field);
				
				details.add(gdanskData);
							
			}
			
			}catch (IOException ioe) {
	            ioe.printStackTrace();
		}
		
		return details;
	}
	
	private static Data createDataSet(String[] metadata) {
		String ID = metadata[0];
		String location = metadata[1];
		String xc = metadata[2];
		double latitude = Double.parseDouble(xc);
		String yc = metadata[3];
		double longitude = Double.parseDouble(yc);
		
		return new Data(ID, location, latitude,longitude);		
		
	}	


}
