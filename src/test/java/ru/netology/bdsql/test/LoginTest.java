package ru.netology.bdsql.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.bdsql.data.DataBase;
import ru.netology.bdsql.data.DataHelper;
import ru.netology.bdsql.page.LoginPage;


import static com.codeborne.selenide.Selenide.open;

public class LoginTest {

    @AfterAll
    public static void afterAll() {
        DataBase.resetBase();
    }

    @Test
    void validData() {
        var loginPage = open("http://localhost:9999/", LoginPage.class);
        var authInfo = DataHelper.validUser();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifyVerificationPageVisibility();
        var verificationCode = DataBase.getValidVerifyCode();
        verificationPage.validVerify(verificationCode.getVerifyCode());
    }

    @Test
    void shouldShowErrorWhenRandomUser() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.invalidUser();
        var verificationPage = loginPage.validLogin(authInfo);
        loginPage.getError();
    }
    @Test
    void shouldBlockWhenInvalidPasswordThreeTimes() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = new DataHelper.AuthInfo(DataHelper.validUser().getLogin(), DataHelper.invalidPass());
        loginPage.validLogin(authInfo);
        loginPage.getError();
        loginPage.cleanFields();
        var authInfo1 = new DataHelper.AuthInfo(DataHelper.validUser().getLogin(), DataHelper.invalidPass());
        loginPage.validLogin(authInfo1);
        loginPage.getError();
        loginPage.cleanFields();
        var authInfo2 = new DataHelper.AuthInfo(DataHelper.validUser().getLogin(), DataHelper.invalidPass());
        loginPage.validLogin(authInfo2);
        loginPage.getBlockError();
    }
}