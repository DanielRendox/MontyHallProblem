package com.company;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GameGui {

    private Game game;
    private JPanel mainPanel;
    private CardLayout mainCardLayout;
    private JPanel gamePanel;
    private CardLayout userContactCardLayout;
    private JPanel userContactPanel;
    private JPanel pieChartsPanel;
    private CardLayout endImageCardLayout;
    private JPanel endImagePanel;
    private JLabel infoLabel;
    private JLabel gameEndInfo;
    private JPanel endButtonsPanel;
    private JPanel historyPanel;
    private DoorButton[] doorButtonsArray;
    private int leftDoorNum;
    private File gameFile;

    public File getGameFile() {
        return gameFile;
    }

    public void setGameFile(File gameFile) {
        this.gameFile = gameFile;
    }

    public GameGui(File gameFile) {
        game = new Game(3);
        this.gameFile = gameFile;
    }

    public JPanel createPanel() {
        mainCardLayout = new CardLayout();
        mainPanel = new JPanel(mainCardLayout);

        createGamePanel();
        createGameEndPanel();

        mainCardLayout.show(mainPanel, "gamePanel");

        return mainPanel;
    }

    private void createGamePanel() {
        gamePanel = new JPanel(new MigLayout(
                "fillx, insets 40",            // Layout constraints
                "",                        // Column constraints
                "[]20[]20[]"));            // Row constraints
        mainPanel.add(gamePanel, "gamePanel");

        createUserContactPanel();
        createDoorsPanel();
    }

    private void createUserContactPanel() {

        // use this syntax (<html> <br/> </html>) , because otherwise JLabel doesn't switch to a new line
        infoLabel = new JLabel("<html>You have " + game.doors.length + " doors.<br/>" +
                "There is a car behind one of them, goats are behind the others.<br/>" +
                "Choose one door to start the game.</html>");
        infoLabel.setFont(Gui.PLAIN_TEXT_FONT);
        gamePanel.add(infoLabel, "align center, wrap");

        userContactCardLayout = new CardLayout();
        userContactPanel = new JPanel(userContactCardLayout);
        gamePanel.add(userContactPanel, "align center, wrap, span");

        createChangeChoicePanel();
        createNextButtonPanel();

        JPanel emptyPanel = new JPanel();
        userContactPanel.add(emptyPanel, "emptyPanel");
        userContactCardLayout.show(userContactPanel, "emptyPanel");
    }

    private void createChangeChoicePanel() {
        JPanel changeChoicePanel = new JPanel(new MigLayout("", "[]60[]"));
        userContactPanel.add(changeChoicePanel, "changeChoicePanel");

        JButton yesButton = new JButton("Yes");
        yesButton.addActionListener((e -> {
            game.setChoiceChanged(true);
            game.setSecondUsersChoice(leftDoorNum);
            showEndPanel();
        }));
        yesButton.setFont(Gui.TITLE_2);
        changeChoicePanel.add(yesButton, "w 200!, h 50!");
        JButton noButton = new JButton("No");
        noButton.addActionListener((e -> {
            game.setSecondUsersChoice(game.getFirstUsersChoice());
            showEndPanel();
        }));
        noButton.setFont(Gui.TITLE_2);
        changeChoicePanel.add(noButton, "w 200!, h 50!");
    }

    private void createNextButtonPanel() {
        JPanel nextButtonPanel = new JPanel(new MigLayout("fillx"));
        userContactPanel.add(nextButtonPanel, "nextButtonPanel");

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener((e -> {
            userContactCardLayout.show(userContactPanel, "changeChoicePanel");
            doorButtonsArray[game.getIndexOfOpenedDoor() - 1].setIcon(DoorButton.openedDoorWithGoatImageIcon);
            doorButtonsArray[game.getIndexOfOpenedDoor() - 1].setOpened(true);

            for (DoorButton doorButton: doorButtonsArray){
                if (!doorButton.isOpened()){
                    leftDoorNum = doorButton.getDoorNum();
                }
            }

            infoLabel.setText("<html>There is a goat behind the door number " +
                    game.getIndexOfOpenedDoor() + ".<br/>" +
                    "Do you want to choose the door number " + leftDoorNum + " instead?</html>");
        }));
        nextButton.setFont(Gui.TITLE_2);
        nextButtonPanel.add(nextButton, "w 200!, h 50!, align right");
    }

    /**
     * Creates buttons. Assigns an ImageIcon and text to each button. Text contains number of the button.
     * Text is on the center of the bottom of the frame. Creates label above the buttons.
     * Label contains the following text "You have " + gameEngine.doorsNum + " doors".
     */
    private void createDoorsPanel() {

        JPanel doorsPanel = new JPanel(new MigLayout("", "[]20[]20[]"));

        gamePanel.add(doorsPanel, "align center, wrap");

        DoorButton doorButton1 = new DoorButton(1);
        DoorButton doorButton2 = new DoorButton(2);
        DoorButton doorButton3 = new DoorButton(3);

        doorButton1.setVerticalTextPosition(AbstractButton.BOTTOM);
        doorButton1.setHorizontalTextPosition(AbstractButton.CENTER);
        doorButton2.setVerticalTextPosition(AbstractButton.BOTTOM);
        doorButton2.setHorizontalTextPosition(AbstractButton.CENTER);
        doorButton3.setVerticalTextPosition(AbstractButton.BOTTOM);
        doorButton3.setHorizontalTextPosition(AbstractButton.CENTER);

        DoorButtonListener doorButtonListener = new DoorButtonListener();
        doorButton1.addActionListener(doorButtonListener);
        doorButton2.addActionListener(doorButtonListener);
        doorButton3.addActionListener(doorButtonListener);

        doorsPanel.add(doorButton1);
        doorsPanel.add(doorButton2);
        doorsPanel.add(doorButton3);

        doorButtonsArray = new DoorButton[3];
        doorButtonsArray[0] = doorButton1;
        doorButtonsArray[1] = doorButton2;
        doorButtonsArray[2] = doorButton3;
    }

    private void createGameEndPanel() {
        // Layout Constraints
        // Column constraints
        JPanel gameEndPanel = new JPanel(new MigLayout(
                "fillx",            // Layout Constraints
                "[]40[]",            // Column constraints
                "[]40[]30[]"));            // Row constraints
        mainPanel.add(gameEndPanel, "gameEndPanel");

        gameEndInfo = new JLabel();
        gameEndInfo.setFont(Gui.TITLE_1);
        gameEndInfo.setHorizontalAlignment(SwingConstants.CENTER);
        gameEndPanel.add(gameEndInfo, "align center, span, wrap");

        pieChartsPanel = new JPanel(new MigLayout("fillx"));
        gameEndPanel.add(pieChartsPanel);

        JPanel endImageAndHistoryPanel = new JPanel(new MigLayout("fillx", "", "[]20[]-10[]"));
        gameEndPanel.add(endImageAndHistoryPanel,"wrap");

        endImageCardLayout = new CardLayout();
        endImagePanel = new JPanel(endImageCardLayout);
        endImageAndHistoryPanel.add(endImagePanel, "align center, w 450::, h 350::, wrap");

        JLabel historyLabel = new JLabel("That’s how it was");
        historyLabel.setFont(Gui.TITLE_2);
        endImageAndHistoryPanel.add(historyLabel,"align center, wrap");

        historyPanel = new JPanel(new MigLayout());
        endImageAndHistoryPanel.add(historyPanel,"align left");

        createWinImagePanel();
        createLoseImagePanel();

        endButtonsPanel = new JPanel(new MigLayout("fillx"));
        gameEndPanel.add(endButtonsPanel,"span,align right");

        JButton restartButton = new JButton("Try again");
        restartButton.addActionListener((e -> {

            game = new Game(3);

            for (DoorButton doorButton: doorButtonsArray){
                doorButton.setEnabled(true);
                doorButton.setOpened(false);
                doorButton.setIcon(DoorButton.closedDoorImageIcon);
            }

            mainCardLayout.show(mainPanel, "gamePanel");
            infoLabel.setText("<html>You have " + game.doors.length + " doors.<br/>" +
                    "There is a car behind one of them, goats are behind the others.<br/>" +
                    "Choose one door to start the game.</html>");
            userContactCardLayout.show(userContactPanel,"emptyPanel");
        }));
        restartButton.setFont(Gui.TITLE_2);
        endButtonsPanel.add(restartButton,"w 500!, h 40!");
    }

    public void addOpenHistoryButton(JButton openHistoryButton) {
        openHistoryButton.setFont(Gui.TITLE_2);
        endButtonsPanel.add(openHistoryButton,"h 40");
    }

    private void createWinImagePanel() {

        JPanel winImagePanel = new JPanel(new MigLayout("fill"));
        endImagePanel.add(winImagePanel, "winImagePanel");

        DrawPanel winDrawPanel = new DrawPanel("carAward.png");
        winImagePanel.add(winDrawPanel,"align center");
    }

    private void createLoseImagePanel(){
        JPanel loseImagePanel = new JPanel(new MigLayout("fill"));
        endImagePanel.add(loseImagePanel,"loseImagePanel");

        DrawPanel loseDrawPanel = new DrawPanel("goatAward.png");
        loseImagePanel.add(loseDrawPanel,"align center");
    }

    private void showEndPanel(){

        GameSaverOpener gameSaverOpener = new GameSaverOpener(gameFile);
        gameSaverOpener.saveGameStat(game);

        if (game.getGameResult()){
            gameEndInfo.setText("Congratulations! You won a car!");
            endImageCardLayout.show(endImagePanel,"winImagePanel");
        }else {
            gameEndInfo.setText("You lost!");
            endImageCardLayout.show(endImagePanel,"loseImagePanel");
        }

        StatisticCreator statisticCreator = new StatisticCreator(gameSaverOpener);
        pieChartsPanel.removeAll();
        pieChartsPanel.add(statisticCreator.createNumbersTextArea(),"align center, wrap 20px");
        pieChartsPanel.add(statisticCreator.createChoiceChangedPieChart(),"wrap");
        pieChartsPanel.add(statisticCreator.createNoChangeInChoicePieChart());

        historyPanel.removeAll();
        historyPanel.add(HistoryCreator.createGameHistory(game,HistoryCreator.SMALLPANEL),"align center");

        mainCardLayout.show(mainPanel, "gameEndPanel");
    }

    private class DoorButtonListener implements ActionListener {

        /**
         * Defines what Button that interprets door has been clicked. Sets variable usersChoice
         * in the Game class to the number of this doorButton. Disables all doorButtons,
         * shows changeChoicePanel using cardLayout.
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            DoorButton selectedDoorButton = (DoorButton) e.getSource();
            selectedDoorButton.setIcon(DoorButton.openedDoorWithQuestionImageIcon);
            selectedDoorButton.setOpened(true);
            game.setFirstUsersChoice(selectedDoorButton.getDoorNum());
            infoLabel.setText("<html>Ok, you chose the door number " + selectedDoorButton.getDoorNum() + "." +
                    "<br/>Now I will show you where definitely the goat is and then" +
                    "<br/>you can decide to change your choice or not.</html>");
            userContactCardLayout.show(userContactPanel, "nextButtonPanel");

            for (DoorButton doorButton: doorButtonsArray){
                doorButton.setEnabled(false);
            }
        }
    }
}


