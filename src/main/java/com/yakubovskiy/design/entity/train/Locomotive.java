package com.yakubovskiy.design.entity.train;

import com.yakubovskiy.design.entity.user.TrainDriver;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@Getter
public class Locomotive extends Carriage {
	private TrainDriver driver;

	private Locomotive(UUID id, TrainDriver driver) {
		super(id);
		this.driver = driver;
		log.info("Locomotive {} was created", id);
	}

	public static Locomotive of(@NonNull final TrainDriver driver) {
		UUID id = UUID.randomUUID();
		return new Locomotive(id, driver);
	}

	public void setDriver(@NonNull final TrainDriver driver) {
		this.driver = driver;
		log.info("Driver {} for locomotive {} was set", driver.getId(), this.getId());
	}

	@Override
	public String toString() {
		return "Locomotive{" +
				"id = " + this.getId() +
				", driver = " + driver + "} ";
	}
}
