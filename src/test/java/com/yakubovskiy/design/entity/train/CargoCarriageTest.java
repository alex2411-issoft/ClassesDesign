package com.yakubovskiy.design.entity.train;

import com.yakubovskiy.design.entity.cargo.Cargo;
import com.yakubovskiy.design.entity.cargo.CargoState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class CargoCarriageTest {
	private static CargoCarriage cargoCarriage;
	private static Cargo cargo;

	@AfterEach
	public void removeCargoes() {
		cargoCarriage.removeAllCargoes();
	}

	@Test
	public void of_success() {
		assertThat(cargoCarriage, hasProperty("capacity", is(greaterThanOrEqualTo(0))));
	}

	@Test
	public void of_incorrectCapacity() {
		assertThrows(IllegalArgumentException.class, () -> CargoCarriage.of(-1000, CargoState.SOLID));
	}

	@Test
	public void of_nullArguments() {
		assertThrows(NullPointerException.class, () -> CargoCarriage.of(0, null));
	}

	@Test
	public void addCargo_success() {
		cargoCarriage = CargoCarriage.of(1000, CargoState.SOLID);
		cargo = Cargo.of("Wood", CargoState.SOLID);
		cargoCarriage.addCargo(cargo);
		assertTrue(cargoCarriage.getCargoes().contains(cargo));
	}

	@Test
	public void addCargo_incorrectState() {
		cargoCarriage = CargoCarriage.of(1000, CargoState.SOLID);
		cargo = Cargo.of("Oil", CargoState.LIQUID);
		assertThrows(IllegalStateException.class, ()-> cargoCarriage.addCargo(cargo));
	}

	@Test
	public void addCargo_carriageIsFull() {
		cargoCarriage = CargoCarriage.of(1000, CargoState.LIQUID);
		cargo = Cargo.of("Oil", CargoState.LIQUID);
		cargo.setWeight(2000);
		assertThrows(IllegalStateException.class, () -> cargoCarriage.addCargo(cargo));
	}

	@Test
	public void addCargo_nullArgument() {
		assertThrows(NullPointerException.class, () -> cargoCarriage.addCargo(null));
	}

	@Test
	public void removeCargo_successByIndex() {
		cargoCarriage = CargoCarriage.of(1000, CargoState.SOLID);
		cargo = Cargo.of("Wood", CargoState.SOLID);
		cargoCarriage.addCargo(cargo);
		int index = cargoCarriage.getCargoes()
				.indexOf(cargo);
		cargoCarriage.removeCargo(index);
		assertFalse(cargoCarriage.getCargoes()
				.contains(cargo));
	}

	@Test
	public void removeCargo_successByCargo() {
		cargoCarriage = CargoCarriage.of(1000, CargoState.SOLID);
		cargo = Cargo.of("Wood", CargoState.SOLID);
		cargoCarriage.addCargo(cargo);
		cargoCarriage.removeCargo(cargo);
		assertFalse(cargoCarriage.getCargoes()
				.contains(cargo));
	}

	@Test
	public void removeCargo_incorrectIndex() {
		cargoCarriage = CargoCarriage.of(1000, CargoState.SOLID);
		cargo = Cargo.of("Wood", CargoState.SOLID);
		cargoCarriage.addCargo(cargo);
		assertThrows(IllegalArgumentException.class, () -> cargoCarriage.removeCargo(10));
	}

	@Test
	public void removeCargo_notContainedInCarriage() {
		Cargo newCargo = Cargo.of("Wood", CargoState.SOLID);
		assertThrows(IllegalArgumentException.class, () -> cargoCarriage.removeCargo(newCargo));
	}

	@Test
	public void removeCargo_nullArgument() {
		assertThrows(NullPointerException.class, () -> cargoCarriage.removeCargo(null));
	}

	@Test
	public void removeAllCargoes() {
		cargoCarriage = CargoCarriage.of(1000, CargoState.SOLID);
		cargo = Cargo.of("Wood", CargoState.SOLID);
		cargoCarriage.addCargo(cargo);
		int size = cargoCarriage.getCargoes().size();
		cargoCarriage.removeAllCargoes();
		int sizeAfterRemove = cargoCarriage.getCargoes().size();
		assertTrue(cargoCarriage.getCargoes().isEmpty());
		assertNotEquals(sizeAfterRemove, size);
	}

}