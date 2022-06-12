package com.company;

public class Main {

    public static void main(String[] args) {
        playUsingGui();
    }

    private static void playUsingGui(){

        Gui gui = new Gui(new Game(3));
        gui.createAndShowGui();
    }
}
