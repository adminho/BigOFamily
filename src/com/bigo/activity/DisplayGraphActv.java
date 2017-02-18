package com.bigo.activity;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DisplayGraphActv extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_graph);
		
		intitDemoGraph();
		
		Toast.makeText(this, getString(R.string.txt_cannot_suport), Toast.LENGTH_LONG).show();
	}


	public void intitDemoGraph(){
		 String titles = "กราฟชะตาชีวิตของคุณ";
		 double []values = new double[] {8,11,2,10,1,4,7,5,7,9,11,1};
		
		
		GraphicalView mChartView  = ChartFactory.getLineChartView(
				this, 
				buildBarDataset(titles,values), 
				buildRenderer(Color.BLUE,PointStyle.CIRCLE));
		
		mChartView.repaint();
		 
		LinearLayout  layout = (LinearLayout ) findViewById(R.id.graphId);
		layout.addView(mChartView);
	}
	
	private XYMultipleSeriesDataset buildBarDataset(String titles,double[] values) {
		CategorySeries series = new CategorySeries(titles);
		for (double val : values) {
			series.add(val);
		}
		
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series.toXYSeries());
		return dataset;
	}
	
	private XYMultipleSeriesRenderer buildRenderer(int color, PointStyle styles) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		renderer.setAxisTitleTextSize(16);
		renderer.setChartTitleTextSize(20);
//		renderer.setLabelsTextSize(15);
//		renderer.setLegendTextSize(15);
		renderer.setPointSize(5f);
		renderer.clearXTextLabels();
		renderer.clearYTextLabels();
		renderer.setShowGrid(true);
		renderer.setBackgroundColor(Color.YELLOW);
		renderer.setGridColor(Color.RED);
		renderer.setShowLabels(false);
		renderer.setAntialiasing(false);
		
		renderer.setMargins(new int[] { 20, 30, 15, 20});
		
		XYSeriesRenderer r = new XYSeriesRenderer();
		r.setColor(color);
		r.setPointStyle(styles);
		r.setFillPoints(true);
		renderer.addSeriesRenderer(r);
		return renderer;
	}
	
}
