package com.yakubovskiy.design.entity.train;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@EqualsAndHashCode
@ToString
public abstract class Carriage {
	private final UUID id;

	protected Carriage(UUID id) {
		this.id = id;
	}
}
