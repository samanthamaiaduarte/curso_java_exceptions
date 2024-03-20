package model.entities;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.exceptions.DomainException;

public class Reservation {
	private Integer roomNumber;
	private LocalDate checkIn;
	private LocalDate checkOut;
	
	private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
	
	public Reservation(Integer roomNumber, LocalDate checkIn, LocalDate checkOut) {
		validateDates(checkIn, checkOut);		
		
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}
	
	public long duration() {
		return Duration.between(checkIn.atStartOfDay(), checkOut.atStartOfDay()).toDays();
	}
	
	public void updateDates(LocalDate checkIn, LocalDate checkOut) {
		validateDates(checkIn, checkOut);
		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	private void validateDates(LocalDate checkIn, LocalDate checkOut) {
		if(checkIn.isBefore(LocalDate.now()) || checkOut.isBefore(LocalDate.now())) {
			 throw new DomainException("Reservation dates must be future dates");
		}
		
		if(checkOut.isBefore(checkIn)) {
			throw new DomainException("Check-out date must be after check-in date");
		}		
	}
	
	@Override
	public String toString() {
		StringBuilder reservation = new StringBuilder();
		long nights = duration();
		
		reservation.append("Reservation: ");
		reservation.append("Room " + roomNumber + ", ");
		reservation.append("check-in: " + checkIn.format(dateFormatter) + ", ");
		reservation.append("check-out: " + checkOut.format(dateFormatter) + ", ");
		reservation.append(nights + (nights > 1 ? " nights" : " night"));
		
		return reservation.toString();
	}
}
