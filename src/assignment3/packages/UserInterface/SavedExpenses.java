package assignment3.packages.UserInterface;

import assignment3.packages.Handler.Expense;

import javax.swing.table.AbstractTableModel;
import java.util.List;


public class SavedExpenses extends AbstractTableModel {
    private final String[] columnNames = {"Amount", "Category", "Date", "Currency"};
    private List<Expense> expenses;

    public SavedExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }


    public List<Expense> getExpenses() {
        return expenses;
    }

    @Override
    public int getRowCount() {
        return expenses.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Expense expense = expenses.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> expense.amount();
            case 1 -> expense.category().getDisplayName();
            case 2 -> expense.date().toString();
            case 3 -> expense.currency().getDisplayName();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }


    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
        this.fireTableDataChanged(); // Notifies the table that the data has changed
    }
}
