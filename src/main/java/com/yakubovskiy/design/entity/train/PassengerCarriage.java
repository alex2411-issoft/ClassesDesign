package com.yakubovskiy.design.entity.train;

import com.yakubovskiy.design.entity.user.Passenger;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

@Slf4j
public class PassengerCarriage extends Carriage {
	private static final int NUMBER_OF_SEATS = 25;
	private final List<Passenger> passengers = new ArrayList<>(NUMBER_OF_SEATS);

	private PassengerCarriage(UUID id) {
		super(id);
		log.info("Passenger carriage {} was created", id);
	}

	public static PassengerCarriage of() {
		UUID number = UUID.randomUUID();
		return new PassengerCarriage(number);
	}

	public void addPassenger(@NonNull final Passenger passenger) {
		checkState(passengers.size() < NUMBER_OF_SEATS, "Train is full");
		passengers.add(passenger);
		log.info("Passenger {} was added to train {}", passenger.getId(), this.getId());
	}

	public void removePassenger(final int index) {
		checkArgument(index >= 0 && index <= passengers.size(), "Incorrect index");
		passengers.remove(index);
		log.info("Passenger {} was removed from train {}", index, this.getId());
	}

	public void removePassenger(@NonNull final Passenger passenger) {
		checkArgument(passengers.contains(passenger), "This Passenger is not in this carriage");
		passengers.remove(passenger);
		log.info("Passenger {} was removed from train {}", passenger.getId(), this.getId());
	}

	public void removeAllPassengers() {
		passengers.clear();
		log.info("All passengers was removed from train {}", this.getId());
	}

	public List<Passenger> getPassengers() {
		return List.copyOf(passengers);
	}

	@Override
	public String toString() {
		return "PassengerCarriage{" +
				"id = " + this.getId() +
				", capacity = " + NUMBER_OF_SEATS +
				", passengers = " + this.getPassengers() + "} ";
	}
}
