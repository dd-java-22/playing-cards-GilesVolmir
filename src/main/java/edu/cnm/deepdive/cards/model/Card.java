package edu.cnm.deepdive.cards.model;

import edu.cnm.deepdive.cards.model.Suit.Color;

public final class Card implements Comparable<Card> {

  private final Rank rank;
  private final Suit suit;


  public Card(Rank rank, Suit suit) {
    this.rank = rank;
    this.suit = suit;
  }

  public Rank getRank() {
    return rank;
  }

  public Suit getSuit() {
    return suit;
  }

  public Color getColor() {
    return suit.color();
  }


  @Override
  public int compareTo(Card other) {
    throw new UnsupportedOperationException("Not yet implemented!");
  }
}
