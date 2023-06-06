package PageObjects.Pages;

import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AddProjectPage extends Form {

    private final ITextBox projectName = getElementFactory().getTextBox(By.xpath("//input[@id='projectName']"), "Project Name Textfield");
    private final IButton saveProject = getElementFactory().getButton(By.xpath("//button[@type='submit']"), "Save Project Button");
    private final ILabel successMessage = getElementFactory().getLabel(By.xpath("//div[@class='form-group']/div"),"Sucess Message", ElementState.DISPLAYED);


    public AddProjectPage(){
        super(By.xpath("//form[@id='addProjectForm']"), "Add Project Page");
    }

    public void enterProjectName(String name){
        projectName.sendKeys(name);
    }

    public void saveProjectName(){
        saveProject.click();
    }

    public String getMessageAfterSavingProject(){
        return successMessage.getText();
    }
}
