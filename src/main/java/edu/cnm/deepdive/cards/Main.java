package edu.cnm.deepdive.cards;

import edu.cnm.deepdive.cards.model.Deck;
import java.util.random.RandomGenerator;

public class Main {

  public static void main(String[] args) {
    Deck deck = new Deck();
    System.out.println(deck);
    System.out.println(deck.deal());

    deck.shuffle(RandomGenerator.getDefault());
    System.out.println(deck);
    System.out.println(deck.deal());
    System.out.println(deck.deal());
    System.out.println(deck.deal());

    deck.sort(null);
    System.out.println(deck);
  }
}
