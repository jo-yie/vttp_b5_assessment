package vttp.batch5.sdf.task01;

// MODIFIED UTILITIES CLASS

public class Utils {

    public static final String[] SEASON = { "Spring", "Summer", "Fall", "Winter" };
	public static final String[] DAY = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
	public static final String[] MONTH = { "January", "Febuary", "March", "April", "May",
		"June", "July", "August", "September", "October", "November", "December"
	};
    public static final String[] WEATHER = {"Clear, Few clouds, Partly cloudy, Partly cloudy",
                                            "Mist + Cloudy, Mist + Broken clouds, Mist + Few clouds, Mist",
                                            "Light Snow, Light Rain + Thunderstorm + Scattered clouds, Light Rain + Scattered clouds",
                                            "Heavy Rain + Ice Pallets + Thunderstorm + Mist, Snow + Fog"};

	public static String toSeason(int season) {
		//season (1:spring, 2:summer, 3:fall, 4:winter)
		switch (season) {
			case 1:
			case 2:
			case 3:
			case 4:
				return SEASON[season - 1];
			default:
				return "funny season";
		}
	}

	public static String toWeekday(int weekday) {
		switch (weekday) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
				return DAY[weekday];
			default:
				return "incorrect day";
		}
	}

	public static String toMonth(int month) {
		switch (month) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
			case 11:
			case 12:
				return MONTH[month - 1];
			default:
				return "unknown month";
		}
	}

    public static String toWeather(int weather) {
        // 1, 2, 3, 4
        switch (weather) {
            case 1:
            case 2:
            case 3: 
            case 4: 
                return WEATHER[weather - 1];
            default: 
                return "incorrect weather";
        }

    }

}