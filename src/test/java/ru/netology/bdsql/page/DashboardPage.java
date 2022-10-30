package ru.netology.bdsql.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    private SelenideElement header = $("[data-test-id=\"dashboard\"]");

    public void visiblePage() {
        header.should(visible);
    }
}