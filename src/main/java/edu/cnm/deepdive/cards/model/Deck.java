package edu.cnm.deepdive.cards.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.random.RandomGenerator;

public class Deck {

  private final List<Card> cards;

  private Iterator<Card> dealer;
  private int remaining;


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
    remaining = cards.size();
  }

  public void shuffle(RandomGenerator rng) {
    Collections.shuffle(cards, rng);
    dealer = cards.iterator();
    remaining = cards.size();
  }

  public void sort(Comparator<Card> comparator) {
    cards.sort(comparator);
    dealer = cards.iterator();
    remaining = cards.size();
  }

  /**
   *
   * @return
   * @throws NoSuchElementException If the deck is empty.
   */
  public Card deal() throws NoSuchElementException {
      Card card = dealer.next();
      remaining--;
      return card;
  }

  public boolean isEmpty() {
    return !dealer.hasNext();
  }

  public int size() {
    return remaining;
  }

  @Override
  public String toString() {
    return cards.toString();
  }
}
