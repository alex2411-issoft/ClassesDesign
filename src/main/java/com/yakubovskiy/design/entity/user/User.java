package com.yakubovskiy.design.entity.user;

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
public class User {
	private final UUID id;
	private final String firstName;
	private final String lastName;
	private int age;

	private User(UUID id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		log.info("User {} was created", id);
	}

	public static User of(@NonNull final String firstName, @NonNull final String lastName) {
		UUID id = UUID.randomUUID();
		return new User(id, firstName, lastName);
	}

	public void setAge(int age) {
		checkArgument(age > 0, "Age less than 0");
		this.age = age;
		log.info("Age of {} for user {} was set", this.age, this.id);
	}
}
