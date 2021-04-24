package com.yakubovskiy.design.entity.ticket;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;

@Slf4j
@Getter
@EqualsAndHashCode(of = "id")
@ToString
public class Ticket {
	private final UUID id;
	private final String from;
	private final String to;
	private int cost;

	private Ticket(UUID id, String from, String to) {
		this.id = id;
		this.from = from;
		this.to = to;
		log.info("Ticket {} was created", id);
	}

	public static Ticket of(@NonNull final String from, @NonNull final String to) {
		checkArgument(!from.equalsIgnoreCase(to), "Incorrect route");
		UUID id = UUID.randomUUID();
		return new Ticket(id, from, to);
	}

	public void setCost(final int cost) {
		checkArgument(cost > 0, "Cost less then 0");
		this.cost = cost;
		log.info("Cost of {} for ticket {} was set", this.cost, this.id);
	}

}
