package edu.cnm.deepdive.cards.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.random.RandomGenerator;

public class Deck {

  private final List<Card> cards;

  private Iterator<Card> dealer;


  public Deck() {
    Suit[] suits = Suit.values();
    Rank[] ranks = Rank.values();
    cards = new ArrayList<>(suits.length * ranks.length);
    for (Suit suit : suits) {
      for (Rank rank : ranks) {
        Card card = new Card(rank, suit);
        cards.add(card);
      }
    }
    dealer = cards.iterator();
  }

  public void shuffle(RandomGenerator rng) {
    Collections.shuffle(cards, rng);
    dealer = cards.iterator();
  }

  public void sort(Comparator<Card> comparator) {
    cards.sort(comparator);
  }
}
