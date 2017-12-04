/**
 * Created by per-joelsompio on 11/11/16.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JTextField;

public class MyUnitTesterFrame extends JFrame {
    private static final int FRAME_WIDTH = 450;
    private static final int FRAME_HEIGHT = 450;

    UnitTester unitTester;

    private JFrame frame;
    private JLabel label;
    private JTextField textField;
    private JTextArea textArea;
    private JPanel panel;
    private JPanel lowerPanel;
    private ActionListener listener;

    private JButton b1;
    private JButton b2;

    private String testName;
    private ArrayList<String> testResults;

    public MyUnitTesterFrame(String title) {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        //add panels
        panel = buildPanel();
        lowerPanel = buildLowerPanel();
        textField = buildTextField();
        label = buildLabel();
        textArea = buildTextArea();

        //add buttons
        lowerPanel.add(textField, BorderLayout.NORTH);
        lowerPanel.add(textArea, BorderLayout.SOUTH);

        //add components to panel then to frame
        frame.add(panel, BorderLayout.NORTH);
        frame.add(lowerPanel, BorderLayout.CENTER);

    }

    //method to create labels
    private JLabel buildLabel() {
        label = new JLabel("");

        return label;
    }

    //method to create a center panel which contains the buttons
    private JPanel buildPanel() {
        panel = new JPanel();
        panel.setVisible(true);
        panel.setBackground(Color.CYAN);

        //add buttons to panel
        b1 = new JButton("Run Test");
        b1.addActionListener(new ButtonListener());

        b2 = new JButton("Clear");
        b2.addActionListener(new ButtonListener());

        panel.add(b1);
        panel.add(b2);

        return panel;
    }

    //method to create another panel
    private JPanel buildLowerPanel() {
        lowerPanel = new JPanel();
        lowerPanel.setVisible(true);
        lowerPanel.setBackground(Color.BLUE);

        return lowerPanel;
    }

    //method to create a textfield
    private JTextField buildTextField() {
        final int FIELD_WIDTH = 30;
        textField = new JTextField(FIELD_WIDTH);
        textField.setEditable(true);

        return textField;
    }

    //method to create a textarea
    private JTextArea buildTextArea() {
        textArea = new JTextArea(20, 30);
        textArea.setVisible(true);
        textArea.setEditable(true);

        return textArea;
    }

    /*
     * Inner class which holds the buttonlistener
     * the actionperformed also instantiates the class UnitTester
     * in order to sweep through the testresults from the UnitTester class
     */
    private class ButtonListener implements ActionListener {

        public ButtonListener() {

        }

        public void actionPerformed(ActionEvent event) {
            if(event.getSource().equals(b1)) {

                testName =textField.getText();
                try {
                    UnitTester test = new UnitTester(testName);
                    test.runTest();
                    testResults = test.getArrayList();
                    for(int i = 0; i < testResults.size(); i++) {
                        textArea.append(testResults.get(i) + "\n");
                    }
                }
                catch(Exception e) {
                    System.out.println("Failed to instantiate UnitTester");
                }
            }
            else if(event.getSource() == b2) {
                textField.setText("");
                textArea.setText("");
            }
        }
    }

}
