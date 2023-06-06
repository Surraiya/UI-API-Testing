package PageObjects.Pages;

import PageObjects.Forms.ProjectPageForms.AllRunningTestForm;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class CreatedProjectPage extends Form {

    public CreatedProjectPage(){
        super(By.xpath("//ol[@class='breadcrumb']/li[2]"), "Created Project page");
    }

    private AllRunningTestForm getTestForm(){
        return new AllRunningTestForm();
    }

    public boolean isTestNameInTestListOfCreatedProject(String name) {
        return getTestForm().getAllTestsName().stream()
                .anyMatch(testName -> testName.equals(name));
    }
}
