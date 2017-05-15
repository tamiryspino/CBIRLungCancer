package gov.ufal.br;

import java.awt.Color;
import java.util.List;
import java.awt.BasicStroke;
import org.jfree.chart.ChartPanel;
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

	public XYLineChart(List<PrecisionByFeatures> precisions, String applicationTitle, String chartTitle) {
		super(applicationTitle);
		JFreeChart xylineChart = ChartFactory.createXYLineChart(chartTitle, "Category", "Score",
				createDataset(precisions), PlotOrientation.VERTICAL, true, true, false);

		ChartPanel chartPanel = new ChartPanel(xylineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		final XYPlot plot = xylineChart.getXYPlot();
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.RED);
		plot.setBackgroundPaint(Color.white);
		plot.setDomainGridlinePaint(Color.black);
		plot.setRangeGridlinePaint(Color.BLACK);
		
		renderer.setSeriesStroke(0, new BasicStroke(.1f));
		
		plot.setRenderer(renderer);
		NumberAxis range = (NumberAxis) plot.getRangeAxis();
		//range.setRange(averagePrecision.get(averagePrecision.size()-1)-0.05, averagePrecision.get(0)+0.05);
		range.setTickUnit(new NumberTickUnit(0.05));
		NumberAxis domain = (NumberAxis) plot.getDomainAxis();
		domain.setRange(0,9);
		setContentPane(chartPanel);
	}

	private XYSeriesCollection createDataset(List<PrecisionByFeatures> precisions) {
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries nodules;
		int noduleCount;
		for (PrecisionByFeatures precision : precisions) {
			nodules = new XYSeries(precision.getFeatureName());
			noduleCount = 0;
			for (Double p : precision.getAverageOfPrecisionByRanking()) {
				nodules.add(noduleCount, p);
				noduleCount++;
			}
			
			dataset.addSeries(nodules);
		}
		return dataset;
	}
}