package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class DrawPanel extends JPanel {

    public final Image image;

    public DrawPanel(String imageFileName) {
        image = Objects.requireNonNull(Gui.createImageIcon(imageFileName)).getImage();
        setPreferredSize(new Dimension(image.getWidth(this),image.getHeight(this)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        ScaleImageDrawer.drawScaledImage(image,this,(Graphics2D) g);
    }
}