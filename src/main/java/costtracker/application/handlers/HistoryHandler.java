package costtracker.application.handlers;
import costtracker.application.handlers.interfaces.PrinterHandler;
import costtracker.domain.businessobjects.IncorrectEntryException;
import costtracker.domain.businessobjects.Purchase;
import costtracker.domain.in.HistoryDocument;
import costtracker.domain.in.HistoryDocumentBase;
import costtracker.domain.in.enums.ElementType;

import java.time.LocalDate;
import java.util.List;


public class HistoryHandler implements PrinterHandler{

    @Override
    public void printToCSV(String description, String name, String path, LocalDate startDate, LocalDate endDate, List<Purchase> purchases) throws IncorrectEntryException {
        HistoryDocument document = HistoryDocumentBase.HistoryDocumentBuilder
                .asCSV()
                .withDescription(description)
                .withKpi(ElementType.Category)
                .withName(name)
                .withPath(path)
                .withTimespan(startDate, endDate)
                .withData(purchases)
                .build();

        document.print();
    }

    @Override
    public void printToXML(String description, String name, String path, LocalDate startDate, LocalDate endDate, List<Purchase> purchases) throws IncorrectEntryException {
        HistoryDocument document = HistoryDocumentBase.HistoryDocumentBuilder
                .asXML()
                .withDescription(description)
                .withKpi(ElementType.Category)
                .withName(name)
                .withPath(path)
                .withTimespan(startDate, endDate)
                .withData(purchases)
                .build();

        document.print();
    }

    @Override
    public void printToJSON(String description, String name, String path, LocalDate startDate, LocalDate endDate, List<Purchase> purchases) throws IncorrectEntryException {
        HistoryDocument document = HistoryDocumentBase.HistoryDocumentBuilder
                .asJSON()
                .withDescription(description)
                .withKpi(ElementType.Category)
                .withName(name)
                .withPath(path)
                .withTimespan(startDate, endDate)
                .withData(purchases)
                .build();

        document.print();
    }
}