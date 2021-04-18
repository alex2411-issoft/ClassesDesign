package com.yakubovskiy.design.entity.user;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import static com.google.common.base.Preconditions.checkArgument;

@Slf4j
@ToString
@EqualsAndHashCode
public class TrainDriver {
	private static final int AGE_TO_DRIVE = 18;
	private final User user;

	private TrainDriver(User user) {
		this.user = user;
		log.info("Train driver {} was created", user);
	}

	public static TrainDriver of(@NonNull final User user, final boolean hasLicense) {
		checkUser(user, hasLicense);
		return new TrainDriver(user);
	}

	private static void checkUser(final User user, final boolean hasLicense) {
		checkArgument(hasLicense, "User " + user.getId() + " doesn't have a license for drive");
		checkArgument(user.getAge() >= AGE_TO_DRIVE, "The driver must be over 18 years old");
		log.info("User {} for train driver was checked", user.getId());
	}


}
