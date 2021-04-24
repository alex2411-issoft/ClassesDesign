package com.yakubovskiy.design.entity.train;

import com.yakubovskiy.design.entity.user.TrainDriver;
import com.yakubovskiy.design.entity.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class TrainTest {
	private static Train train;
	private static Locomotive locomotive;
	private static final boolean HAS_LICENSE = true;

	@BeforeAll
	public static void initTrain() {
		User user = User.of("Alex", "Ivanov");
		user.setAge(18);
		TrainDriver trainDriver = TrainDriver.of(user, HAS_LICENSE);
		locomotive = Locomotive.of(trainDriver);
		train = Train.of(locomotive);
	}

	@AfterEach
	public void removeCarriages() {
		train.removeAllCarriages();
		train.setNextCarriage(locomotive);
	}

	@Test
	public void of_success() {
		assertThat(train, hasProperty("number", is(notNullValue())));
	}

	@Test
	public void of_nullArgument() {
		assertThrows(NullPointerException.class, () -> Train.of(null));
	}

	@Test
	public void setNextCarriage_success() {
		int size = train.getTrainSize();

		PassengerCarriage passengerCarriage = PassengerCarriage.of();
		train.setNextCarriage(passengerCarriage);
		int sizeAfterAdding = train.getTrainSize();

		assertTrue(train.hasCarriage());
		assertTrue(sizeAfterAdding > size);
	}

	@Test
	public void setNextCarriage_nullArgument() {
		assertThrows(NullPointerException.class, () -> train.setNextCarriage(null));
	}

	@Test
	public void removeCarriage_success() {
		PassengerCarriage passengerCarriage = PassengerCarriage.of();
		train.setNextCarriage(passengerCarriage);
		int size = train.getTrainSize();

		train.removeCarriage(passengerCarriage);
		int sizeAfterRemoving = train.getTrainSize();

		assertTrue(size > sizeAfterRemoving);
		assertTrue(train.hasCarriage());
	}

	@Test
	public void removeCarriage_nullArgument() {
		assertThrows(NullPointerException.class, () -> train.removeCarriage(null));
	}

	@Test
	public void removeAllCarriages_success() {
		int size = train.getTrainSize();
		train.removeAllCarriages();
		int sizeAfterRemoving = train.getTrainSize();

		assertFalse(train.hasCarriage());
		assertTrue(size > sizeAfterRemoving);
	}


}