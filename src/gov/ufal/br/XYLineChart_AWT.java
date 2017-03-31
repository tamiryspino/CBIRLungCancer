package gov.ufal.br;

import java.awt.Color;
import java.util.List;
import java.awt.BasicStroke;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

/**
 * https://www.tutorialspoint.com/jfreechart/jfreechart_xy_chart.htm
 * @author tamiryspino
 *
 */
public class XYLineChart_AWT extends ApplicationFrame {
	public XYLineChart_AWT(List<Double> precision, String applicationTitle, String chartTitle) {
		super(applicationTitle);
		JFreeChart xylineChart = ChartFactory.createXYLineChart(chartTitle, "Category", "Score",
				createDataset(precision), PlotOrientation.VERTICAL, true, true, false);

		ChartPanel chartPanel = new ChartPanel(xylineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		final XYPlot plot = xylineChart.getXYPlot();
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.RED);
		plot.setBackgroundPaint(Color.white);
		plot.setDomainGridlinePaint(Color.black);
		plot.setRangeGridlinePaint(Color.BLACK);
		/*renderer.setSeriesPaint(1, Color.GREEN);
		renderer.setSeriesPaint(2, Color.YELLOW);
		*/
		renderer.setSeriesStroke(0, new BasicStroke(.5f));
		//renderer.setSeriesStroke(1, new BasicStroke(3.0f));
		//renderer.setSeriesStroke(2, new BasicStroke(2.0f));
		plot.setRenderer(renderer);
		NumberAxis range = (NumberAxis) plot.getRangeAxis();
        range.setRange(0.7, 1.05);
        range.setTickUnit(new NumberTickUnit(0.05));
        setContentPane(chartPanel);
	}

	private XYSeriesCollection createDataset(List<Double> precision) {
		final XYSeries benignNodules = new XYSeries("Nódulos Benignos");
		int i = 1;
		for (Double p : precision) {
			benignNodules.add(i, p);
			i++;
		}
		/*final XYSeries chrome = new XYSeries("Chrome");
		chrome.add(1.0, 4.0);
		chrome.add(2.0, 5.0);
		chrome.add(3.0, 6.0);
		final XYSeries iexplorer = new XYSeries("InternetExplorer");
		iexplorer.add(3.0, 4.0);
		iexplorer.add(4.0, 5.0);
		iexplorer.add(5.0, 4.0);*/
		final XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(benignNodules);
		/*dataset.addSeries(chrome);
		dataset.addSeries(iexplorer);
		*/
		return dataset;
	}

	/*public static void main(String[] args) {
		XYLineChart_AWT chart = new XYLineChart_AWT(precision, "Browser Usage Statistics",
				"Which Browser are you using?");
		chart.pack();
		RefineryUtilities.centerFrameOnScreen(chart);
		chart.setVisible(true);
	}*/
}