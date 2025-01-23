package com.tz.Sber.autotests.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.selector.ByText;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Epic("SberLeasing UI Test")
@Feature("Car Selection")
public class SberLeasingTest {

    @Test
    @Description("Select a car and verify its brand")
    @Story("Car Selection")
    public void selectCarAndVerifyBrand() {
        Configuration.pageLoadTimeout = 60_000;

//        // 1. Перейти в Google
//        open("https://www.google.com");
//
//        // 2. Поиск СберЛизинг
//        $(By.name("q")).setValue("СберЛизинг").pressEnter();
//
//        // 3. Перейти на сайт СберЛизинг
//        $(By.partialLinkText("СберЛизинг")).click();

        //капча гугла не дает найти по поиску
        //пока сделал так для примера

        open("https://www.sberleasing.ru/");
        $("button.sbl-btn.sbl-btn_large.m-0").shouldHave(text("Принять всё")).click();
        // 4. Подобрать автомобиль по параметрам
        $("a.sbl-btn.sbl-btn_medium.mx-auto.sbl-btn_icon.sbl-btn_icon-config[href='/automall/#marketplace-horizontal-filter-title']").click();
        $x("//span[contains(text(), 'Город')]").shouldBe(visible).click();
        $x("//label[contains(text(), 'Краснодар')]").shouldBe(visible).click();
        $x("//span[contains(text(), 'Марка')]").shouldBe(visible).click();//.setValue("Cadillac");
        $x("//label[contains(text(), 'Changan')]").shouldBe(visible).click();
        $x("//span[contains(text(), 'Модель')]").shouldBe(visible).click();
        $x("//label[contains(text(), 'CS35 Plus')]").shouldBe(visible).click();
        executeJavaScript("document.activeElement.blur();");
        $("a.sbl-btn.sbl-btn_medium").shouldBe(visible).scrollTo();
        executeJavaScript("window.scrollBy(0, -100);");
        $("a.sbl-btn.sbl-btn_medium").click();
        // 6. Проверить соответствие марки
        $("div.car-card__item-mark").shouldHave(text("Changan CS35 Plus"));


    }
}


