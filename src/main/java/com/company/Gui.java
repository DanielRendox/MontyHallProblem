package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Gui {

    private JFrame frame;
    private CardLayout mainCardLayout;
    private JPanel mainPanel;
    private GameHistoryGui historyGui;
    private Game game;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Gui(Game game) {
        this.game = game;
    }

    public static final Font TITLE_1 = new Font("MS Sans Serif", Font.PLAIN, 25);
    public static final Font TITLE_2 = new Font("MS Sans Serif", Font.PLAIN,20);
    public static final Font PLAIN_TEXT_FONT = new Font("MS Sans Serif", Font.PLAIN, 18);
    public static final Font SMALL_TEXT_FONT = new Font("MS Sans Serif", Font.PLAIN, 16);
    public static final Font SUBSCRIPT_FONT = new Font("MS Sans Serif",Font.PLAIN,13);

    public void createAndShowGui(){
        frame = new JFrame("Monty Hall Game");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainCardLayout = new CardLayout();
        mainPanel = new JPanel(mainCardLayout);
        frame.getContentPane().add(mainPanel,BorderLayout.CENTER);

        addComponentsOnFrame();
        createMenu();

        frame.setSize(1140,950);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createMenu(){
        JMenu menu = new JMenu("Menu");
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        JMenuItem gameMenuItem = new JMenuItem("Game");
        gameMenuItem.addActionListener(e -> mainCardLayout.show(mainPanel,"gamePanel"));
        menu.add(gameMenuItem);

        JMenuItem historyMenuItem = new JMenuItem("History of your games");
        historyMenuItem.addActionListener(e -> showHistoryPanel());
        menu.add(historyMenuItem);

        JMenuItem computerPlaysMenuItem = new JMenuItem("Computer plays");
        computerPlaysMenuItem.addActionListener(e -> mainCardLayout.show(mainPanel,"explorePanel"));
        menu.add(computerPlaysMenuItem);

        JMenuItem aboutGameMenuItem = new JMenuItem("About the Monty Hall paradox");
        aboutGameMenuItem.addActionListener(e -> mainCardLayout.show(mainPanel,"greetingPanel"));
        menu.add(aboutGameMenuItem);

        JMenuItem citedPageMenuItem = new JMenuItem("Cited page");
        citedPageMenuItem.addActionListener(e -> mainCardLayout.show(mainPanel,"citedPanel"));
        menu.add(citedPageMenuItem);
    }

    private void addComponentsOnFrame(){

        File gameFile = new File("GameHistory.ser");

        GameGui gameGui = new GameGui(gameFile);
        mainPanel.add(gameGui.createPanel(),"gamePanel");

        JButton openHistoryButton = new JButton("All history");
        openHistoryButton.addActionListener(e -> showHistoryPanel());
        gameGui.addOpenHistoryButton(openHistoryButton);

        historyGui = new GameHistoryGui(gameFile);
        mainPanel.add(historyGui.createPanel(),"historyPanel");

        mainPanel.add(GameExploreGui.createPanel(frame),"explorePanel");
        mainPanel.add(CitedPageGui.createPanel(),"citedPanel");

        JButton startGameButton = new JButton("Start the game");
        startGameButton.addActionListener(e -> mainCardLayout.show(mainPanel,"gamePanel"));
        startGameButton.setFont(Gui.TITLE_2);
        mainPanel.add(new GreetingGui().createPanel(frame,startGameButton),"greetingPanel");

        mainCardLayout.show(mainPanel,"greetingPanel");
    }

    /** Returns an ImageIcon, or null if the fileName was invalid. */
    public static ImageIcon createImageIcon(String fileName) {

        File sourceFile = new File("src/main/resources",fileName);
        if (sourceFile.exists()) {
            return new ImageIcon(sourceFile.getAbsolutePath());
        } else {
            System.err.println("Couldn't find file: " + fileName);
            return null;
        }
    }

    private void showHistoryPanel(){

        historyGui.createHistory();

        mainCardLayout.show(mainPanel,"historyPanel");
    }
}
