package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class Game implements Serializable{

    private static final long serialVersionUID = -6740251572201239090L;

    private int firstUsersChoice;
    private int secondUsersChoice;
    public final boolean[] doors;
    private int indexOfOpenedDoor;
    private boolean gameResult;
    private Date date;
    private boolean isChoiceChanged;

    public Game(int doorsNum) {
        doors = placePrizes(doorsNum);
    }

    public Game(boolean[] doors) {
        this.doors = doors;
    }

    public int getFirstUsersChoice() {
        return firstUsersChoice;
    }
    public void setFirstUsersChoice(int firstUsersChoice) {
        this.firstUsersChoice = firstUsersChoice;
        calculateIndexOfOpenedDoor();
    }

    public int getSecondUsersChoice() {
        return secondUsersChoice;
    }

    public void setSecondUsersChoice(int secondUsersChoice) {
        this.secondUsersChoice = secondUsersChoice;
    }

    public int getIndexOfOpenedDoor() {
        return indexOfOpenedDoor;
    }
    public boolean getGameResult() {
        gameResult = doors[secondUsersChoice-1];
        return gameResult;
    }

    public boolean isChoiceChanged() {
        return isChoiceChanged;
    }

    public void setChoiceChanged(boolean choiceChanged) {
        isChoiceChanged = choiceChanged;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /**
     *              Method randomly places goats and a car. That is, assigns true to one of the array elements,
     *              and false to the others. True value interprets a car and false ones interpret goats.
     * @return      boolean array that has one true value and array.length-1 false values
     */
    private boolean[] placePrizes(int doorsNum){

        boolean[] doors = new boolean[doorsNum];

        Arrays.fill(doors, false);
        Random random = new Random();
        doors[random.nextInt(doorsNum)] = true;

        return doors;
    }

    /**
     *              The method selects the door with a goat from the doors left after the users choice and assigns it's
     *              number (from 1 to 3) to the indexOfOpenedDoor variable.
     */
    public void calculateIndexOfOpenedDoor(){

        // Using the cycle fill the ArrayList with the numbers of doors from the doors array. Skip that door which
        // contain a car, and the door that was been chosen.
        ArrayList<Integer> goats = new ArrayList<>(doors.length - 1);
        for(int i=0; i<doors.length; i++){
            if(!doors[i] && i != (firstUsersChoice - 1)){
                goats.add(i+1);
            }
        }

        indexOfOpenedDoor = (int) RandomOperations.randomElementFromList(goats);
    }

    public static String doorsToString(boolean[] doors) {
        if (doors == null)
            return "null";
        int iMax = doors.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        for (int i = 0; ; i++) {
            if (doors[i]) {
                stringBuilder.append("car");
            } else stringBuilder.append("goat");
            if (i == iMax) return stringBuilder.append(']').toString();
            stringBuilder.append(", ");
        }
    }

}
