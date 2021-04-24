package com.yakubovskiy.design.entity.cargo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CargoTest {
	private static Cargo cargo;

	@BeforeAll
	public static void initCargo() {
		cargo = Cargo.of("Coal", CargoState.SOLID);
	}

	@Test
	public void of_success() {
		assertThat(cargo, hasProperty("name", is(equalTo("Coal"))));
		assertThat(cargo, hasProperty("state", is(equalTo(CargoState.SOLID))));
	}

	@Test
	public void of_nullArguments() {
		assertThrows(NullPointerException.class, () -> Cargo.of(null, null));
		assertThrows(NullPointerException.class, () -> Cargo.of(null, CargoState.SOLID));
		assertThrows(NullPointerException.class, () -> Cargo.of("Coal", null));
	}

	@Test
	public void setWeight_success() {
		cargo.setWeight(100);
		assertThat(cargo, hasProperty("weight", is(equalTo(100))));
	}

	@Test
	public void setWeight_incorrectWeight() {
		assertThrows(IllegalArgumentException.class, () -> cargo.setWeight(-100));
	}
}