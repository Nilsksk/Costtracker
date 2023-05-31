package costtracker.application.handlers.interfaces;


import java.time.LocalDate;
import java.util.List;

import costtracker.domain.businessobjects.IncorrectEntryException;
import costtracker.domain.businessobjects.Purchase;

public interface PrinterHandler{

    void printToCSV(String description, String name, String path, LocalDate startDate, LocalDate endDate, List<Purchase> purchases) throws IncorrectEntryException;

    void printToXML(String description, String name, String path, LocalDate startDate, LocalDate endDate, List<Purchase> purchases) throws IncorrectEntryException;

    void printToJSON(String description, String name, String path, LocalDate startDate, LocalDate endDate, List<Purchase> purchases) throws IncorrectEntryException;

}