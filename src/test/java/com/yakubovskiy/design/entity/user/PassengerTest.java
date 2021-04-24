package com.yakubovskiy.design.entity.user;

import com.yakubovskiy.design.entity.ticket.Ticket;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PassengerTest {
	private static Ticket ticket;
	private static User user;
	private static Passenger passenger;

	@BeforeAll
	public static void initPassenger() {
		user = User.of("Alex", "Ivanov");
		ticket = Ticket.of("Minsk", "Lida");
		passenger = Passenger.of(user, ticket);
	}

	@Test
	public void of_success() {
		assertThat(passenger, hasProperty("ticket", is(notNullValue())));
		assertThat(passenger, hasProperty("user", is(notNullValue())));
	}

	@Test
	public void of_nullArguments() {
		assertThrows(NullPointerException.class, () -> Passenger.of(null, null));
		assertThrows(NullPointerException.class, () -> Passenger.of(user, null));
		assertThrows(NullPointerException.class, () -> Passenger.of(null, ticket));
	}
}