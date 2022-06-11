import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class MainForm {
    private JPanel MainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton collapseButton;
    private JTextField textField4;

    private String surname;
    private String name;
    private String middleName;
    public JPanel getMainPanel(){
        return MainPanel;
    }

    private void collapseActionPerformed(){
        surname = textField1.getText();
        name = textField2.getText();
        middleName = textField3.getText();
        if ((surname.isEmpty())||(name.isEmpty())||(middleName.isEmpty())){
            JOptionPane.showMessageDialog(
                    MainPanel,
                    "fill text fields!!!",
                    "Error message!",
                    JOptionPane.PLAIN_MESSAGE
            );
            return;
        }
        textField1.setVisible(false);
        textField2.setVisible(false);
        textField3.setVisible(false);
        textField4.setText(surname.trim() + " " + name.trim() + " " + middleName.trim());
        textField4.setVisible(true);
        MainPanel.revalidate();
        MainPanel.repaint();
        collapseButton.setText("Expand");
    }

    private void expandActionPerformed(){
        String[] fullName = textField4.getText().trim().split(" ");
        if (fullName.length != 3){
            JOptionPane.showMessageDialog(
                    MainPanel,
                    "Indicate surname name and middle name",
                    "Error message!",
                    JOptionPane.PLAIN_MESSAGE
            );
            return;
        }
        surname = fullName[0];
        name = fullName[1];
        middleName = fullName[2];
        textField1.setText(surname);
        textField2.setText(name);
        textField3.setText(middleName);

        textField1.setVisible(true);
        textField2.setVisible(true);
        textField3.setVisible(true);
        textField4.setVisible(false);
        MainPanel.revalidate();
        MainPanel.repaint();
        collapseButton.setText("Collapse");
    }
    public MainForm(){
        collapseButton.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (collapseButton.getText().equals("Collapse")){
                    collapseActionPerformed();
                }
                else if (collapseButton.getText().equals("Expand")){
                    expandActionPerformed();
                }
            }
        });
    }
}
