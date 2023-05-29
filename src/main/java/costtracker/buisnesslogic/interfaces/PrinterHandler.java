package costtracker.buisnesslogic.interfaces;


import costtracker.businessobjects.IncorrectEntryException;
import costtracker.businessobjects.Purchase;
import java.time.LocalDate;
import java.util.List;

public interface PrinterHandler{

    void printToCSV(String description, String name, String path, LocalDate startDate, LocalDate endDate, List<Purchase> purchases) throws IncorrectEntryException;

    void printToXML(String description, String name, String path, LocalDate startDate, LocalDate endDate, List<Purchase> purchases) throws IncorrectEntryException;

    void printToJSON(String description, String name, String path, LocalDate startDate, LocalDate endDate, List<Purchase> purchases) throws IncorrectEntryException;

}