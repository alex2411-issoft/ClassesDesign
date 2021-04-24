package com.yakubovskiy.design.entity.train;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@EqualsAndHashCode(of = "number")
@Getter
public class Train {
	private final UUID number;
	private Node<Carriage> head;

	private Train(UUID number, Locomotive locomotive) {
		this.number = number;
		this.head = new Node<>(locomotive);
		log.info("Train {} was created", number);
	}

	public static Train of(@NonNull final Locomotive locomotive) {
		UUID number = UUID.randomUUID();
		return new Train(number, locomotive);
	}

	public void setNextCarriage(@NonNull final Carriage carriage) {
		if (head == null) {
			head = new Node<>(carriage);
			log.info("Head carriage was set for train {}", this.number);
			return;
		}

		Node<Carriage> currentCarriage = head;
		while (currentCarriage.next != null) {
			currentCarriage = currentCarriage.next;
		}

		currentCarriage.next = new Node<>(carriage);
		log.info("Next carriage was set for train {}", this.number);
	}

	public int getTrainSize() {
		if(head==null) {
			return 0;
		}

		int result = 1;
		Node<Carriage> currentCarriage = head;
		while (currentCarriage.next != null) {
			++result;
			currentCarriage = currentCarriage.next;
		}

		return result;
	}

	public void removeCarriage(@NonNull final Carriage carriage) {
		if (head == null) {
			log.info("There are no carriages on the train");
			return;
		}

		if (head.value == carriage) {
			head = null;
			log.info("Head carriage {} of train {} was removed", carriage.getId(), this.getNumber());
			return;
		}

		Node<Carriage> currentCarriage = head;
		while (currentCarriage.next != null) {
			if (currentCarriage.next.value == carriage) {
				currentCarriage.next = null;
				log.info("Carriage {} was removed from train {}", carriage.getId(), this.getNumber());
				return;
			}
			currentCarriage = currentCarriage.next;
		}
		log.info("Next carriage was removed from train {}", this.number);
	}

	public void removeAllCarriages() {
		head = null;
	}

	public boolean hasCarriage() {
		return head != null;
	}


	@Override
	public String toString() {
		return "Train{" +
				"number=" + number +
				", head=" + head +
				" }'";
	}

	private static class Node<Carriage> {
		private final Carriage value;
		private Node<Carriage> next;

		public Node(Carriage value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "{" +
					"value=" + value +
					", next=" + next +
					'}';
		}
	}
}