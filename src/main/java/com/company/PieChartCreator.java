package com.company;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RectangleInsets;

import java.awt.*;
import java.text.NumberFormat;

public class PieChartCreator{

    private final String chartTitle;
    private final double[] chartValues;
    private final String[] sectionsNames;
    private PieDataset  dataset;
    // {0} — the name of the section, {1} — the number value, {2} — percent value
    private String labelFormat = "{0}: {1} = {2}";    // Format of the discription label to the sections of the chart
    private String tipFormat = "{2}";           // Format of the tooltip showing, when hovering the mouse cursor

    public String getLabelFormat() {
        return labelFormat;
    }

    public void setLabelFormat(String labelFormat) {
        this.labelFormat = labelFormat;
    }

    public String getTipFormat() {
        return tipFormat;
    }

    public void setTipFormat(String tipFormat) {
        this.tipFormat = tipFormat;
    }

    public PieChartCreator(String chartTitle, String[] sectionsNames, double[] chartValues,
                           String labelFormat, String tipFormat) {

        this(chartTitle,sectionsNames,chartValues);
        this.labelFormat = labelFormat;
        this.tipFormat = tipFormat;
    }

    public PieChartCreator(String chartTitle, String[] sectionsNames,
                           double[] chartValues) {

        this.chartTitle = chartTitle;
        this.sectionsNames = sectionsNames;
        this.chartValues = chartValues;
    }

    public ChartPanel createChartPanel() {
        dataset = createPieDataset();
        JFreeChart chart = createChart();
        chart.setPadding(new RectangleInsets(4, 8, 2, 2));

        ChartPanel panel = new ChartPanel(chart);
        panel.setFillZoomRectangle(true);

        return panel;
    }

    private PieDataset createPieDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (int i = 0; i < chartValues.length; i++)
            dataset.setValue(sectionsNames[i], Double.valueOf(chartValues[i]));
        return dataset;
    }

    private JFreeChart createChart() {
        JFreeChart chart = ChartFactory.createPieChart(
                chartTitle,
                dataset,             // data
                false,               // no legend
                false,                // tooltips
                false                // no URL generation
        );

        TextTitle title = chart.getTitle();
        title.setPaint(Color.DARK_GRAY);
        title.setFont(Gui.TITLE_2.deriveFont(Font.BOLD));

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setToolTipGenerator(new StandardPieToolTipGenerator(tipFormat));
        PieSectionLabelGenerator pslg = new StandardPieSectionLabelGenerator(labelFormat,
                NumberFormat.getNumberInstance(),
                NumberFormat.getPercentInstance());
        plot.setLabelGenerator(pslg);

        plot.setBackgroundPaint(null);
        plot.setInteriorGap(0.04);

        plot.setOutlineVisible(false);

        plot.setBaseSectionOutlinePaint(Color.WHITE);
        plot.setSectionOutlinesVisible(true);
        plot.setBaseSectionOutlineStroke(new BasicStroke(2.0f));

        plot.setLabelFont(Gui.SMALL_TEXT_FONT);
        plot.setLabelLinkPaint(Color.DARK_GRAY);
        plot.setLabelLinkStroke(new BasicStroke(2.0f));
        plot.setLabelOutlineStroke(null);
        plot.setLabelPaint(Color.DARK_GRAY);
        plot.setLabelBackgroundPaint(null);
        return chart;
    }
}
