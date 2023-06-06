package PageObjects.Forms.ProjectPageForms;

import Models.TestModel;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static Utilities.TestTableIndex.*;

public class AllRunningTestForm extends Form {

    private final ITextBox tableBody = getElementFactory().getTextBox(By.xpath("//table[@class='table']/tbody"), "Table rows");
    private final By rowsLocator = By.xpath("//tr");
    private final By columnsLocator = By.xpath("//td");

    public AllRunningTestForm(){
        super(By.xpath("//table[@class='table']"), "All Running Test Form");
    }


    public List<TestModel> getAllRunningTests() {
        return getTestRows()
                .map(row -> createTestModel(row))
                .toList();
    }

    public List<String> getAllTestsName() {
        return getTestRows()
                .flatMap(row -> row.findChildElements(columnsLocator, ElementType.TEXTBOX).stream())
                .map(column -> column.getText())
                .toList();
    }

    private Stream<IElement> getTestRows() {
        return tableBody.findChildElements(rowsLocator, ElementType.TEXTBOX)
                .stream()
                .skip(1);
    }

    private TestModel createTestModel(IElement row) {
        List<ITextBox> columns = row.findChildElements(columnsLocator, ElementType.TEXTBOX);
        return new TestModel(
                columns.get(DURATION.index).getText(),
                columns.get(METHOD.index).getText(),
                columns.get(NAME.index).getText(),
                columns.get(START_TIME.index).getText(),
                columns.get(END_TIME.index).getText(),
                columns.get(STATUS.index).getText()
        );
    }

    public boolean isTestsSortedByStartTimeDesc(List<TestModel> tests){
        return IntStream.range(0, tests.size() - 1)
                .allMatch(i -> tests.get(i).getStartTime()
                        .compareTo(tests.get(i + 1).getStartTime()) >= 0);
    }
}
