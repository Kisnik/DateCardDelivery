import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DateCardDeliveryTest {


    Person person = PersonGenerator.Generator.personGenerator("ru");


    String returnDate(int daysFromToday) {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, daysFromToday);
        return dateFormat.format(cal.getTime());
    }

    @BeforeEach
    void openWebSite() {
        open("http://localhost:7777");
    }



    @Test
    void shouldSuccess() {
        $("[data-test-id=city] .input__control").setValue(person.getCity());
        $("[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(Keys.DELETE);
        $("[placeholder=\"Дата встречи\"]").setValue(returnDate(3));
        $("[data-test-id=name] .input__control").setValue(person.getName());
        $("[name=phone]").setValue(person.getPhone());
        $("[data-test-id=agreement] .checkbox__box").click();
        $(byText("Запланировать")).click();
        $("[data-test-id=success-notification] .notification__content").
                shouldHave(exactText("Встреча успешно запланирована на " + returnDate(3)));
        $(byText("Запланировать")).click();
        $("[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(Keys.DELETE);
        $("[placeholder=\"Дата встречи\"]").setValue(returnDate(4));
        $("[data-test-id=replan-notification] .notification__title").shouldHave(exactText("Необходимо подтверждение"));
        $(byText("Перепланировать")).click();
        $("[data-test-id=success-notification] .notification__content").
                shouldHave(exactText("Встреча успешно запланирована на " + returnDate(4)));


    }

}
