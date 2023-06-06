package PageObjects.Forms;

import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HeaderForm extends Form {

    private final IButton addProject = getElementFactory().getButton(By.cssSelector(".btn.btn-xs.btn-primary.pull-right"),"Add Project Button", ElementState.DISPLAYED);

    public HeaderForm(){
        super(By.xpath("//div[@class='panel-heading']"), "Header Form");
    }

    public void clickOnAddProject(){
        addProject.click();
    }
}
