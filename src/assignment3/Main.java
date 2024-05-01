package assignment3;

import assignment3.packages.Handler.Category;
import assignment3.packages.Handler.Expense;
import assignment3.packages.Handler.ExpensesManager;
import assignment3.packages.UserInterface.CategoryFilterPanel;
import assignment3.packages.UserInterface.Dialogs.InvalidAmountDialog;
import assignment3.packages.UserInterface.NewExpensesPanel;
import assignment3.packages.UserInterface.Dialogs.SavedExpensesEditDialog;
import assignment3.packages.UserInterface.SavedExpensesPanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        createAndShowGUI();
    }

    private static void createAndShowGUI() {
        JFrame frame;
        frame = new JFrame("Expense Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create the object of expenseManager
        ExpensesManager expensesManager = new ExpensesManager();

        // Create instances of relevant panels
        NewExpensesPanel newExpensesPanel = new NewExpensesPanel();
        SavedExpensesPanel savedExpensesPanel = new SavedExpensesPanel();
        CategoryFilterPanel categoryFilterPanel = new CategoryFilterPanel(expensesManager, savedExpensesPanel);

        // Add panels to the frame and specify their relative position in the frame
        frame.add(newExpensesPanel, BorderLayout.NORTH);
        frame.add(savedExpensesPanel, BorderLayout.CENTER);
        frame.add(categoryFilterPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);




        // Listener for clicking savebutton

        InvalidAmountDialog negativeDialog = new InvalidAmountDialog(frame); //Error message dialog box

        newExpensesPanel.getSaveButton().addActionListener(e ->  {
            double amount = newExpensesPanel.getAmount();
            Category category = newExpensesPanel.getExpenseCategory();
            LocalDate date = newExpensesPanel.getDate();
            if(amount != 0.0) {
                Expense newExpense = new Expense(amount, category, date);
                expensesManager.addExpense(newExpense);
                savedExpensesPanel.updateTable(expensesManager.getAllExpenses());
            } else {
                negativeDialog.setVisible(true);
            }

        });

        SavedExpensesEditDialog editDialog = new SavedExpensesEditDialog(frame);

        // Edit Button ActionListener
        newExpensesPanel.getEditButton().addActionListener(e -> {
            int selectedRow = savedExpensesPanel.getSavedSelectedExpenseIndex();
            if (selectedRow != -1) {
                // get the index
                Expense selectedExpense = expensesManager.getAllExpenses().get(selectedRow);
                boolean saved = editDialog.showDialog(selectedExpense);
                if (saved) {
                    Expense editedExpense = editDialog.getEditedExpense();
                    if(editedExpense.amount() > 0) {
                        expensesManager.replaceExpense(selectedRow, editedExpense);
                        savedExpensesPanel.updateTable(expensesManager.getAllExpenses());
                    } else {
                        negativeDialog.setVisible(true);
                    }

                }
                }
        });


        // Clear Button ActionListener
        newExpensesPanel.getClearButton().addActionListener(e -> {
            expensesManager.clearExpenses();
            savedExpensesPanel.updateTable(expensesManager.getAllExpenses());
        });

        newExpensesPanel.getDeleteButton().addActionListener(e -> {
            int selectedRow = savedExpensesPanel.getSavedSelectedExpenseIndex();
            if(selectedRow != -1) {
                expensesManager.deleteExpense(expensesManager.getAllExpenses().get(selectedRow));
                savedExpensesPanel.updateTable(expensesManager.getAllExpenses());
            }
        });


        // Filter Button ActionListener

        categoryFilterPanel.getFilterButton().addActionListener(e -> categoryFilterPanel.applyFilter());

        // Restore Button ActionListener

        categoryFilterPanel.getRestoreButton().addActionListener(e -> categoryFilterPanel.restoreFilter());

        //Sum Button ActionListener

        categoryFilterPanel.getSumButton().addActionListener(e -> categoryFilterPanel.setSum(categoryFilterPanel.sum()));

    }
}
