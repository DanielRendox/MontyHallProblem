package com.company;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameExploreGui {

    public static JPanel createPanel(JFrame frame) {
        JPanel explorePanel = new JPanel(new MigLayout("insets 10, fillx"));

        JLabel title = new JLabel("COMPUTER PLAYS");
        title.setFont(Gui.TITLE_1);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        explorePanel.add(title,"dock north");

        JLabel subtitle = new JLabel("<html>If you go to the Game Stat page, you will find that the winning probability " +
                "is bigger when you change your choice. But according to the Manty Hall paradox the probability of " +
                "winning the car is 67% if you change choice and 33% if not. Most likely your results are far from " +
                "these. That's because you played too litle to achieve these results. So I made the algorytm that plays " +
                "the game as many times as you say randomly changing choice and not changing it and gives you the result. " +
                "Note: the the higher the value is, the more the statistics approach to the expected values.</html>");
        subtitle.setFont(Gui.SMALL_TEXT_FONT);
        subtitle.setForeground(Color.GRAY);
        explorePanel.add(subtitle,"wrap 15px, span");

        JPanel mainPanel = new JPanel(new MigLayout("fillx","[]20[]"));
        explorePanel.add(mainPanel,"align center");

        JPanel spinnerButtonLabel = new JPanel(new MigLayout("fillx"));
        mainPanel.add(spinnerButtonLabel,"align center, wrap");

        JLabel info = new JLabel("<html>Please, specify how many games should<br/>the program play (from 10 to 10 000):</html>");
        info.setFont(Gui.PLAIN_TEXT_FONT);
        spinnerButtonLabel.add(info);

        SpinnerNumberModel numberModel = new SpinnerNumberModel(100,10,10_000,50);
        JSpinner spinner = new JSpinner(numberModel);
        spinner.setFont(Gui.PLAIN_TEXT_FONT);
        spinnerButtonLabel.add(spinner,"span 1 1");

        JButton playButton = new JButton("Play");
        playButton.setFont(Gui.PLAIN_TEXT_FONT);
        spinnerButtonLabel.add(playButton,"w 150!, h 50!, wrap 40px");

        JPanel pieChartsPanel = new JPanel(new MigLayout());
        mainPanel.add(pieChartsPanel,"align center, wrap, span");

        playButton.addActionListener(new ExplorePlayButtonListener(numberModel,pieChartsPanel,frame));

        return explorePanel;
    }

    private static class ExplorePlayButtonListener implements ActionListener {

        SpinnerNumberModel spinnerNumberModel;
        JPanel panelToAddCharts;
        JFrame frame;

        public ExplorePlayButtonListener(SpinnerNumberModel spinnerNumberModel, JPanel panelToAddCharts, JFrame frame) {
            this.spinnerNumberModel = spinnerNumberModel;
            this.panelToAddCharts = panelToAddCharts;
            this.frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            panelToAddCharts.removeAll();

            ExploringProblem exploringProblem = new ExploringProblem(spinnerNumberModel.getNumber().intValue());

            PieChartCreator choiceChangedGamesChart = new PieChartCreator("Games with changing choice",
                    new String[]{"Wins", "Fails"}, new double[]{exploringProblem.getNumOfWinsChoiceChanged(),
                    exploringProblem.getNumOfFailsChoiceChanged()});
            choiceChangedGamesChart.setLabelFormat("{0}: {2} = {1}");
            JPanel choiceChangedChartPanel = choiceChangedGamesChart.createChartPanel();
            panelToAddCharts.add(choiceChangedChartPanel,"w 550!, h 350!");

            PieChartCreator noChangeInChoiceGamesChart = new PieChartCreator("Games without changing choice",
                    new String[]{"Wins", "Fails"}, new double[]{exploringProblem.getNumOfWinsNoChangeInChoice(),
                    exploringProblem.getNumOfFailsNoChangeInChoice()});
            choiceChangedGamesChart.setLabelFormat("{0}: {2} = {1}");
            JPanel noChangeInChoiceChartPanel = noChangeInChoiceGamesChart.createChartPanel();
            panelToAddCharts.add(noChangeInChoiceChartPanel,"w 550!, h 350!");

            frame.setVisible(false);
            frame.setVisible(true);
        }
    }

}
