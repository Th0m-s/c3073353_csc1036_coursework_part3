package assignment3.packages.Handler;

import java.io.Serializable;
import java.time.LocalDate;

public record Expense(double amount, Category category, LocalDate date) implements Serializable { }