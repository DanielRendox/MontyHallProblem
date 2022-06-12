package com.company;

import java.util.Random;

public class ExploringProblem {

    private final Game game = new Game(3);
    public final int numOfRetries;
    private int numOfWinsChoiceChanged;
    private int numOfFailsChoiceChanged;
    private int numOfWinsNoChangeInChoice;
    private int numOfFailsNoChangeInChoice;
    private float winningProbabilityChoiceChanged;
    private float winningProbabilityNoChangeInChoice;

    public ExploringProblem(int numOfRetries){
        this.numOfRetries = numOfRetries;
        explore();
    }

    public int getNumOfWinsChoiceChanged() {
        return numOfWinsChoiceChanged;
    }
    public int getNumOfFailsChoiceChanged() {
        return numOfFailsChoiceChanged;
    }
    public int getNumOfWinsNoChangeInChoice() {
        return numOfWinsNoChangeInChoice;
    }
    public int getNumOfFailsNoChangeInChoice() {
        return numOfFailsNoChangeInChoice;
    }
    public float getWinningProbabilityChoiceChanged() {
        return winningProbabilityChoiceChanged;
    }
    public float getWinningProbabilityNoChangeInChoice() {
        return winningProbabilityNoChangeInChoice;
    }

    private void explore(){

        for (int i = 0; i< numOfRetries; i++){
            if (play(true)){
                numOfWinsChoiceChanged++;
            }else {
                numOfFailsChoiceChanged++;
            }
        }

        winningProbabilityChoiceChanged = ((float) numOfWinsChoiceChanged/ (float) numOfRetries) * 100;

        for (int i = 0; i< numOfRetries; i++){
            if (play(false)){
                numOfWinsNoChangeInChoice++;
            }else {
                numOfFailsNoChangeInChoice++;
            }
        }

        winningProbabilityNoChangeInChoice = ((float) numOfWinsNoChangeInChoice/ (float) numOfRetries) * 100;
    }

    private boolean play(boolean changeChoice){

        game.setChoiceChanged(changeChoice);

        Random random = new Random();
        game.setFirstUsersChoice(random.nextInt(3) + 1);

        if (changeChoice){
            changeChoice();
        }else {
            int i = game.getFirstUsersChoice();
            game.setSecondUsersChoice(i);
        }

        return game.getGameResult();
    }

    /**
     *              Chooses the door from the gameEngine.doors array, different from the door where the goat is
     *              (this information the game gives us) and the door of the previous choice.
     */
    private void changeChoice(){
        int choice = 0;

        for(int i = 1; i<(game.doors.length + 1); i++){
            if(i != game.getFirstUsersChoice() && i != game.getIndexOfOpenedDoor())
                choice = i;
        }
        game.setSecondUsersChoice(choice);
    }
}
