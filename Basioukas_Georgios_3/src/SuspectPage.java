import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class SuspectPage extends JFrame {

    private Registry registry;
    private Suspect suspect;

    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    //all needed widgets for the frame
    private JTextArea nameArea;
    private JTextArea codeNameArea;
    private JTextArea phoneNumbers;

    private JTextField inputNumber;
    private JTextArea smsArea;
    private JButton smsButton;

    private  JTextArea partnersText;
    private JTextArea partnersArea;

    private JTextArea suggestedPartnersText;
    private JTextArea suggestedPartnersArea;

    private JTextArea countrymenText;
    private JTextArea countrymenArea;

    private JButton returnButton;


    public SuspectPage(Suspect s, Registry r) {
        registry = r;
        suspect = s;
        createGUI();
    }

    private void createGUI(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Suspect Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentsToPane(frame.getContentPane());
        frame.setSize(700,800);
        frame.setVisible(true);
    }

    public void addComponentsToPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        nameArea = new JTextArea("Name: " + suspect.getName() + "   |  ");
        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        c.ipady = 40; //height
        c.gridx = 0;  //location parameter in row orientation
        c.gridy = 0;  //location parameter in column orientation
        pane.add(nameArea, c);

        codeNameArea = new JTextArea(" Code Name: " + registry.suspectCodeNameList.get(findIndexOfSuspect()) + "   |  ");
        c.ipady = 40;
        c.gridx = 1;
        c.gridy = 0;
        pane.add(codeNameArea, c);

        phoneNumbers = new JTextArea(" Phone Numbers: \n");
        for(String a : registry.getAllNumbersOfSpecificSuspect(findIndexOfSuspect())){
            phoneNumbers.append(a + "\n");
        }
        c.gridx = 2;
        c.gridy = 0;
        pane.add(phoneNumbers, c);

        inputNumber = new JTextField("Input phone number");
        c.ipady = 40;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        pane.add(inputNumber, c);

        smsArea = new JTextArea("CRITICAL SMS: \n");
        c.ipady = 40;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;
        pane.add(smsArea, c);

        smsButton = new JButton("Find SMS");
        c.ipady = 40;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 1;
        pane.add(smsButton, c);

        partnersText = new JTextArea("Partners: ");
        c.ipady = 40;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        pane.add(partnersText, c);

        partnersArea = new JTextArea("POTENTIAL PARTNERS: \n");
        c.ipady = 40;
        c.weightx = 0.0;
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 2;
        pane.add(partnersArea, c);
        //iterate through partners and put them in the widget
        for(int i=0; i<suspect.potentialPartners.size();i++){
            Suspect s = suspect.potentialPartners.get(i);
            partnersArea.append(s.name + "\n");
        }

        suggestedPartnersText = new JTextArea("Suggested Partners: ");
        c.ipady = 40;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 3;
        pane.add(suggestedPartnersText, c);

        suggestedPartnersArea = new JTextArea("BASED ON TRIADIC CLOSURE: \n");
        c.ipady = 40;
        c.weightx = 0.0;
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 3;
        pane.add(suggestedPartnersArea, c);
        //iterate through suggested partners and put them in the widget
        for(int i=0; i<suspect.getSuggestedPartners().size();i++){
            Suspect s = suspect.getSuggestedPartners().get(i);
            suggestedPartnersArea.append(s.name + "\n");
        }

        countrymenText = new JTextArea("Suspects from same country: ");
        c.ipady = 40;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 4;
        pane.add(countrymenText, c);

        countrymenArea = new JTextArea("Suspects also coming from " + suspect.originCountry + "\n");
        c.ipady = 40;
        c.weightx = 0.0;
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 4;
        pane.add(countrymenArea, c);
        //iterate through same country partners and put them in the widget
        ArrayList<Suspect> temp = suspect.sameCountryPartners();
        for (Suspect s : temp) {
            countrymenArea.append(s.name + "\n");
        }


        returnButton = new JButton("Return to Search Screen");
        c.ipady = 0;
        c.weighty = 1.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.insets = new Insets(10,0,0,0);  //top padding
        c.gridx = 0;
        c.gridwidth = 3;
        c.gridy = 5;
        pane.add(returnButton, c);

        SuspectPage.ButtonListener listener = new SuspectPage.ButtonListener();
        smsButton.addActionListener(listener);
        returnButton.addActionListener(listener);
    }

    class ButtonListener implements ActionListener {
        ArrayList<String> smsList;
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(smsButton)){
                for(int i = 0; i < registry.suspectNumbers.get(findIndexOfSuspect()).size(); i++){   //the size of the phone numbers to that specific suspect
                    smsList = registry.getMessagesBetween(registry.suspectNumbers.get(findIndexOfSuspect()).get(i), inputNumber.getText()); //number1 is the phone numbers of that specific suspect and number2 the user input number
                    if(smsList.isEmpty()){
                        smsArea.append("No threatening sms found with number " + registry.suspectNumbers.get(findIndexOfSuspect()).get(i) + "\n");
                    }
                    else{
                        smsArea.append(smsList + "\n");
                    }
                }
            }
            else if(e.getSource().equals(returnButton)){
               new FindSuspect(registry);
            }
        }
    }

    //I need to get the index of the Suspect in order to find the the correct values to the specific suspect
    private int findIndexOfSuspect(){
        return registry.suspectNameList.indexOf(suspect.name);
    }

}
