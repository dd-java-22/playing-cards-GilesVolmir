package edu.cnm.deepdive.cards.model;

import edu.cnm.deepdive.cards.model.Suit.Color;

public final class Card implements Comparable<Card> {

  private final Rank rank;
  private final Suit suit;
  private final String strRepr;


  public Card(Rank rank, Suit suit) {
    this.rank = rank;
    this.suit = suit;
    this.strRepr = "%s of %s".formatted(rank, suit);
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
    int result = this.suit.compareTo(other.suit);
    if (result == 0) {
      result = this.rank.compareTo(other.rank);
    }
    return result;
  }

  @Override
  public String toString() {
    return strRepr;
  }
}
