package com.yakubovskiy.design.entity.train;

import com.yakubovskiy.design.entity.cargo.Cargo;
import com.yakubovskiy.design.entity.cargo.CargoState;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

@Slf4j
@Getter
public class CargoCarriage extends Carriage {
	private final int capacity;
	private final CargoState state;
	private final List<Cargo> cargoes;

	private CargoCarriage(UUID id, int capacity, CargoState cargoState) {
		super(id);
		this.capacity = capacity;
		this.state = cargoState;
		this.cargoes = new ArrayList<>(capacity);
		log.info("Cargo carriage {} was created", id);
	}

	public static CargoCarriage of(final int capacity, @NonNull final CargoState state) {
		checkArgument(capacity > 0, "Capacity is less than 0");
		UUID number = UUID.randomUUID();
		return new CargoCarriage(number, capacity, state);
	}

	public void addCargo(@NonNull final Cargo cargo) {
		checkState(cargo.getState() == this.state, "Incorrect cargo state");
		checkState(cargoes.size() + cargo.getWeight() <= capacity, "Train is full");
		cargoes.add(cargo);
	}

	public void removeCargo(final int index) {
		checkArgument(index >= 0 && index <= cargoes.size(), "Incorrect index");
		cargoes.remove(index);
		log.info("Cargo {} was removed from train {}", index, this.getId());
	}

	public void removeCargo(@NonNull final Cargo cargo) {
		checkArgument(cargoes.contains(cargo), "This cargo is not in this carriage");
		cargoes.remove(cargo);
		log.info("Cargo {} was removed from train {}", cargo.getNumber(), this.getId());
	}

	public void removeAllCargoes() {
		cargoes.clear();
		log.info("All cargoes was removed from train {}", this.getId());
	}

	public List<Cargo> getCargoes() {
		return List.copyOf(cargoes);
	}

	@Override
	public String toString() {
		return "CargoCarriage{" +
				"id = " + this.getId() +
				", state = " + this.getState() +
				", capacity = " + this.getCapacity() +
				", cargoes = " + this.getCargoes() + "} ";
	}
}
