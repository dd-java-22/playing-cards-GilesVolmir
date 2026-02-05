package edu.cnm.deepdive.cards.view;

import edu.cnm.deepdive.cards.model.Card;
import edu.cnm.deepdive.cards.model.Deck;
import edu.cnm.deepdive.cards.model.Suit.Color;
import edu.cnm.deepdive.cards.service.Trick;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.random.RandomGenerator;

public class View {

  private static final Comparator<Card> BLACK_FIRST_COMPARATOR =
      Comparator.comparing(Card::getColor)
          .thenComparing(Card::compareTo);
  private static final Comparator<Card> RED_FIRST_COMPARATOR =
      Comparator.comparing(Card::getColor,Comparator.reverseOrder())
          .thenComparing(Comparator.naturalOrder());

  public void perform() {
    System.out.println("Are you Ready For a Card Trick?");
    Deck deck = new Deck();
    System.out.println("Here's the deck we're starting with:");
    System.out.println(deck);
    RandomGenerator rng = RandomGenerator.getDefault();
    Trick trick = new Trick(deck, rng);
    trick.perform();
    System.out.println("Now we'll swap some cards");
    int nSwaps = trick.swap();
    System.out.printf("I've swapped %d card%s between our piles%n", nSwaps, nSwaps==1?"":"s");
    Map<Color, List<Card>> result = trick.getResult();
    TrickResult representation = new TrickResult(result.get(Color.BLACK), result.get(Color.RED));
    System.out.println("Here's the result:");
    System.out.println(result);
  }

  private record TrickResult(List<Card> blackPile, List<Card> redPile) {

    @Override
    public String toString() {
      long redInRedCount = redPile
          .stream()
          .filter((card) -> {
            return card.getColor() == Color.RED;
          })
          .count();
      long blackInBlackCount = blackPile
          .stream()
          .filter((card) -> {
            return card.getColor() == Color.BLACK;
          })
          .count();
      String redInRedString = "Number of Red Cards in the Red Pile: %d".formatted(redInRedCount);
      String blackInBlackString = "Number of Black Cards in the Black Pile: %d".formatted(blackInBlackCount);
      String pileContents = "Red Pile: %s%nBlackPile: %s".formatted(redPile,blackPile);
      return "%s%n%s%n%s".formatted(redInRedString, blackInBlackString, pileContents);
    }
  }
}
