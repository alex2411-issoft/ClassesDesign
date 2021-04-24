package com.yakubovskiy.design.entity.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {
	private static User user;

	@BeforeAll
	public static void initUser() {
		user = User.of("Alex", "Ivanov");
	}

	@Test
	public void setAge_success() {
		user.setAge(20);
		assertThat(user, hasProperty("age", equalTo(20)));
	}

	@Test
	public void setAge_incorrectAge() {
		assertThrows(IllegalArgumentException.class, () -> user.setAge(-20));
	}

	@Test
	public void of_success() {
		assertThat(user, hasProperty("firstName", is(equalTo("Alex"))));
		assertThat(user, hasProperty("lastName", is(equalTo("Ivanov"))));
	}

	@Test
	public void of_nullArguments() {
		assertThrows(NullPointerException.class, () -> User.of(null, null));
		assertThrows(NullPointerException.class, () -> User.of("Alex", null));
		assertThrows(NullPointerException.class, () -> User.of(null, "Ivanov"));
	}

}