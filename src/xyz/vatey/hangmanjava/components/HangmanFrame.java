package xyz.vatey.hangmanjava.components;

import javax.swing.*;

import xyz.vatey.hangmanjava.managers.GuessButtonListener;
import xyz.vatey.hangmanjava.managers.HangmanGame;

import java.awt.*;
import java.util.ArrayList;

/**
 * HangmanFrame is a JFrame for the main hangman game window.
 * A HangmanFrame is generated by a HangmanGame.
 * @see xyz.brandon.hangmanjava.managers.HangmanGame;
 */
public class HangmanFrame extends JFrame {

    private StickfigureComponent stickFigure;
    private GameFieldsPanel gameFields;
    private Graphics2D g2d;

    /**
     * Generates the hangman game window, split into a grid with stickfigurecomponent on the left
     * and GameFieldsPanel on the right.
     * @param wordSize              Length of the word to guess, passed to gamefieldspanel
     * @param guessButtonListener   Guess button action listener class, generated in HangmanGame
     * @see   HangmanGame;
     * @see   StickfigureComponent
     * @see   GameFieldsPanel
     */
    public HangmanFrame(int wordSize, GuessButtonListener guessButtonListener) {

        setSize(450,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Hangman");

        setLayout(new GridLayout(1,2));

        //Create stick figure frame, left side of window
        stickFigure = new StickfigureComponent();
        add(stickFigure);

        //Create input fields, right side of window
        gameFields = new GameFieldsPanel(wordSize, guessButtonListener);
        add(gameFields);

        gameFields.updateLettersGuessed(stickFigure.getRemainingGuesses());
        setVisible(true);

    }

    /**
     * Makes a call to the drawNextPart() function within the stickFigure component
     * @return  True represents a new part being present, False means the last part has been drawn (player lost)
     * @see     StickfigureComponent
     */
    public boolean drawNextPart() {
        gameFields.updateLettersGuessed(stickFigure.getRemainingGuesses());
        return stickFigure.drawNextPart(stickFigure.getGraphics());
    }

    /**
     * Uses the incorrectGuesses arraylist from HangmanGame to generate a incorrect letters string
     * passed to the gameFieldsPanel for lettersGuessed field.
     * @param incorrectCharacters   ArrayList of incorrect characters
     * @see   GameFieldsPanel
     * @see   HangmanGame
     */
    public void updateIncorrectLetters(ArrayList<Character> incorrectCharacters) {
        String strBldr = "";
        for (Character incorrectChar : incorrectCharacters) {
            strBldr += incorrectChar + ", ";
        }
        if (incorrectCharacters.size() > 0) {
            strBldr = strBldr.substring(0, strBldr.length()-2);
        }
        gameFields.updateLettersGuessed(strBldr);
    }

    /**
     * Passes message to be displayed in input response field of gameFieldsPanel
     * @param msg   message to display
     * @see   GameFieldsPanel
     */
    public void sendAlert(String msg) {
        gameFields.updateInputResponseMessage(msg);
    }

    /**
     * Used to clear the input field in gamefieldpanel
     * @see   GameFieldsPanel
     */
    public void refreshGameFields() {
        gameFields.clearInputField();
    }

    /**
     * Takes the word progress string generated in HangmanGame and passes it
     * to the gameFields progress field.
     * @param wordProgress  word progress string to be displayed
     * @see   GameFieldsPanel
     */
    public void updateWordProgress(String wordProgress) {
        gameFields.updateProgress(wordProgress);
    }

    /**
     * Updates the gamefields remaining guess count JLabel
     * @param guessCount    Number of guesses remaining
     * @see   GameFieldsPanel
     */
    public void updateRemainingGuesses(int guessCount) {
        gameFields.updateLettersGuessed(guessCount);
    }

    /**
     * Grabs the input field text from gameFields
     * @return  returns text typed into guess input field
     * @see     GameFieldsPanel
     */
    public String getGuessText() {
        return gameFields.getInputField().toLowerCase().trim();
    }

}
