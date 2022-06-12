package com.company;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class GreetingGui {

    public static final boolean[][] prices = {{true,false,false},{false,true,false},{false,false,true}};
    private JPanel examplesPanel;
    private JPanel resultsPanel;

    public JPanel createPanel(JFrame frame,JButton startGameButton){

        JPanel mainPanel = new JPanel(new MigLayout("fillx"));

        JPanel greetingPanel = new JPanel(new MigLayout("fillx, insets 10"));
        mainPanel.add(greetingPanel,"align center");

        JLabel title = new JLabel("Hello!");
        title.setFont(Gui.TITLE_1);
        greetingPanel.add(title,"align center, wrap 20px");

        JTextArea suppose = new JTextArea("«   Suppose you're on a game show, and you're given the choice of three doors: Behind one " +
                "door is a car; behind the others, goats. You pick a door, say No. 1, and the host, who knows what's " +
                "behind the doors, opens another door, say No. 3, which has a goat. He then says to you, \"Do you want " +
                "to pick door No. 2?\" Is it to your advantage to switch your choice?   »",0,67);
        suppose.setFont(Gui.PLAIN_TEXT_FONT.deriveFont(Font.ITALIC));
        suppose.setForeground(Color.BLUE);

        JTextArea paradoxExplanation = new JTextArea("   The Monty Hall paradox is one of the known problems of probability theory, the solution of which seems " +
                "at first to contradict common sense. It turns out that you are more likely to win a car if you change the " +
                "choice! Below you can read why.",0, 67);
        paradoxExplanation.setFont(Gui.SMALL_TEXT_FONT);

        JTextArea youCanSkip = new JTextArea("   I made this game so you can see that it is really better to change " +
                "the choice. You can read the explanation below to understand why or start the game by pressing the " +
                "\"Start a game\" button (You you will be able to return to this page at any time using the menu).",0,67);
        youCanSkip.setFont(Gui.SMALL_TEXT_FONT.deriveFont(Font.BOLD));

        suppose.setEditable(false);
        suppose.setLineWrap(true);
        suppose.setWrapStyleWord(true);
        paradoxExplanation.setEditable(false);
        paradoxExplanation.setLineWrap(true);
        paradoxExplanation.setWrapStyleWord(true);
        youCanSkip.setEditable(false);
        youCanSkip.setLineWrap(true);
        youCanSkip.setWrapStyleWord(true);

        greetingPanel.add(suppose,"align center, wrap 20px");
        greetingPanel.add(paradoxExplanation,"wrap 10px");
        greetingPanel.add(youCanSkip,"wrap 20px");

        JLabel examplesTitle = new JLabel("Lets choose the door number ");
        examplesTitle.setFont(Gui.TITLE_1);
        greetingPanel.add(examplesTitle,"split 2, align center");

        Integer[] doorNums = new Integer[]{1,2,3};
        JComboBox doorNumChoose = new JComboBox(doorNums);
        doorNumChoose.setFont(Gui.TITLE_1);

        greetingPanel.add(doorNumChoose,"wrap");

        JPanel reviewPanel = new JPanel(new MigLayout("","[]80[]"));
        greetingPanel.add(reviewPanel,"wrap 20px");

        examplesPanel = new JPanel(new MigLayout());
        reviewPanel.add(examplesPanel);

        resultsPanel = new JPanel(new MigLayout("","[]80[]"));
        reviewPanel.add(resultsPanel);

        JLabel changeChoiceLabel = new JLabel("<html>Result if<br/>change choice</html>");
        changeChoiceLabel.setFont(Gui.TITLE_2);
        resultsPanel.add(changeChoiceLabel,"align center");

        JLabel notChangeChoiceLabel = new JLabel("<html>Result if not<br/>change choice</html>");
        notChangeChoiceLabel.setFont(Gui.TITLE_2);
        resultsPanel.add(notChangeChoiceLabel,"align center, wrap");

        doorNumChoose.addActionListener(e -> {

            resultsPanel.removeAll();
            resultsPanel.add(changeChoiceLabel,"align center");
            resultsPanel.add(notChangeChoiceLabel,"align center, wrap");

            createResults((Integer) doorNumChoose.getSelectedItem());
            frame.setVisible(false);
            frame.setVisible(true);
        });

        JLabel doorInfo1 = new JLabel("<html>\u2800<br/>Door 1</html>");
        doorInfo1.setFont(Gui.TITLE_2);
        examplesPanel.add(doorInfo1,"align center, cell 0 0");
        JLabel doorInfo2 = new JLabel("<html>\u2800<br/>Door 2</html>");
        doorInfo2.setFont(Gui.TITLE_2);
        examplesPanel.add(doorInfo2,"align center, cell 1 0");
        JLabel doorInfo3 = new JLabel("<html>\u2800<br/>Door 3</html>");
        doorInfo3.setFont(Gui.TITLE_2);
        examplesPanel.add(doorInfo3,"align center, cell 2 0");

        createResults(1);
        createExamples();

        JPanel outcomeAndButton = new JPanel(new MigLayout("fillx"));
        greetingPanel.add(outcomeAndButton);

        JLabel outcomeExplanation = new JLabel("<html>So as you can see, if you choose a door with a goat behind it, " +
                "and switch your choice, you win a car. There are 2 doors with goats behind them, and only 1 door with a car. " +
                "Thats why the probability of winning is just like that.</html>");
        outcomeExplanation.setFont(Gui.SMALL_TEXT_FONT);
        outcomeAndButton.add(outcomeExplanation);

        outcomeAndButton.add(startGameButton,"w 300!, h 60!,span 1 3, gapleft 100, wrap");

        JLabel outcome = new JLabel("<html>No matter what door we choose first, the probability of win<br/>" +
                "is always 2/3 (67%) if we change choice and 1/3 if not.</html>");
        outcome.setFont(Gui.SMALL_TEXT_FONT.deriveFont(Font.BOLD));
        outcomeAndButton.add(outcome,"wrap");

        SwingLink wikipediaLink = new SwingLink("Want to know more? Read wikipedia.",
                "https://en.wikipedia.org/wiki/Monty_Hall_problem");
        wikipediaLink.setFont(Gui.SMALL_TEXT_FONT);
        outcomeAndButton.add(wikipediaLink);

        return mainPanel;
    }

    private void createExamples(){

        for (int i = 0; i< prices.length; i++) {
            for (int j = 0; j < prices[0].length; j++) {
                if (prices[i][j]){
                    examplesPanel.add(new DrawPanel("car" + (i+1) + ".png"),"cell " + i + " " + (j+1));
                }else {
                    examplesPanel.add(new DrawPanel("goat" + (i+1) + ".png"),"cell " + i + " " + (j+1));
                }
            }
        }
    }

    private void createResults(int choosedDoorNum){

        for (int j = 0; j < prices[0].length; j++){
            // if there is a car behind the choosed door
            if (prices[choosedDoorNum - 1][j]) {
                // result if change choice
                resultsPanel.add(new DrawPanel("goat.png"));
                // result if not change choice
                resultsPanel.add(new DrawPanel("car.png"),"wrap");
            } else {
                // result if change choice
                resultsPanel.add(new DrawPanel("car.png"));
                // result if not change choice
                resultsPanel.add(new DrawPanel("goat.png"),"wrap");
            }
        }
    }
}