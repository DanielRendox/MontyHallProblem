package com.company;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class CitedPageGui {

    public static JPanel createPanel(){

        JPanel mainPanel = new JPanel(new MigLayout("fillx"));
        JPanel citedPagePanel = new JPanel(new MigLayout("insets 10","[]20[]"));
        mainPanel.add(citedPagePanel,"align center");

        JLabel titleAuthor = new JLabel("Author");
        titleAuthor.setFont(Gui.TITLE_1);
        citedPagePanel.add(titleAuthor, "wrap 20px, span, align center");

        citedPagePanel.add(new DrawPanel("hacker.png"),"wrap");

        JLabel titleThanks = new JLabel("THANKS!");
        titleThanks.setFont(Gui.TITLE_1);
        citedPagePanel.add(titleThanks, "wrap 20px, span, align center");

        // TODO Add information about me

        JLabel gratitudeForTheIdea = new JLabel("Thanks for the idea!");
        JLabel goatAwardExplanation = new JLabel("Goat award was made by");
        JLabel carAwardExplanation = new JLabel("Car award was made by");
        JLabel goatCardExplanation = new JLabel("Goat image on the door was made by");
        JLabel carCardExplanation = new JLabel("Сar image on the door was made by");
        JLabel anonymousHacker = new JLabel("Anonymous hacker vector was made by");
        JLabel swingLinkClassExplanation = new JLabel("SwingLink class of the code of this program was made by");

        gratitudeForTheIdea.setFont(Gui.PLAIN_TEXT_FONT);

        goatAwardExplanation.setFont(Gui.PLAIN_TEXT_FONT);
        carAwardExplanation.setFont(Gui.PLAIN_TEXT_FONT);
        goatCardExplanation.setFont(Gui.PLAIN_TEXT_FONT);
        carCardExplanation.setFont(Gui.PLAIN_TEXT_FONT);
        anonymousHacker.setFont(Gui.PLAIN_TEXT_FONT);
        swingLinkClassExplanation.setFont(Gui.PLAIN_TEXT_FONT);

        SwingLink gratitudeForTheIdeaLink = new SwingLink("Michael Vsauce",
                "https://www.youtube.com/c/vsauce1");
        SwingLink goatAwardLink = new SwingLink("www.freepik.com",
                "https://www.freepik.com/vectors/farm-landscape");
        SwingLink carAwardLink = new SwingLink("PIRO4D (Pixabay)",
                "https://pixabay.com/ru/users/piro4d-2707530");
        SwingLink goatCardLink = new SwingLink("Clker-Free-Vector-Images (Pixabay)",
                "https://pixabay.com/ru/users/clker-free-vector-images-3736");
        SwingLink carCardLink = new SwingLink("janjf93 (Pixabay)",
                "https://pixabay.com/ru/users/janjf93-3084263");
        SwingLink anonymousHackerLink = new SwingLink("Macrovector (www.freepik.com)",
                "https://www.freepik.com/author/macrovector");
        SwingLink swingLinkAthorAttributionLink = new SwingLink("Michael Diamond (Github)",
                "https://github.com/dimo414");

        gratitudeForTheIdeaLink.setFont(Gui.PLAIN_TEXT_FONT);
        goatAwardLink.setFont(Gui.PLAIN_TEXT_FONT);
        carAwardLink.setFont(Gui.PLAIN_TEXT_FONT);
        goatCardLink.setFont(Gui.PLAIN_TEXT_FONT);
        carCardLink.setFont(Gui.PLAIN_TEXT_FONT);
        anonymousHackerLink.setFont(Gui.PLAIN_TEXT_FONT);
        swingLinkAthorAttributionLink.setFont(Gui.PLAIN_TEXT_FONT);

        citedPagePanel.add(gratitudeForTheIdea);
        citedPagePanel.add(gratitudeForTheIdeaLink,"wrap");
        citedPagePanel.add(goatAwardExplanation);
        citedPagePanel.add(goatAwardLink,"wrap");
        citedPagePanel.add(carAwardExplanation);
        citedPagePanel.add(carAwardLink,"wrap");
        citedPagePanel.add(goatCardExplanation);
        citedPagePanel.add(goatCardLink,"wrap");
        citedPagePanel.add(carCardExplanation);
        citedPagePanel.add(carCardLink,"wrap");
        citedPagePanel.add(anonymousHacker);
        citedPagePanel.add(anonymousHackerLink,"wrap");
        citedPagePanel.add(swingLinkClassExplanation);
        citedPagePanel.add(swingLinkAthorAttributionLink,"wrap");

        return mainPanel;
    }
}
