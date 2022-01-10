package com.mivebe;

public class TryingEnums {

    public static void useWeekDays() {

        WeekDays day = WeekDays.Wednesday;

        if (day == WeekDays.Wednesday){
            System.out.println("Market Day");
        }

        for (WeekDays day2 : WeekDays.values()){
            System.out.println(day2);
        }

        System.out.println(Cereals.CEREAL1.price);
    }
}
