package com.yakubovskiy.design.entity.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;

@Slf4j
@ToString
@EqualsAndHashCode(of = "user")
@Getter
public class TrainDriver {
	private static final int AGE_TO_DRIVE = 18;
	private final UUID id;
	private final User user;

	private TrainDriver(UUID id, User user) {
		this.id = id;
		this.user = user;
		log.info("Train driver {} was created", id);
	}

	public static TrainDriver of(@NonNull final User user, final boolean hasLicense) {
		validDriver(user, hasLicense);
		UUID id = UUID.randomUUID();
		return new TrainDriver(id, user);
	}

	private static void validDriver(final User user, final boolean hasLicense) {
		checkArgument(hasLicense, "User " + user.getId() + " doesn't have a license for drive");
		checkArgument(user.getAge() >= AGE_TO_DRIVE, "The driver must be over 18 years old");
		log.info("User {} for train driver was checked", user.getId());
	}

}
