package PageObjects.Pages;

import PageObjects.Forms.FooterForm;
import PageObjects.Forms.HeaderForm;
import PageObjects.Forms.MainPageForms.ProjectListForm;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import static Utilities.StringUtil.extractIntegerFromString;

public class MainPage extends Form{

    private final IButton nexage = getElementFactory().getButton(By.xpath("//div[@class='list-group']/a[contains(@href,1)]"), "Nexage Project");

    public MainPage(){
        super(By.xpath("//ol[@class= 'breadcrumb']/li[1]"), "Main Page");
    }

    private FooterForm getFooterForm(){
        return new FooterForm();
    }

    public String getVersionOptionNumber(){
        return getFooterForm().getVersionNumber();
    }

    public void clickNexageProject() {
        nexage.click();
    }

    public String getNexageProjectId(){
        return extractIntegerFromString(nexage.getAttribute("href"), "projectId=(\\d+)");
    }

    private HeaderForm getHeaderForm(){
        return new HeaderForm();
    }

    public void clickAddProjectButton(){
        getHeaderForm().clickOnAddProject();
    }

    public ProjectListForm getProjectListForm(){
        return new ProjectListForm();
    }

    public boolean isProjectSavedSuccessfully(String projectName){
        return getProjectListForm().isProjectExist(projectName);
    }

    public void goToCreatedProjectPage(String projectName){
        getProjectListForm().getCreatedProject(projectName).click();
    }
}
