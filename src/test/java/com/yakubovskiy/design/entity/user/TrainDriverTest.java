package com.yakubovskiy.design.entity.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TrainDriverTest {
	private static final boolean HAS_LICENSE = true;
	private static final boolean NO_LICENSE = false;
	private static User user;

	@BeforeAll
	public static void initUser() {
		user = User.of("Alex", "Ivanov");
	}

	@Test
	public void of_success() {
		user.setAge(18);
		TrainDriver trainDriver = TrainDriver.of(user, HAS_LICENSE);
		assertThat(trainDriver, hasProperty("user", notNullValue()));
		assertThat(trainDriver.getUser()
				.getAge(), is(equalTo(18)));
	}

	@Test
	public void of_nullArguments() {
		assertThrows(NullPointerException.class, () -> TrainDriver.of(null, NO_LICENSE));
	}

	@Test
	public void validDriver_noLicense() {
		user.setAge(18);
		assertThrows(IllegalArgumentException.class, () -> TrainDriver.of(user, NO_LICENSE));
	}

	@Test
	public void validDriver_underageUser() {
		user.setAge(10);
		assertThrows(IllegalArgumentException.class, () -> TrainDriver.of(user, HAS_LICENSE));
	}

}