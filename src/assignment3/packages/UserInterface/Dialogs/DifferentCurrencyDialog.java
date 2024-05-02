package assignment3.packages.UserInterface.Dialogs;

import javax.swing.*;
import java.awt.*;

public class DifferentCurrencyDialog extends JDialog {
    private JLabel Error;
    private JButton close;

    public DifferentCurrencyDialog(Frame owner) {
        super(owner, "Different Currency Error", true);
        setLayout(new BorderLayout(10, 10));
        Error = new JLabel("    Cannot sum different currencies    ");
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
