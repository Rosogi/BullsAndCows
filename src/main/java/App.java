import javax.swing.*;
import javax.swing.text.AbstractDocument;
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
        setBounds(100, 100, 220, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        getNumber();

        final JPanel inputPanel = new JPanel();
        final JTextArea playerGuess = new JTextArea();
        final JLabel inputLabel = new JLabel("Input: ");
        final JButton submitButton = new JButton("Submit!");

        final JPanel textPanel = new JPanel();
        final JLabel textLabel = new JLabel("<html> </html>");
        textPanel.add(textLabel);

        submitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (playerGuess.getText().length() > 4){
                    textLabel.setText("<html>Вы ввели больше 4-х сиволов!</html>");
                } else {
                    counter++;
                    checkGuess(playerGuess.getText());
                    if(bullsCount==4){
                        textLabel.setText("Вы победили!");
                    } else {
                        String currentText = textLabel.getText();
                        String newText = currentText.substring(0, currentText.length()-7);
                        textLabel.setText(newText + "<br> " + getTryString() + "</html>");

                        bullsCount = 0;
                        cowsCount = 0;
                    }
                }
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

        for (int i = 0; i < stringArray.length; i++) {
            intArray[i] = Character.getNumericValue(stringArray[i]);
        }

        for (int i = 0; i < compNumber.length; i++) {
            System.out.println(compNumber[i]);
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
        for (int i=1; i<9; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        return list;
    }

    //Перенести функционал getNumber в generateNumbers
    private void getNumber() {
        compNumber = new int[4];
        for (int i = 0; i < compNumber.length; i++) {
            compNumber[i] = generateNumbers().get(i);
        }
    }

    private boolean containsNumber(int [] arr, int targetValue) {
        for (int s: arr) {
            if (s == targetValue) {
                return true;
            }
        }
        return false;
    }

    private String getTryString(){
        return counter + ": Коров- " + cowsCount +", Быков- " + bullsCount;
    }
}