package com.yakubovskiy.design.entity.train;

import com.yakubovskiy.design.entity.ticket.Ticket;
import com.yakubovskiy.design.entity.user.Passenger;
import com.yakubovskiy.design.entity.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassengerCarriageTest {
	private static PassengerCarriage passengerCarriage;
	private static Passenger passenger;

	@BeforeAll
	public static void initPassengerCarriage() {
		passengerCarriage = PassengerCarriage.of();
		User user = User.of("Alex", "Ivanov");
		Ticket ticket = Ticket.of("Minsk", "Lida");
		passenger = Passenger.of(user, ticket);
	}

	@AfterEach
	public void removePassengers() {
		passengerCarriage.removeAllPassengers();
	}

	@Test
	public void addPassenger_success() {
		passengerCarriage.addPassenger(passenger);
		assertTrue(passengerCarriage.getPassengers()
				.contains(passenger));
	}

	@Test
	public void addPassenger_carriageIsFull() {
		assertThrows(IllegalStateException.class, () -> {
			User user = User.of("Alex", "Ivanov");
			for (int i = 0; i < 30; i++) {
				Ticket ticket = Ticket.of("Minsk", "Lida");
				passenger = Passenger.of(user, ticket);
				passengerCarriage.addPassenger(passenger);
			}
		});
	}

	@Test
	public void addPassenger_nullArgument() {
		assertThrows(NullPointerException.class, () -> passengerCarriage.addPassenger(null));
	}

	@Test
	public void removePassenger_successByIndex() {
		passengerCarriage.addPassenger(passenger);
		int index = passengerCarriage.getPassengers()
				.indexOf(passenger);
		passengerCarriage.removePassenger(index);
		assertFalse(passengerCarriage.getPassengers()
				.contains(passenger));
	}

	@Test
	public void removePassenger_successByPassenger() {
		passengerCarriage.addPassenger(passenger);
		assertTrue(passengerCarriage.getPassengers()
				.contains(passenger));
		passengerCarriage.removePassenger(passenger);
		assertFalse(passengerCarriage.getPassengers()
				.contains(passenger));
	}

	@Test
	public void removePassenger_incorrectIndex() {
		passengerCarriage.addPassenger(passenger);
		assertThrows(IllegalArgumentException.class, () -> passengerCarriage.removePassenger(10));
	}

	@Test
	public void removePassenger_notContainedInCarriage() {
		User user = User.of("Ivan","Ivanov");
		Ticket ticket = Ticket.of("Minsk","Lida");
		Passenger newPassenger = Passenger.of(user,ticket);
		assertFalse(passengerCarriage.getPassengers().contains(newPassenger));
		assertThrows(IllegalArgumentException.class, () -> passengerCarriage.removePassenger(newPassenger));
	}

	@Test
	public void removePassenger_nullArgument() {
		assertThrows(NullPointerException.class, () -> passengerCarriage.removePassenger(null));
	}

	@Test
	public void removeAllPassengers_success() {
		passengerCarriage.addPassenger(passenger);
		int size = passengerCarriage.getPassengers()
				.size();
		passengerCarriage.removeAllPassengers();
		int sizeAfterRemove = passengerCarriage.getPassengers()
				.size();
		assertTrue(passengerCarriage.getPassengers()
				.isEmpty());
		assertNotEquals(sizeAfterRemove, size);
	}
}