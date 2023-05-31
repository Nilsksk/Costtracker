package costtracker.it.documentservice;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

import costtracker.adapter.dialog.DialogDisplay;
import costtracker.adapter.document.HistoryDocumentDialog;
import costtracker.adapter.document.HistoryDocumentRepository;
import costtracker.adapter.persistence.UnitOfWork;
import costtracker.adapter.repositoryadapters.PurchaseRepositoryAdapterImp;
import costtracker.application.in.HistoryDocumentDialogAdapter;
import costtracker.application.in.HistoryDocumentService;
import costtracker.application.interfaces.HistoryDocumentAdapter;
import costtracker.application.interfaces.PurchaseRepositoryAdapter;
import costtracker.domain.dependencyinjection.DependencyContainer;
import costtracker.plugin.console.dialog.ConsoleDisplay;
import costtracker.plugin.db.unitofwork.UnitOfWorkImp;

public class DocumentServiceIntegrationTest {
	@Test
	void TestCreateDocument() {
		DependencyContainer container = DependencyContainer.getInstance();
		container.registerScoped(UnitOfWork.class, UnitOfWorkImp.class);
		container.registerScoped(PurchaseRepositoryAdapter.class, PurchaseRepositoryAdapterImp.class);
		container.registerScoped(HistoryDocumentAdapter.class, HistoryDocumentRepository.class);
		container.registerScoped(HistoryDocumentDialogAdapter.class, HistoryDocumentDialog.class);
		container.registerScoped(DialogDisplay.class, ConsoleDisplay.class);
		container.registerSingleton(Scanner.class, new Scanner(System.in));
		
		HistoryDocumentService service = new HistoryDocumentService();
		
		service.createHistoryDocument();
		
	}
}
