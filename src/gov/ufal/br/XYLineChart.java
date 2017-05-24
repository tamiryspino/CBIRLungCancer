package gov.ufal.br;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.awt.BasicStroke;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

/**
 * https://www.tutorialspoint.com/jfreechart/jfreechart_xy_chart.htm
 * 
 * @author tamiryspino
 *
 */
public class XYLineChart extends ApplicationFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7097736463433624021L;

	public XYLineChart(String nameOfFile, List<PrecisionByRanking> precisions, String applicationTitle, String chartTitle) throws IOException {
		super(applicationTitle);
		JFreeChart xylineChart = ChartFactory.createXYLineChart(chartTitle, "Nodule", "Precision",
				createDataset(precisions), PlotOrientation.VERTICAL, true, true, false);

		ChartPanel chartPanel = new ChartPanel(xylineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		final XYPlot plot = xylineChart.getXYPlot();
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint( 0, Color.RED);
		renderer.setSeriesPaint( 1, Color.GREEN );
	    renderer.setSeriesPaint( 2, Color.YELLOW );
	    renderer.setSeriesPaint( 3, Color.BLUE);
	    renderer.setSeriesPaint( 4, Color.ORANGE);
	    renderer.setSeriesPaint( 5, Color.PINK);
	    renderer.setSeriesStroke( 0, new BasicStroke( 2.0f ) );
	    renderer.setSeriesStroke( 1, new BasicStroke( 2.0f ) );
	    renderer.setSeriesStroke( 2, new BasicStroke( 2.0f ) );
	    renderer.setSeriesStroke( 3, new BasicStroke( 2.0f ) );
		plot.setBackgroundPaint(Color.white);
		plot.setDomainGridlinePaint(Color.black);
		plot.setRangeGridlinePaint(Color.BLACK);
		
		plot.setRenderer(renderer);
		NumberAxis range = (NumberAxis) plot.getRangeAxis();
		range.setRange(0.5, 1.05);
		NumberAxis domain = (NumberAxis) plot.getDomainAxis();
		domain.setRange(0,9);
		setContentPane(chartPanel);
		
		File XYChart = new File(nameOfFile + ".jpeg"); 
	    ChartUtilities.saveChartAsJPEG( XYChart, xylineChart, 560, 367);
	}

	private XYSeriesCollection createDataset(List<PrecisionByRanking> precisions) {
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries nodules;
		int noduleCount;
		for (PrecisionByRanking precision : precisions) {
			nodules = new XYSeries(precision.getGroupName());
			noduleCount = 0;
			for (Double p : precision.getPrecisionOfAll()) {
				nodules.add(noduleCount, p);
				noduleCount++;
			}
			
			dataset.addSeries(nodules);
		}
		return dataset;
	}
}