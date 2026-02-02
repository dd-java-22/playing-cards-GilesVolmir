package edu.cnm.deepdive.cards;

import edu.cnm.deepdive.cards.model.Deck;
import edu.cnm.deepdive.cards.service.Trick;
import java.util.random.RandomGenerator;

public class Main {

  public static void main(String[] args) {
    Deck deck = new Deck();
    RandomGenerator rng = RandomGenerator.getDefault();
    Trick trick = new Trick(deck, rng);
    trick.perform();
    trick.reveal();
  }
}
