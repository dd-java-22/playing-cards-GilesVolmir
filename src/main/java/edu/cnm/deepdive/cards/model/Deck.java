package edu.cnm.deepdive.cards.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Deck {

  private final List<Card> cards;
  private final Iterator<Card> dealer;


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
    dealer = null;
  }
}
