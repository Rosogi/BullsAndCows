import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame {
    private int counter = 0;
    private String test = "";
    private int[] compNumber;

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
                test = test + String.valueOf(playerGuess.getText() + System.lineSeparator());
                textLabel.setText(counter + " " + test + checkGuess(playerGuess.getText()));

            }
        });


        inputPanel.add(inputLabel);
        inputPanel.add(playerGuess);
        inputPanel.add(submitButton);

        add(inputPanel, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);


        setVisible(true);
    }

    private void getNumber() {
        compNumber = new int[4];
        for (int i = 0; i < compNumber.length; i++) {
            compNumber[i] = (int) (Math.random() * 9);
        }
    }


    private String checkGuess(String playerGuess) {
        String result = "";

        char[] stringArray = playerGuess.toCharArray();
        int[] intArray = new int[3];
        for (int i = 0; i < stringArray.length; i++) {
            intArray[i] = (int) stringArray[i];
        }

        for (int i = 0; i < compNumber.length; i++) {
            if (intArray[i] == compNumber[i]) {
            }


        }

    }
}
