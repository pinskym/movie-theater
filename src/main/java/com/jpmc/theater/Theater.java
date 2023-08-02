package com.jpmc.theater;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Theater {
    private static final NumberFormat MONEY_FORMAT = NumberFormat.getCurrencyInstance();

    public static void main(String[] args) {
        Theater theater = new Theater(LocalDate.now());
        theater.printSchedule();
        System.out.println("JSON:\n" + theater.toJson());
    }

    private final LocalDate currentDate;
    private final List<Showing> showings;
    private final Map<String, Map<LocalDateTime, Showing>> schedule = new HashMap<>();

    public Theater(LocalDate date) {
        this.currentDate = date;
        Movie spiderMan = new Movie("Spider-Man: No Way Home", "A good movie", Duration.ofMinutes(90), 1250, true);
        Movie turningRed = new Movie("Turning Red", "A so-so movie", Duration.ofMinutes(85), 1100);
        Movie theBatMan = new Movie("The Batman", "A Great movie", Duration.ofMinutes(95), 9000);
        showings = List.of(
                new Showing(turningRed, 1, LocalDateTime.of(currentDate, LocalTime.of(9, 0))),
                new Showing(spiderMan, 2, LocalDateTime.of(currentDate, LocalTime.of(11, 0))),
                new Showing(theBatMan, 3, LocalDateTime.of(currentDate, LocalTime.of(12, 50))),
                new Showing(turningRed, 4, LocalDateTime.of(currentDate, LocalTime.of(14, 30))),
                new Showing(spiderMan, 5, LocalDateTime.of(currentDate, LocalTime.of(16, 10))),
                new Showing(theBatMan, 6, LocalDateTime.of(currentDate, LocalTime.of(17, 50))),
                new Showing(turningRed, 7, LocalDateTime.of(currentDate, LocalTime.of(19, 30))),
                new Showing(spiderMan, 8, LocalDateTime.of(currentDate, LocalTime.of(21, 10))),
                new Showing(theBatMan, 9, LocalDateTime.of(currentDate, LocalTime.of(23, 0)))
        );
        showings.forEach(s -> schedule.computeIfAbsent(s.getMovie().getTitle(), dummy -> new HashMap<>())
                .put(s.getStartTime(), s));
    }

    public Reservation reserve(String movieTitle, LocalTime startTime, Customer customer, int howManyTickets) {
        if (!schedule.containsKey(movieTitle)) {
            System.err.println("No title \"" + movieTitle + "\" is running today");
            return null;
        }
        Showing showing = schedule.get(movieTitle).get(LocalDateTime.of(currentDate, startTime));
        if (showing == null) {
            System.err.println("Movie \"" + movieTitle + "\" does not run at " + startTime.getHour() + ":" + startTime.getMinute());
            return null;
        }
        return new Reservation(customer, showing, howManyTickets);
    }

    public void printSchedule() {
        System.out.println(currentDate);
        System.out.println("===================================================");
        showings.forEach(s ->
                System.out.println(s.getOrderOfShow() + ": " + s.getStartTime() + " " + s.getMovie().getTitle() + " " + humanReadableFormat(s.getMovie().getRunningTime()) + " "
                        + moneyString(s.getDiscountedPrice())
                        + (s.getFullPrice() != s.getDiscountedPrice() ? " (full " + moneyString(s.getFullPrice()) + ")" : ""))
        );
        System.out.println("===================================================");
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        showings.forEach(s -> {
            JSONObject item = new JSONObject();
            item.put("orderNumber", s.getOrderOfShow());
            item.put("startTime", s.getStartTime());
            item.put("title", s.getMovie().getTitle());
            item.put("runningTime", s.getMovie().getRunningTime());
            item.put("discountedPrice", moneyString(s.getDiscountedPrice()));
            item.put("fullPrice", moneyString(s.getFullPrice()));
            array.put(item);
        });

        json.put("schedule", array);
        return json;
    }

    public String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    // (s) postfix should be added to handle plural correctly
    private String handlePlural(long value) {
        return value == 1 ? "" : "s";
    }

    public static String moneyString(long longCentsValue) {
        return MONEY_FORMAT.format(longCentsValue / 100.0);
    }
}
