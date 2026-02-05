package edu.cnm.deepdive.cards.service;

import edu.cnm.deepdive.cards.model.Card;
import edu.cnm.deepdive.cards.model.Deck;
import edu.cnm.deepdive.cards.model.Suit.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
  }

  public int swap() {
    int maxSwap = Math.min(blackPile.size(), redPile.size());
    int nSwaps = rng.nextInt(maxSwap) + 1;  // random from {1, 2, ..., maxSwap}
    for (int i = 0; i < nSwaps; i++) {
      redPile.add(blackPile.removeFirst());
      blackPile.add(redPile.removeFirst());
    }
    return nSwaps;
  }

  public Map<Color, List<Card>> getResult() {
    return Map.of(
        Color.BLACK, Collections.unmodifiableList(blackPile),
        Color.RED, Collections.unmodifiableList(redPile)
    );
  }

}

//    blackPile.sort(
//        Comparator.comparing(Card::getColor).thenComparing(Card::compareTo));
//    redPile.sort(
//        Comparator.comparing(Card::getColor).reversed().thenComparing(Card::compareTo));