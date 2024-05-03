package assignment3.packages.UserInterface.Dialogs;

import javax.swing.*;
import java.awt.*;

public class InvalidAmountDialog extends JDialog {
    private JLabel Error;
    private JButton close;

    public InvalidAmountDialog(Frame owner) {
        super(owner, "Invalid amount", true);
        setLayout(new BorderLayout(10, 10));
        Error = new JLabel("     Amount must be greater than zero.     ");
        Error.setHorizontalAlignment(SwingConstants.CENTER);
        add(Error, BorderLayout.CENTER);

        //Close button

        close = new JButton("Ok");
        close.addActionListener(e -> this.setVisible(false));
        add(close, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(owner);
    }
}
