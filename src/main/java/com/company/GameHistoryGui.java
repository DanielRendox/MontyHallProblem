package com.company;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class GameHistoryGui {

    private CardLayout mainCardLayout;
    private JPanel mainPanel;
    private JPanel historyPanel;
    private File fileWithHistory;
    private long fileWithHistoryLastModified;

    public File getFileWithHistory() {
        return fileWithHistory;
    }

    public void setFileWithHistory(File fileWithHistory) {
        this.fileWithHistory = fileWithHistory;
    }

    public GameHistoryGui(File fileWithHistory) {
        this.fileWithHistory = fileWithHistory;
    }

    public JPanel createPanel(){

        mainCardLayout = new CardLayout();
        mainPanel = new JPanel(mainCardLayout);

        createNoHistoryPanel();
        createHistoryPanel();

        mainCardLayout.show(mainPanel,"noHistoryPanel");

        return mainPanel;
    }

    private void createNoHistoryPanel(){

        JPanel noStatPanel = new JPanel(new MigLayout("fill"));

        JLabel noInfoLabel = new JLabel("There is no history");
        noInfoLabel.setFont(Gui.SMALL_TEXT_FONT);
        noInfoLabel.setForeground(Color.GRAY);
        noStatPanel.add(noInfoLabel,"align center");

        mainPanel.add(noStatPanel,"noHistoryPanel");
    }

    private void createHistoryPanel(){

        JPanel mainHistoryPanel = new JPanel(new MigLayout("insets 10, fillx"));
        mainPanel.add(mainHistoryPanel,"historyPanel");

        JLabel title = new JLabel("GAME HISTORY");
        title.setFont(Gui.TITLE_1);
        mainHistoryPanel.add(title,"align center, wrap");

        JLabel subtitle = new JLabel("<html>If you doubt the results, see the history<br/>" +
                "to make sure that the program works correctly</html>");
        subtitle.setFont(Gui.PLAIN_TEXT_FONT);
        subtitle.setForeground(Color.GRAY);
        mainHistoryPanel.add(subtitle,"align center, wrap 20px");

        historyPanel = new JPanel(new MigLayout());
        JScrollPane historyScrollPane = new JScrollPane(historyPanel);
        historyScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        // this method is used to control the scroll speed in a JScrollPane when using the mouse wheel by
        // changing the value of the setUnitIncrement() pararameter
        historyScrollPane.getVerticalScrollBar().setUnitIncrement(20);
        mainHistoryPanel.add(historyScrollPane, "align center");
    }

    /**
     *      Method adds history on the historyPanel only if fileWithHistory.exists() and history hasn't been
     *      created yet or if it was changed so we need to get and show it once again
     */
    public void createHistory(){

        if (fileWithHistory.exists() && fileWithHistoryLastModified != fileWithHistory.lastModified()){

            fileWithHistoryLastModified = fileWithHistory.lastModified();

            GameSaverOpener gameSaverOpener = new GameSaverOpener(fileWithHistory);
            ArrayList<Game> gamesList = gameSaverOpener.openGameStat();

            historyPanel.removeAll();
            for (Game openedGame: gamesList) {
                historyPanel.add(HistoryCreator.createGameHistory(openedGame, HistoryCreator.BIGPANEL), "wrap -20px");
            }

            mainCardLayout.show(mainPanel, "historyPanel");
        }
    }
}
