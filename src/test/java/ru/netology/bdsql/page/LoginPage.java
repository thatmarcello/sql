package ru.netology.bdsql.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.bdsql.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=\"login\"] .input__control");
    private SelenideElement passwordField = $("[data-test-id=\"password\"] .input__control");
    private SelenideElement buttonResume = $("[data-test-id=\"action-login\"] .button__text");

    private SelenideElement error = $("[data-test-id=\"error-notification\"]");
    private SelenideElement buttonError = $("[class=\"icon-button__content\"]");


    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.val(info.getLogin());
        passwordField.val(info.getPassword());
        buttonResume.click();
        return page(VerificationPage.class);
    }
    public void getError() {
        error.shouldBe(visible);
    }
    public void cleanFields() {
        loginField.doubleClick().sendKeys(Keys.BACK_SPACE);
        passwordField.doubleClick().sendKeys(Keys.BACK_SPACE);
    }

    public void getBlockError() {
        error
                .shouldHave(text("Вы ввели неверный пароль 3 раза. Возможность входа в личный кабинет заблокирована. Обратитесь в службу поддержки банка."))
                .shouldBe(visible);
    }

}