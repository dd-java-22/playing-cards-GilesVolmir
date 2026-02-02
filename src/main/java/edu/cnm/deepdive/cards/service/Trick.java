package edu.cnm.deepdive.cards.service;

import edu.cnm.deepdive.cards.model.Card;
import edu.cnm.deepdive.cards.model.Deck;
import edu.cnm.deepdive.cards.model.Suit.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

public class Trick {

  private final Deck deck;
  private final RandomGenerator rng;

  private final List<Card> blackPile;
  private final List<Card> redPile;

  public Trick(Deck deck, RandomGenerator rng) {
    this.deck = deck;
    this.rng = rng;
    blackPile = new ArrayList<>();
    redPile = new ArrayList<>();
  }

  public void perform() {
    blackPile.clear();
    redPile.clear();
    deck.shuffle(rng);
    while (!deck.isEmpty()) {
      Card selector = deck.deal();
      if (selector.getColor().equals(Color.BLACK)) {
        blackPile.add(deck.deal());
      } else {
        redPile.add(deck.deal());
      }
    }

    // TODO: 2/2/2026 add logic to swap cards between piles.

    blackPile.sort(null);
    redPile.sort(null);

    int blackInBlackPile = 0;
    for (Card card : blackPile) {
      if (card.getColor().equals(Color.BLACK)) {
        blackInBlackPile++;
      }
    }
    int redInRedPile = 0;
    for (Card card : redPile) {
      if (card.getColor().equals(Color.RED)) {
        redInRedPile++;
      }
    }
    assert blackInBlackPile == redInRedPile;
  }

  public void reveal() {
    System.out.println("Black Pile:");
    System.out.println(blackPile);
    System.out.println("Red Pile");
    System.out.println(redPile);
  }
}
