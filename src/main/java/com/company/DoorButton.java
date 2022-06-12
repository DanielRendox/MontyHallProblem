package com.company;

import javax.swing.*;

public class DoorButton extends JButton {

    private int doorNum;
    private boolean isOpened;
    public static final ImageIcon closedDoorImageIcon = Gui.createImageIcon("closedDoor.png");
    public static final ImageIcon openedDoorWithGoatImageIcon = Gui.createImageIcon("openedDoorWithGoat.png");
    public static final ImageIcon openedDoorWithQuestionImageIcon = Gui.createImageIcon("questionDoor.png");

    public int getDoorNum() {
        return doorNum;
    }
    public void setDoorNum(int doorNum) {
        this.doorNum = doorNum;
    }

    @Override
    public void setIcon(Icon defaultIcon) {
        super.setIcon(defaultIcon);
        super.setDisabledIcon(this.getIcon());
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public DoorButton(int doorNum) {
        super(Integer.toString(doorNum));
        super.setIcon(closedDoorImageIcon);
        super.setDisabledIcon(this.getIcon());
        this.doorNum = doorNum;
        super.setFont(Gui.TITLE_2);
    }
}
