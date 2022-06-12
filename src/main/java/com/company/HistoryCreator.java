package com.company;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.text.SimpleDateFormat;

public class HistoryCreator {

    public static final int SMALLPANEL = 1;
    public static final int BIGPANEL = 2;

    public static JPanel createGameHistory(Game game, int size){

        JPanel mainPanel = new JPanel(new MigLayout("fill"));

        JLabel demarcationLabel = new JLabel("-------------------------------------------------------------------------");

        mainPanel.add(demarcationLabel,"wrap, span");

        DrawPanel picturePanel;

        boolean[] doors = game.doors;
        for(int i=0;i<doors.length;i++){
            if (doors[i]){
                picturePanel = new DrawPanel("car" + (i+1) + ".png");
                mainPanel.add(picturePanel);
            } else {
                picturePanel = new DrawPanel("goat" + (i+1) + ".png");
                mainPanel.add(picturePanel);
            }
        }

        JPanel textPanel = new JPanel(new MigLayout());
        mainPanel.add(textPanel,"wrap");

        StringBuilder text = new StringBuilder();

        SimpleDateFormat saveDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        text.append(saveDateFormat.format(game.getDate())).append("\n");

        if (game.getGameResult()){
            text.append("Result: win\n");
        }else{
            text.append("Result: fail\n");
        }

        text.append("Choice changed: ").append(game.isChoiceChanged()).append("\n");

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.append(text.toString());
        textPanel.add(textArea);

        JLabel firstChoiceInfo = new JLabel("First choice");
        mainPanel.add(firstChoiceInfo,"align center, cell " + (game.getFirstUsersChoice()-1) + " 2");

        JLabel secondChoiceInfo = new JLabel();

        if (game.isChoiceChanged()){
            secondChoiceInfo.setText("Second choice");
            secondChoiceInfo.setFont(Gui.SUBSCRIPT_FONT);
            mainPanel.add(secondChoiceInfo, "align center, cell " + (game.getSecondUsersChoice() - 1) + " 2");
        }

        JLabel openedInfo = new JLabel("Opened");
        openedInfo.setFont(Gui.SUBSCRIPT_FONT);
        mainPanel.add(openedInfo,"align center, cell " + (game.getIndexOfOpenedDoor()-1) + " 2");


        if (size == SMALLPANEL){
            demarcationLabel.setFont(Gui.TITLE_2);
            textArea.setFont(Gui.SMALL_TEXT_FONT);
            firstChoiceInfo.setFont(Gui.SUBSCRIPT_FONT);
            secondChoiceInfo.setFont(Gui.SUBSCRIPT_FONT);
            openedInfo.setFont(Gui.SUBSCRIPT_FONT);
        }else {
            demarcationLabel.setFont(Gui.TITLE_1);
            textArea.setFont(Gui.PLAIN_TEXT_FONT);
            firstChoiceInfo.setFont(Gui.SMALL_TEXT_FONT);
            secondChoiceInfo.setFont(Gui.SMALL_TEXT_FONT);
            openedInfo.setFont(Gui.SMALL_TEXT_FONT);
        }

        return mainPanel;
    }

}
