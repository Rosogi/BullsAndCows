import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class App extends JFrame {
    private int counter = 0;
    private String test = "";
    private int[] compNumber;
    private int bullsCount = 0, cowsCount = 0;

    public static void main(String[] args) {
        new App();
    }

    public App() {
        setTitle("Bull's and Cow's");
        setBounds(100, 100, 300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        getNumber();

        final JPanel inputPanel = new JPanel();
        final JTextArea playerGuess = new JTextArea();
        final JLabel inputLabel = new JLabel("Input: ");
        final JButton submitButton = new JButton("Submit!");

        final JPanel textPanel = new JPanel();
        final JLabel textLabel = new JLabel("");
        textPanel.add(textLabel);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter++;
                checkGuess(playerGuess.getText());
                //test = test + String.valueOf(playerGuess.getText() + System.lineSeparator());
                //textLabel.setText(counter + " " + test + checkGuess(playerGuess.getText()));
                textLabel.setText(compNumber[0] + "" + compNumber[1] + "" + compNumber[2] + "" + compNumber[3] + " - N, " + playerGuess.getText() + " - Y, " + cowsCount + " - Cows, " + bullsCount + " - Bulls");
                bullsCount = 0;
                cowsCount = 0;


            }
        });


        inputPanel.add(inputLabel);
        inputPanel.add(playerGuess);
        inputPanel.add(submitButton);

        add(inputPanel, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);


        setVisible(true);
    }


    private void checkGuess(String playerGuess) {

        char[] stringArray = playerGuess.toCharArray();
        int[] intArray = new int[stringArray.length];

        System.out.println("Загадано = " );
        for (int i = 0; i < compNumber.length; i++){
            System.out.print(compNumber[i]);
        }

        for (int i = 0; i < stringArray.length; i++) {
            intArray[i] = Character.getNumericValue(stringArray[i]);
        }
        System.out.println("Вы ввели = " );
        for (int i = 0; i < intArray.length; i++){
            System.out.print(intArray[i]);
        }
        for (int i = 0; i < compNumber.length; i++) {
            if (intArray[i] == compNumber[i]) {
                bullsCount++;
            } else {
                if (containsNumber(Arrays.stream(compNumber).toArray(), intArray[i])){
                    cowsCount++;
                }
            }


        }



    }

    private ArrayList<Integer> generateNumbers(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=1; i<5; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        for (int i=0; i<3; i++) { //4 - размер выходного масива
            System.out.println(list.get(i));
        }

        return list;
    }

    //Перенести функционал getNumber в generateNumbers
    private void getNumber() {
        compNumber = new int[4];
        for (int i = 0; i < compNumber.length; i++) {
            compNumber[i] = generateNumbers().get(i);
        }
    }

    public static boolean containsNumber(int [] arr, int targetValue) {
        for (int s: arr) {
            if (s == targetValue) {
                return true;
            }
        }
        return false;
    }
}
