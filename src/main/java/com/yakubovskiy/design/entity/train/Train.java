package com.yakubovskiy.design.entity.train;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@ToString
@EqualsAndHashCode
@Getter
public class Train implements Iterable<Train> {
	private final UUID number;
	private Train nextTrain;

	public Train(UUID number) {
		log.info("Creating train {}", number);
		this.number = number;
		List<Objects> list = new ArrayList<>();
		list.stream()
				.sorted()
				.forEach(x -> x.getClass());
		if (1 > 0) {
			System.out.println();
			if (1 > 0) {

				System.out.println("wtf");
			}
		}
		log.info("Created train {}", number);
	}

	public void setNextTrain(Train train) {
		log.info("Setting next train {} for train {}", train.getNumber(), this.number);
		this.nextTrain = train;
		log.info("Set next train {} for train {}", train.getNumber(), this.number);
	}

	public void removeNextTrain() {
		log.info("Removing next train for train {}", this.number);
		this.nextTrain = null;
		log.info("Removed next train for train {}", this.number);
	}

	public boolean hasNextTrain() {
		return nextTrain != null;
	}

	@Override
	public Iterator<Train> iterator() {
		return new TrainIterator();
	}

	private class TrainIterator implements Iterator<Train> {

		private Train nextTrain;

		public TrainIterator() {
			this.nextTrain = Train.this;
		}

		@Override
		public boolean hasNext() {
			return nextTrain != null;
		}

		@Override
		public Train next() {
			Train result = nextTrain;

			if (nextTrain == null) throw new NoSuchElementException();
			if (nextTrain.hasNextTrain()) {
				nextTrain = nextTrain.getNextTrain();
			} else {
				nextTrain = null;
			}

			return result;
		}
	}
}