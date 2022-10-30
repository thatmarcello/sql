package ru.netology.bdsql.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class VerificationPage {

    private SelenideElement verificationCodeField = $("[data-test-id=\"code\"] .input__control");
    private SelenideElement buttonEnter = $("[data-test-id=\"action-verify\"] .button__text");
    private SelenideElement errorNotification = $("[data-test-id='error-notification']");

    public void verifyVerificationPageVisibility() {
        verificationCodeField.should(Condition.visible);
    }

    public void verifyErrorNotificationVisibility() {
        errorNotification.should(Condition.visible);
    }

    public DashboardPage validVerify(String code) {
        input(code);
        return page(DashboardPage.class);
    }

    public void input(String code) {
        verificationCodeField.val(code);
        buttonEnter.click();
    }

}