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

  public record TrickResult(List<Card> blackPile, List<Card> redPile) {

    @Override
    public String toString() {
      long redInRedCount = redPile.stream()
          .filter((card) -> {
            return card.getColor() == Color.RED;
          })
          .count();
      long blackInBlackCount = blackPile.stream().
          filter((card) -> {
            return card.getColor() == Color.BLACK;
          })
          .count();
      String redInRedString = "Number of Red Cards in the Red Pile: %d".formatted(redInRedCount);
      String blackInBlackString = "Number of Black Cards in the Black Pile: %d".formatted(blackInBlackCount);
      String pileContents = "Red Pile: %s%nBlackPile: %s".formatted(redPile,blackPile);
      return "%s%n%s%n%s".formatted(redInRedString, blackInBlackString, pileContents);
    }
  }

  public Trick(Deck deck, RandomGenerator rng) {
    this.deck = deck;
    this.rng = rng;
    blackPile = new ArrayList<>();
    redPile = new ArrayList<>();
  }

  public TrickResult perform(boolean swap) {
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

    if (swap) {
      int maxSwap = Math.min(blackPile.size(), redPile.size());
      int nSwaps = rng.nextInt(maxSwap) + 1;  // random from {1, 2, ..., maxSwap}
      for (int i = 0; i < nSwaps; i++) {
        redPile.add(blackPile.removeFirst());
        blackPile.add(redPile.removeFirst());
      }
    }

    return new TrickResult(blackPile, redPile);
//    blackPile.sort(
//        Comparator.comparing(Card::getColor).thenComparing(Card::compareTo));
//    redPile.sort(
//        Comparator.comparing(Card::getColor).reversed().thenComparing(Card::compareTo));
  }


}