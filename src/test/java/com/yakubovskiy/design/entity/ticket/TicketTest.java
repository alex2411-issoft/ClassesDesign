package com.yakubovskiy.design.entity.ticket;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TicketTest {
	private static Ticket ticket;

	@BeforeAll
	public static void initTicket() {
		ticket = Ticket.of("Minsk", "Lida");
	}

	@Test
	public void of_success() {
		assertThat(ticket, hasProperty("to", is(equalTo("Lida"))));
		assertThat(ticket, hasProperty("from", is(equalTo("Minsk"))));
		assertThat(ticket.getFrom(), not(equalToIgnoringCase(ticket.getTo())));
	}

	@Test
	public void of_nullArguments() {
		assertThrows(NullPointerException.class, () -> Ticket.of(null, null));
		assertThrows(NullPointerException.class, () -> Ticket.of("Minsk", null));
		assertThrows(NullPointerException.class, () -> Ticket.of(null, "Lida"));
	}

	@Test
	public void of_incorrectRoute() {
		assertThrows(IllegalArgumentException.class, () -> Ticket.of("Minsk", "Minsk"));
	}

	@Test
	public void setCost_success() {
		ticket.setCost(100);
		assertThat(ticket, hasProperty("cost", equalTo(100)));
	}

	@Test
	public void setCost_incorrectCost() {
		assertThrows(IllegalArgumentException.class, () -> ticket.setCost(-100));
	}
}