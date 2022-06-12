package com.company;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class GameSaverOpener {

    private File statFile;
    private ArrayList<Game> gamesList;

    public ArrayList<Game> getGamesList() {
        return gamesList;
    }

    public GameSaverOpener(File statFile) {
        this.statFile = statFile;
    }

    public File getStatFile() {
        return statFile;
    }

    public void setStatFile(File statFile) {
        this.statFile = statFile;
    }

    /**
     *      Serializes the all game class in the saveFile. Also assings the value to the data variable
     *      and saves it.
     */
    public void saveGameStat(Game game){
        
        game.setDate(new Date());
        
        gamesList = openGameStat();

        if (gamesList == null){
            gamesList = new ArrayList<>();
        }
        
        gamesList.add(game);

        try(FileOutputStream fileOutputStream = new FileOutputStream(statFile);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream)){
            
            objectOutputStream.writeObject(gamesList);

        }catch (IOException ex){
            JOptionPane.showMessageDialog(null,
                    "Failed to save the result of your game :(\n" +
                            "Error Message: " + ex.getMessage(),
                    "Cannot Save Result", JOptionPane.WARNING_MESSAGE);
        }
    }

    public ArrayList<Game> openGameStat(){
        if(statFile.exists()) {
            try (FileInputStream fileInputStream = new FileInputStream(statFile);
                 BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                 ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream)) {

                @SuppressWarnings("unchecked") ArrayList<Game> result = (ArrayList<Game>) objectInputStream.readObject();
                return result;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null,
                        "Failed to open statistic of your game :(\n" +
                                "Error Message: " + ex.getMessage(),
                        "Cannot Open Statistic", JOptionPane.WARNING_MESSAGE);
            }
        }
        return null;
    }
}
