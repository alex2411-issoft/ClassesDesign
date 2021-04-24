package com.yakubovskiy.design.entity.cargo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;

@Slf4j
@ToString
@Getter
@EqualsAndHashCode(of = "number")
public class Cargo {
	private final UUID number;
	private final String name;
	private final CargoState state;
	private int weight;


	private Cargo(UUID number, String name, CargoState state) {
		this.number = number;
		this.name = name;
		this.state = state;
		log.info("Cargo {} was created", number);
	}

	public static Cargo of(@NonNull final String name, @NonNull final CargoState state) {
		UUID id = UUID.randomUUID();
		return new Cargo(id, name, state);
	}

	public void setWeight(final int weight) {
		checkArgument(weight > 0, "Weight less than 0");
		this.weight = weight;
		log.info("Weight of {} for cargo {} was set", this.weight, this.number);
	}
}
