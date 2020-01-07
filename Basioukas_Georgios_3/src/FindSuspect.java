import javax.swing.*;
import java.awt.event.*;

public class FindSuspect extends JFrame{

    private Registry registry;
    private JTextField suspectTextField;

    public FindSuspect(Registry r) {
        registry = r;

        JPanel panel = new JPanel();
        suspectTextField = new JTextField("Please enter suspect's name",20);
        JButton findButton = new JButton("Find");

        panel.add(suspectTextField);
        panel.add(findButton);

        ButtonListener listener = new ButtonListener();
        findButton.addActionListener(listener);

        this.setContentPane(panel);
        this.setTitle("Find Suspect");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 100);

    }

    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(registry.suspectNameList.contains(parseSuspectTextField())){
                //dispatch next frame
                new SuspectPage(registry.cpSuspectList.get(parseSuspectTextField()), registry);
                dispose();
            }
            else {
                //throw error
                JOptionPane.showMessageDialog(null, "Suspect " + parseSuspectTextField() + " not found");
            }
        }
    }

    //get the user input text from the text field
    public String parseSuspectTextField(){
        return suspectTextField.getText();
    }
}
