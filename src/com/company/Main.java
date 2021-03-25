package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("Welcome to hangman");
        int lives = 6;
        ArrayList<String> pickedLetters = new ArrayList<>();
        //open file
        File dictionary = new File("src/com/company/dictionary");
        Scanner scanner = new Scanner(dictionary);
        Scanner input = new Scanner(System.in);
        ArrayList<String> words = new ArrayList<>();

        // add words from dictionary to an arraylist
        while(scanner.hasNextLine()){
            words.add(scanner.nextLine());
        }
//        for(String s : words){
//            System.out.println(s);
//        }

        //get random index from a words array
        int ran = (int)(Math.random() * words.size());
        String hiddenWord = words.get(ran);
        char[] wordArray = hiddenWord.toCharArray();

        char[] myAnswer = new char[wordArray.length];
        for(int i = 0; i < wordArray.length; i++){
            myAnswer[i] = '?';
        }

        boolean finished = false;
        while(!finished){
            System.out.println("**************************************");
            System.out.println("Pick a letter: ");
            String letter = input.next();
            //check for valid input
            while(letter.length() != 1 || Character.isDigit(letter.charAt(0))){
                System.out.println("Error input - Try again");
                letter = input.next();
            }
            pickedLetters.add(letter);
            //check if letter is in a word
            boolean found = false;
            for (int i = 0; i < wordArray.length; i++){
                if(letter.charAt(0) == wordArray[i]){
                    myAnswer[i] = wordArray[i];
                    found = true;
                }
            }
            if(!found){
                lives--;
                System.out.println("Wrong letter");
            }
            boolean done = true;
            for (char c : myAnswer) {
                if (c == '?') {
                    System.out.print(" _ ");
                    done = false;
                } else {
                    System.out.print(c);
                }
            }
            System.out.println("\n" + "Lives left: " + lives);
            System.out.println("Already picked letters: " + pickedLetters.toString());
            drawHangman(lives);

            //check if the game ends
            if(done){
                System.out.println("Congrats You Found The Word");
                finished = true;
            }
            if(lives <= 0){
                System.out.println("You are dead! Study your english!");
                System.out.println("The word was: "+ hiddenWord);
                finished = true;
            }
        }
    }
    public static void drawHangman(int livesLeft){
        switch (livesLeft) {
            case 6 -> {
                System.out.println("|----------");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
            }
            case 5 -> {
                System.out.println("|----------");
                System.out.println("|    O");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
            }
            case 4 -> {
                System.out.println("|----------");
                System.out.println("|    O");
                System.out.println("|    |");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
            }
            case 3 -> {
                System.out.println("|----------");
                System.out.println("|    O");
                System.out.println("|   -|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
            }
            case 2 -> {
                System.out.println("|----------");
                System.out.println("|    O");
                System.out.println("|   -|-");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
            }
            case 1 -> {
                System.out.println("|----------");
                System.out.println("|    O");
                System.out.println("|   -|-");
                System.out.println("|   /");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
            }
            case 0 -> {
                System.out.println("|----------");
                System.out.println("|    O");
                System.out.println("|   -|-");
                System.out.println("|   /|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
            }
        }
    }
}
