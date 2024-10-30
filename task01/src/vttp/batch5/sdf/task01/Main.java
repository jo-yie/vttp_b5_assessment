package vttp.batch5.sdf.task01;

import java.util.*;
import java.io.*;

import vttp.batch5.sdf.task01.models.Bike;

// Use this class as the entry point of your program

public class Main {

	public static void main(String[] args) throws IOException {

		// instantiate BikeEntry class
		Bike be = new Bike();

		// store BikeEntry(s) in List<BikeEntry> bikeList
		List<Bike> bikeList = new ArrayList<>();

		// create new File
		File file = new File("day.csv"); 

		// decorator pattern
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);

		// ignore first line of csv file
        String headerLine = br.readLine(); 

		String temp = ""; 
		while ((temp = br.readLine()) != null) {

			// split each line in csv file by comma 
			String[] line = temp.split(",");
			
			// for each line --> new BikeEntry() 
			Bike tempBe = be.toBikeEntry(line);

			// put entry into bikeList
			bikeList.add(tempBe);

		}

		// sort bikeList by num casual + registered users (totalCyclists)
		Comparator<Bike> compare = Comparator.comparing(Bike::getTotalCyclists, Comparator.reverseOrder());
		bikeList.sort(compare);

		// select top 5 days and print 
		for (int i = 0; i < 5; i++) {
			Bike b = bikeList.get(i);
			printDetails(b, i);

			System.out.println();

		}

	}

	// print details 
	public static void printDetails(Bike b, int pos) {

		// instantiate new Utilities object 
		Utils ut = new Utils();

		String position = "";

		if (pos == 0) {
			position = "highest"; 
		} else if (pos == 1) {
			position = "second highest";
		} else if (pos == 2) {
			position = "third highest";
		} else if (pos == 3) {
			position = "fourth highest";
		} else if (pos == 4) {
			position = "fifth highest";
		}

		String season = ut.toSeason(b.getSeason()); 
		String day = ut.toWeekday(b.getWeekday()); 
		String month = ut.toMonth(b.getMonth()); 
		int total = b.getTotalCyclists(); 
		String weather = ut.toWeather(b.getWeather());
		boolean holiday = b.isHoliday();

		String holidayStr; 

		if (holiday) {
			holidayStr = "a holiday";
		} else {
			holidayStr = "not a holiday";
		}

		System.out.printf("The %s recorded number of cyclists was in %s, on a %s in the month of %s. There were a total of %d cyclists. The weather was %s.\n%s was %s.\n", position, season, day, month, total, weather, day, holidayStr);

	}

}
