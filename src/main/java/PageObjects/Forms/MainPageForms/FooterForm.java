package PageObjects.Forms.MainPageForms;

import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;


public class FooterForm extends Form {

    private final ITextBox footerText =  getElementFactory().getTextBox(By.xpath("//div[@class='container']/p/span"), "Footer text");

    public FooterForm(){
        super(By.xpath("//footer[@class='footer']"), "Footer Form");
    }

    public String getVersionNumber(){
        return footerText.getText();
    }
}
