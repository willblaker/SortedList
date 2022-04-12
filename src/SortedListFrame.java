import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class SortedListFrame extends JFrame {
    JPanel mainPnl, inpPnl, outPnl;
    JButton inputBtn, outputBtn;
    JTextField inputField, outField;
    JLabel inputLbl, outLbl;
    JTextArea outputTxt;
    private static ArrayList<String> list = new ArrayList<>();

    public SortedListFrame(){
        mainPnl = new JPanel();
        mainPnl.setLayout(new GridLayout(2,1));

        createInPnl();
        mainPnl.add(inpPnl);

        createOutPnl();
        mainPnl.add(outPnl);

        add(mainPnl);
        setSize(500,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void createInPnl(){
        inpPnl = new JPanel();
        inpPnl.setBorder(new TitledBorder(new EtchedBorder(), "Input"));
        inpPnl.setLayout(new BorderLayout());

        inputLbl = new JLabel("Input string in comma separated list.", SwingConstants.CENTER);
        inputField = new JTextField(280);
        inputBtn = new JButton("Add to list");
        inputBtn.addActionListener((ActionEvent ae) ->{
            addStrings();
            clearInputField();
        });

        inpPnl.add(inputLbl, BorderLayout.NORTH);
        inpPnl.add(inputField, BorderLayout.CENTER);
        inpPnl.add(inputBtn, BorderLayout.SOUTH);
    }

    public void createOutPnl(){
        outPnl = new JPanel();
        outPnl.setBorder(new TitledBorder(new EtchedBorder(), "Output (Input string to search)"));
        outPnl.setLayout(new BorderLayout());

        outLbl = new JLabel("Input string to search.", SwingConstants.CENTER);
        outField = new JTextField(35);
        outputBtn = new JButton("Search");
        outputBtn.addActionListener((ActionEvent ae) ->{
            clearOutputTxt();
            searchStrings();
            outField.setText("");
        });

        outputTxt = new JTextArea("",20,130);

        outPnl.add(outLbl, BorderLayout.NORTH);
        outPnl.add(outField, BorderLayout.NORTH);
        outPnl.add(outputTxt, BorderLayout.CENTER);
        outPnl.add(outputBtn, BorderLayout.SOUTH);
    }

    public void addStrings(){
        String str = inputField.getText();
        String[] elements = str.split(",");
        for(int j = 0; j < elements.length; j++){
            list.add(elements[j]);
        }
        for(int i = 0; i < list.size() - 1; i++){
            int low = i;
            for(int k = i+1; k <list.size(); k++){
                if(list.get(k).compareTo(list.get(low)) < 0)
                    low = k;
                else;
            }

            String temp = list.get(i);
            list.set(i, list.get(low));
            list.set(low, temp);
        }
        clearOutputTxt();
        for(int a = 0; a < list.size(); a++){
            outputTxt.append(list.get(a) + "\n");
        }
    }

    public void searchStrings(){
        if(list.contains(outField.getText()))
            outputTxt.append(outField.getText() + " found at index of " + list.indexOf(outField.getText()));
        else{
            list.add(outField.getText());
            for(int i = 0; i < list.size() - 1; i++){
                int low = i;
                for(int k = i+1; k <list.size(); k++){
                    if(list.get(k).compareTo(list.get(low)) < 0)
                        low = k;
                    else;
                }
                String temp = list.get(i);
                list.set(i, list.get(low));
                list.set(low, temp);
            }

            outputTxt.append(outField.getText() + " would be located at an index of " + list.indexOf(outField.getText()));
            outputTxt.remove(list.indexOf(outField.getText()));
        }
    }

    public void clearOutputTxt(){
        outputTxt.selectAll();
        outputTxt.replaceSelection("");
    }

    public void clearInputField(){
        inputField.setText("");
    }
}
