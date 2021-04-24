package com.yakubovskiy.design.entity.user;

import com.yakubovskiy.design.entity.ticket.Ticket;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@Getter
@ToString
@EqualsAndHashCode(of = "ticket")
public class Passenger {
	private final UUID id;
	private final User user;
	private final Ticket ticket;

	private Passenger(UUID id, User user, Ticket ticket) {
		this.id = id;
		this.user = user;
		this.ticket = ticket;
		log.info("Passenger {} was created", id);
	}

	public static Passenger of(@NonNull final User user, @NonNull final Ticket ticket) {
		UUID id = UUID.randomUUID();
		return new Passenger(id, user, ticket);
	}
}
