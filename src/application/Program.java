package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import model.entities.Reservation;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.print("Room number: ");
		int roomNumber = sc.nextInt();
		System.out.print("Check-in date (DD/MM/YYYY): ");
		LocalDate checkIn = LocalDate.parse(sc.next(), dateFormatter);
		System.out.print("Check-out date (DD/MM/YYYY): ");
		LocalDate checkOut = LocalDate.parse(sc.next(), dateFormatter);
		
		if(checkIn.isAfter(LocalDate.now()) && checkOut.isAfter(LocalDate.now())) {
			if(checkOut.isAfter(checkIn)) {
				Reservation reservation = new Reservation(roomNumber, checkIn, checkOut);
				System.out.println(reservation);
				
				System.out.println();
				System.out.println("Enter data to update the reservation:");
				System.out.print("Check-in date (DD/MM/YYYY): ");
				checkIn = LocalDate.parse(sc.next(), dateFormatter);
				System.out.print("Check-out date (DD/MM/YYYY): ");
				checkOut = LocalDate.parse(sc.next(), dateFormatter);
						
				String error = reservation.updateDates(checkIn, checkOut);
				if(error != null) {
					System.out.println("Error in reservation: " + error);
				} else {
					System.out.println(reservation);
				}
				
			} else {
				System.out.println("Error in reservation: Check-out date must be after check-in date");
			}
		} else {
			System.out.println("Error in reservation: Reservation dates must be future dates");
		}
			
		sc.close();
	}
}
