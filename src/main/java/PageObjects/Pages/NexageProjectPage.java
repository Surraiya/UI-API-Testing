package PageObjects.Pages;

import Models.TestModel;
import PageObjects.Forms.ProjectPageForms.AllRunningTestForm;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.List;


public class NexageProjectPage extends Form {
    private static final Logger logger = Logger.getInstance();

    public NexageProjectPage(){
        super(By.xpath("//ol[@class= 'breadcrumb']/li[2]"), "Nexage Project");
    }

    private AllRunningTestForm getTestForm(){
        return new AllRunningTestForm();
    }

    public List<TestModel> getFirstPageTests(){
        return getTestForm().getAllRunningTests();
    }

    public boolean isFirstPageTestsSortedByDate(List<TestModel> tests){
        logger.info("Tests from first page of Nexage Project are sorted by Date");
        return getTestForm().isTestsSortedByStartTimeDesc(tests);
    }
}
