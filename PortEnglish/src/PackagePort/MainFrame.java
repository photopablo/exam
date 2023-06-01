package PackagePort;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame{
    private JTextField tfIDNumber;

    private JComboBox comboBoxCountry;
    private JTextField tfWeight;

    private JRadioButton btnPriority1;
    private JRadioButton btnPriority2;
    private JRadioButton btnPriority3;
    private JTextArea descriptionIsShowHereTextArea;
    private JTextArea stateInHubPlanTextArea;
    private JTextField tfRemittentCompany;
    private JTextField tfReceiverCompany;
    private JButton pileButton;

    private JButton unpileButton;

    private JButton showContainerDescriptionButton;

    private JButton numberOfContainersButton;

    private JCheckBox checkBoxCustomInspection;
    private JTextArea containerDescriptionTextArea;
    private JComboBox containerFromACountry;
    private JTextField tfCountryContainers;
    private JPanel mainPanel;
    private JTextField containerIDTextField;
    private JTextField containerToRemoveTextField;
    private JRadioButton firstHubRadioButton;
    private JRadioButton secondHubRadioButton;
    private JRadioButton thirdHubRadioButton;
    private JButton examButton;
    static int selectedHub;


    public MainFrame() {

        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Port Management");
        setSize(1500,550);
        setVisible(true);


        Hub firstHub = new Hub();
        Hub secondHub = new Hub();
        Hub thirdHub = new Hub();

        Port port = new Port(firstHub, secondHub, thirdHub);
        firstHubRadioButton.setSelected(true);

        // We add a few country to the country selection combo box
        comboBoxCountry.addItem("Select a country");
        comboBoxCountry.addItem("Spain");
        comboBoxCountry.addItem("Germany");
        comboBoxCountry.addItem("Russia");
        comboBoxCountry.addItem("USA");


        containerFromACountry.addItem("Spain");
        containerFromACountry.addItem("Germany");
        containerFromACountry.addItem("Russia");
        containerFromACountry.addItem("USA");



        // Button to Add a new Container, management of exception done
        pileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int priority = 1;
                if (btnPriority1.isSelected()) {
                    priority = 1;
                } else if (btnPriority2.isSelected()) {
                    priority = 2;
                } else if (btnPriority3.isSelected()) {
                    priority = 3;
                }

                if (firstHubRadioButton.isSelected()) {
                    selectedHub = 0;
                } else if (secondHubRadioButton.isSelected()) {
                    selectedHub = 1;
                } else if (thirdHubRadioButton.isSelected()) {
                    selectedHub = 2;
                }


                Container Current = new Container(
                        Integer.valueOf(tfIDNumber.getText()),
                        Integer.valueOf(tfWeight.getText()),
                        (String) comboBoxCountry.getSelectedItem(),
                        checkBoxCustomInspection.isSelected(),
                        priority,
                        descriptionIsShowHereTextArea.getText(),
                        tfRemittentCompany.getText(),
                        tfReceiverCompany.getText()
                );

                port.checkAvailable(Current);
                stateInHubPlanTextArea.setText(port.hubs[selectedHub].hubDisplay());

                JOptionPane.showMessageDialog(mainPanel, "Container added to stack.");
            }
        });

        examButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numberOfContainers = 0;
                if (btnPriority1.isSelected()) {
                    for(int c = 0; c < 3; c++) {
                        numberOfContainers = numberOfContainers + port.hubs[c].count(1);
                    }
                } else if (btnPriority2.isSelected()) {
                    for(int c = 0; c < 3; c++) {
                        numberOfContainers = numberOfContainers + port.hubs[c].count(2);
                    }
                } else if (btnPriority3.isSelected()) {
                    for(int c = 0; c < 3; c++) {
                        numberOfContainers = numberOfContainers + port.hubs[c].count(3);
                    }
                }

                JOptionPane.showMessageDialog(mainPanel, Integer.toString(numberOfContainers));
            }
        });
        // Button to remove a container from the hub, using the input row and column
        unpileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    if(firstHubRadioButton.isSelected()){
                        selectedHub = 0;
                    } else if (secondHubRadioButton.isSelected()) {
                        selectedHub = 1;
                    }else if (thirdHubRadioButton.isSelected()){
                        selectedHub = 2;
                    }

                    boolean removed = port.hubs[selectedHub].removeContainer(Integer.parseInt(containerToRemoveTextField.getText()));
                    stateInHubPlanTextArea.setText(port.hubs[selectedHub].hubDisplay());
                    if (removed == true) {
                        JOptionPane.showMessageDialog(mainPanel, "Container removed.");
                    } else {
                        JOptionPane.showMessageDialog(mainPanel, "There is no container in this column.");
                    }
            }
        });


        showContainerDescriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Checking which hub is selected
                    if(firstHubRadioButton.isSelected()){
                        selectedHub = 0;
                    } else if (secondHubRadioButton.isSelected()) {
                        selectedHub = 1;
                    }else if (thirdHubRadioButton.isSelected()){
                        selectedHub = 2;
                    }

                    String showDescription;
                    showDescription = port.hubs[selectedHub].findContainerById(Integer.parseInt(containerIDTextField.getText()), 1);
                    containerDescriptionTextArea.setText(showDescription);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(mainPanel, "Please enter valid numbers for the ID.");
                }
            }
        });


        numberOfContainersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numberOfContainers = 0;
                for(int c = 0; c < 3; c++) {
                    numberOfContainers = numberOfContainers + port.hubs[c].countContainersByCountry((String) containerFromACountry.getSelectedItem());
                }
                tfCountryContainers.setText(String.valueOf(numberOfContainers));
            }
        });




        firstHubRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(firstHubRadioButton.isSelected()){
                    stateInHubPlanTextArea.setText(port.hubs[0].hubDisplay());
                }
            }
        });
        secondHubRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(secondHubRadioButton.isSelected()){
                    stateInHubPlanTextArea.setText(port.hubs[1].hubDisplay());
                }
            }
        });
        thirdHubRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(thirdHubRadioButton.isSelected()){
                    stateInHubPlanTextArea.setText(port.hubs[2].hubDisplay());
                }
            }
        });
    }



    private void clearContainerFields() {
        tfIDNumber.setText("");
        comboBoxCountry.setSelectedIndex(0);
        tfWeight.setText("");
        btnPriority1.setSelected(false);
        btnPriority2.setSelected(false);
        btnPriority3.setSelected(false);
        tfRemittentCompany.setText("");
        tfReceiverCompany.setText("");
        checkBoxCustomInspection.setSelected(false);
        descriptionIsShowHereTextArea.setText("");
    }

}