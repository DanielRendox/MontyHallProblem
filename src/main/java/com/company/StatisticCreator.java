package com.company;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class StatisticCreator {

    private ArrayList<Game> gamesList;
    private int numOfWinsChoiceChanged;
    private int numOfFailsChoiceChanged;
    private int numOfWinsNoChangeInChoice;
    private int numOfFailsNoChangeInChoice;

    public StatisticCreator(File fileWithStatistic) {
        GameSaverOpener gameSaverOpener = new GameSaverOpener(fileWithStatistic);
        gamesList = gameSaverOpener.openGameStat();
        findStatistic();
    }

    /**
     *      Use this constructor only if you already have GameSaverOpener that has just opened
     *      gamesList
     */
    public StatisticCreator(GameSaverOpener gameSaverOpener) {
        gamesList = gameSaverOpener.getGamesList();
        findStatistic();
    }

    private void findStatistic() {

        if (gamesList == null){
            return;
        }

        for (Game openedGame: gamesList){

            if (openedGame.getGameResult()){
                if (openedGame.isChoiceChanged()){
                    numOfWinsChoiceChanged++;
                }else {
                    numOfWinsNoChangeInChoice++;
                }
            }else{
                if (openedGame.isChoiceChanged()){
                    numOfFailsChoiceChanged++;
                }else {
                    numOfFailsNoChangeInChoice++;
                }
            }
        }
    }

    public JPanel createChoiceChangedPieChart(){

        if (gamesList == null){
            return null;
        }

        JPanel pieChartPanel = new JPanel(new MigLayout("fill"));

        PieChartCreator choiceChangedGamesChart = new PieChartCreator("Games with changing choice",
                new String[]{"Wins", "Fails"}, new double[]{numOfWinsChoiceChanged,numOfFailsChoiceChanged});

        pieChartPanel.add(choiceChangedGamesChart.createChartPanel(),"align center");

        Dimension dimension = new Dimension(450,270);
        pieChartPanel.setMinimumSize(dimension);
        pieChartPanel.setPreferredSize(dimension);

        return pieChartPanel;
    }

    public JPanel createNoChangeInChoicePieChart(){

        if (gamesList == null){
            return null;
        }

        JPanel pieChartPanel = new JPanel(new MigLayout("fill"));

        PieChartCreator noChangeInChoiceGamesChart = new PieChartCreator("Games without changing choice",
                new String[]{"Wins", "Fails"}, new double[]{numOfWinsNoChangeInChoice,numOfFailsNoChangeInChoice});

        pieChartPanel.add(noChangeInChoiceGamesChart.createChartPanel(),"align center");

        Dimension dimension = new Dimension(450,270);
        pieChartPanel.setMinimumSize(dimension);
        pieChartPanel.setPreferredSize(dimension);

        return pieChartPanel;
    }

    public JTextArea createNumbersTextArea(){

        int numOfGames = numOfWinsChoiceChanged + numOfWinsNoChangeInChoice +
                numOfFailsChoiceChanged + numOfFailsNoChangeInChoice;

        String numbersText = "Number of games: " + numOfGames + "\nNumber of wins: " +
                (numOfWinsChoiceChanged + numOfWinsNoChangeInChoice) + "\nNumber of fails: " +
                (numOfFailsChoiceChanged + numOfFailsNoChangeInChoice);

        JTextArea numbersTextArea = new JTextArea();
        numbersTextArea.setFont(Gui.PLAIN_TEXT_FONT);
        numbersTextArea.setEditable(false);
        numbersTextArea.append(numbersText);

        return numbersTextArea;
    }
}
