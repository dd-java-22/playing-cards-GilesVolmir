package edu.cnm.deepdive.cards;

import edu.cnm.deepdive.cards.model.Card;
import edu.cnm.deepdive.cards.model.Deck;
import edu.cnm.deepdive.cards.model.Suit.Color;
import edu.cnm.deepdive.cards.service.Trick;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;

public class View {

  private static final Comparator<Card> BLACK_FIRST_COMPARATOR =
      Comparator.comparing(Card::getColor)
          .thenComparing(Card::compareTo);
  private static final Comparator<Card> RED_FIRST_COMPARATOR =
      Comparator.comparing(Card::getColor,Comparator.reverseOrder())
          .thenComparing(Comparator.naturalOrder());
  private static final String BUNDLE_BASE_NAME = "card";
  private static final String COMPOSITION_FORMAT_KEY = "composition-format";

  private static final ResourceBundle bundle;

  static {
    bundle = ResourceBundle.getBundle(BUNDLE_BASE_NAME);
  }


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
    TrickResult representation = new TrickResult(trick.getResult());
    System.out.println("Here's the result:");
    System.out.println(representation);
  }

  private record TrickResult(List<Card> blackPile, List<Card> redPile) {

    TrickResult(Map<Color,List<Card>> piles) {
      List<Card> sortedBlackPile = piles
          .get(Color.BLACK)
          .stream()
          .sorted(BLACK_FIRST_COMPARATOR)
          .toList();
      List<Card> sortedRedPile = piles
          .get(Color.RED)
          .stream()
          .sorted(RED_FIRST_COMPARATOR)
          .toList();
      this(sortedBlackPile,sortedRedPile);
    }

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
      String compositionFormat = bundle.getString(COMPOSITION_FORMAT_KEY);
      String redPileRepr = redPile
          .stream()
          .map((card -> {
            return compositionFormat.formatted(
                bundle.getString(card.getRank().name()),
                bundle.getString(card.getSuit().name()));
          }))
          .collect(Collectors.joining(", "));
      String blackPileRepr = blackPile
          .stream()
          .map((card -> {
            return compositionFormat.formatted(
                bundle.getString(card.getRank().name()),
                bundle.getString(card.getSuit().name()));
          }))
          .collect(Collectors.joining(", "));
      String redInRedString = "Number of Red Cards in the Red Pile: %d".formatted(redInRedCount);
      String blackInBlackString = "Number of Black Cards in the Black Pile: %d".formatted(blackInBlackCount);
      String pileContents = "Red Pile: %s%nBlackPile: %s".formatted(redPile,blackPile);
      return "%s%n%s%n%s%n%s".formatted(redInRedString, blackInBlackString, redPileRepr, blackPileRepr);
    }
  }
}
