import java.util.ArrayList;
import java.util.Scanner;

public class Hangman {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Hangman, wanna play? Press Y to confirm.");
        var a = scanner.next();
        int j = 0;

        while (a.equalsIgnoreCase("y")) {

            a = run(j, a);
            if (!a.equalsIgnoreCase("y")) {
                System.out.println("Good bye!");
            }
        }
    }

    private static String run(int j, String a) {

        //Setting rule of 5 tries and picking word from Array.
        var numOfTries = 5;
        while (a.equalsIgnoreCase("y")) {
            String[] words = {"rabarbar", "piknik", "tradycje"};

            System.out.println("New game:");
            String word = words[j];
            final Scanner scanner = new Scanner(System.in);

            //Creating new ArrayLists. First to collect letter after each attempt.
            ArrayList usedLetters = new ArrayList<String>();
            ArrayList ourWord = new ArrayList<String>();
            ArrayList wordInArray = new ArrayList<String>();
            var allowedSigns = "qwertyuiopasdfghjklzxcvbnm";

            //Second to show progress of the game.
            for (int i = 0; i < word.length(); i++) {
                ourWord.add("_");
            }
            //Third to cast every char from word (String) on ArrayList.
            for (int i = 0; i < word.length(); i++) {
                wordInArray.add(i, word.charAt(i));
            }
            //Printing ourWord which is at beginning "empty", just to show how many letters it contains.
            System.out.println(ourWord);


            //Checking any remaining numOfTries and that word has been already guessed (negation).
            while ((numOfTries > 0) && !(ourWord.equals(wordInArray))) {
                System.out.println("Give letter");
                String letter = scanner.next().toLowerCase();

                //Rejecting invalid signs, same letter and more than one letter.
                while ((!(allowedSigns.contains(letter))
                        || (letter.length() != 1)
                        || (usedLetters.contains(letter)))) {
                    if (usedLetters.contains(letter)) {
                        System.out.println(letter + " is already used. Give another letter");
                        letter = scanner.next().toLowerCase();
                    } else {
                        System.out.println(letter + " is not allowed. Give another letter");
                        letter = scanner.next().toLowerCase();
                    }
                }

                usedLetters.add(letter);

                if (word.contains(letter)) {
                    System.out.println("Good");

                    //Iteration over letters in word.
                    for (int i = 0; i < word.length(); i++) {

                        //Setting good letters in ourWord.
                        if (letter.equals(String.valueOf(word.charAt(i)))) {
                            ourWord.set(i, word.charAt(i));
                        }
                    }
                    //Decreasing numOdTries for each mistake.
                } else {
                    numOfTries--;
                    System.out.println("Wrong");
                    System.out.println(letter.length());
                }

                //Printing usedLetters and numOfTries after each attempt. Also printing ourWord to show position of
                //good letters.
                System.out.println("Used letters: " + usedLetters + " Number of tries: " + numOfTries);
                System.out.println(ourWord);
            }

            //Ending of app in case of lost or win.
            if (numOfTries == 0) {
                System.out.println("You lost...");
            } else {
                System.out.println("You won!");
            }
            //Asking about next game.
            System.out.println("Wanna play next? Press Y to confirm.");
            a = scanner.next().toLowerCase();
            j++;

        } return a;



    }
}
