package edu.cnm.deepdive.cards.view;

import edu.cnm.deepdive.cards.model.Deck;
import edu.cnm.deepdive.cards.service.Trick;
import edu.cnm.deepdive.cards.service.Trick.TrickResult;
import java.util.random.RandomGenerator;

public class View {

  public void perform() {
    System.out.println("Are you Ready For a Card Trick?");
    Deck deck = new Deck();
    System.out.println("Here's the deck we're starting with:");
    System.out.println(deck);
    RandomGenerator rng = RandomGenerator.getDefault();
    Trick trick = new Trick(deck, rng);
    TrickResult result = trick.perform(true);
    System.out.println("Here's the result:");
    System.out.println(result);
  }
}
