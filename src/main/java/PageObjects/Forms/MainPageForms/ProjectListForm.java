package PageObjects.Forms.MainPageForms;

import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.List;


public class ProjectListForm extends Form {

    private final IButton projectContainer = getElementFactory().getButton(By.xpath("//div[@class='list-group']"),
            "Project List Container", ElementState.EXISTS_IN_ANY_STATE);
    private final String projectsLocator = "//a[@class='list-group-item']";

    public ProjectListForm(){
        super(By.xpath("//div[@class='list-group']"), "Project List Form");
    }

    private List<IButton> getProjects(){
        return projectContainer.findChildElements(By.xpath(projectsLocator), ElementType.BUTTON);
    }

    public boolean isProjectExist(String projectName){
        return getProjects().stream()
                .map(IButton::getText)
                .anyMatch(name -> name.equals(projectName));
    }

    public IButton getCreatedProject(String projectName){
        return getProjects().stream()
                .filter(project -> project.getText().equals(projectName))
                .findFirst()
                .orElse(null);
    }
}
