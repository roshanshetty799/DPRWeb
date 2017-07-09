package com.dpr.testCases;

import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.dpr.driver.DriverFactory;

public class calendarHanding {

static int targetDay = 4, targetMonth = 6, targetYear = 1993;

static int currenttDate = 0, currenttMonth = 0, currenttYear = 0;

static int jumMonthBy = 0;

static boolean increment = true;

public static void getCurrentDayMonth() {

    Calendar cal = Calendar.getInstance();
    currenttDate = cal.get(Calendar.DAY_OF_MONTH);
    currenttMonth = cal.get(Calendar.MONTH) + 1;
    currenttYear = cal.get(Calendar.YEAR);
}

public static void getTargetDayMonthYear(String dateString) {
    int firstIndex = dateString.indexOf("/");
    int lastIndex = dateString.lastIndexOf("/");

    String day = dateString.substring(0, firstIndex);
    targetDay = Integer.parseInt(day);

    String month = dateString.substring(firstIndex + 1, lastIndex);
    targetMonth = Integer.parseInt(month);

    String year = dateString.substring(lastIndex + 1, dateString.length());
    targetYear = Integer.parseInt(year);

}

public static void calculateToHowManyMonthToJump() {

    if ((targetMonth - currenttMonth) > 0) {
        jumMonthBy = targetMonth - currenttMonth;

    } else {
        jumMonthBy = currenttMonth - targetMonth;
        increment = false;
    }
}

public static void main(String[] args) throws InterruptedException {
    // TODO Auto-generated method stub
    String dateToSet = "16/12/2016";

    getCurrentDayMonth();
    System.out.println(currenttDate);
    System.out.println(currenttMonth);
    System.out.println(currenttYear);

    getTargetDayMonthYear(dateToSet);
    System.out.println(targetDay);
    System.out.println(targetMonth);
    System.out.println(targetYear);

    calculateToHowManyMonthToJump();
    System.out.println(jumMonthBy);
    System.out.println(increment);

   WebDriver driver  = new DriverFactory().getDriver();
   driver.navigate().to("https://jqueryui.com/resources/demos/datepicker/default.html");

    driver.findElement(By.xpath("//*[@id='datepicker']")).click();

    for (int i = 0; i < jumMonthBy; i++) {
        if (increment) {
            driver.findElement(
                    By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span"))
                    .click();
        } else {
            driver.findElement(
                    By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span"))
                    .click();
        }
        Thread.sleep(1000);

    }

    driver.findElement(By.linkText(Integer.toString(targetDay))).click();

}
}
