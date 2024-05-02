package assignment3.packages.UserInterface;

import assignment3.packages.Handler.Category;
import assignment3.packages.Handler.Currency;
import assignment3.packages.Handler.Expense;
import assignment3.packages.Handler.ExpensesManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CategoryFilterPanel extends JPanel {
    private ExpensesManager expensesManager;
    private SavedExpensesPanel savedExpensesPanel;
    private JComboBox<Category> filterComboBox;
    private JComboBox<Currency> currencyfilterComboBox;
    private JButton filterButton;
    private JButton sumButton;
    private JLabel SumLabel;




    private JButton restoreButton;


    public CategoryFilterPanel(ExpensesManager expensesManager, SavedExpensesPanel savedExpensesPanel) {
        this.expensesManager = expensesManager;
        this.savedExpensesPanel = savedExpensesPanel;

        setLayout(new FlowLayout(FlowLayout.LEFT));

        filterComboBox = new JComboBox<>(Category.values());
        add(filterComboBox);

        currencyfilterComboBox = new JComboBox<>(Currency.values());
        add(currencyfilterComboBox);

        filterButton = new JButton("Filter");
        add(filterButton);

        restoreButton = new JButton("Restore");
        add(restoreButton);

        sumButton = new JButton("Sum");
        add(sumButton);

        SumLabel = new JLabel("Total: 0");
        SumLabel.setPreferredSize(new Dimension(100, SumLabel.getPreferredSize().height));
        add(SumLabel);

    }

    // Methods to get the selected category

    public JButton getFilterButton() {
        return filterButton;
    }

    public JButton getRestoreButton() {
        return restoreButton;
    }

    public JButton getSumButton() { return sumButton;}



    public void applyFilter() {
        Category selectedCategory = (Category) filterComboBox.getSelectedItem();
        Currency selectedCurrency = (Currency) currencyfilterComboBox.getSelectedItem();
        // Retrieve filtered expenses and update the table
        savedExpensesPanel.updateTable(expensesManager.getExpensesByCategoryandCurrency(selectedCategory, selectedCurrency));
    }

    public void restoreFilter(){

        List<Expense> previouslySavedExpenses = savedExpensesPanel.getpreviouslySavedExpenses();
        if (previouslySavedExpenses != null) {
            savedExpensesPanel.updateTable(previouslySavedExpenses);
        }
    }

    public int sum() {
        int sum = 0;
        for(int i = 0; i < savedExpensesPanel.getExpenseTable().getRowCount(); i++) {
            sum += (double) savedExpensesPanel.getExpenseTable().getValueAt(i, 0);
        }
        return sum;
    }

    public void setSum(double sum) {
        SumLabel.setText("Total: " + sum);
    }



}
