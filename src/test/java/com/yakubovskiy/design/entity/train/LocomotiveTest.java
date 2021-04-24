package com.yakubovskiy.design.entity.train;

import com.yakubovskiy.design.entity.user.TrainDriver;
import com.yakubovskiy.design.entity.user.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LocomotiveTest {
	private static Locomotive locomotive;
	private static final boolean HAS_LICENSE = true;

	@BeforeAll
	public static void initLocomotive() {
		User user = User.of("Alex", "Ivanov");
		user.setAge(18);
		TrainDriver trainDriver = TrainDriver.of(user, HAS_LICENSE);
		locomotive = Locomotive.of(trainDriver);
	}

	@Test
	public void of_success() {
		assertThat(locomotive, is(notNullValue()));
	}

	@Test
	public void of_nullArgument() {
		assertThrows(NullPointerException.class, () -> Locomotive.of(null));
	}

	@Test
	public void setDriver_success() {
		User user = User.of("Ivan", "Ivanov");
		user.setAge(20);
		TrainDriver trainDriver = TrainDriver.of(user, HAS_LICENSE);
		locomotive.setDriver(trainDriver);
		assertEquals(locomotive.getDriver(), trainDriver);
	}

	@Test
	public void setDriver_nullArgument() {
		assertThrows(NullPointerException.class, () -> locomotive.setDriver(null));
	}

}