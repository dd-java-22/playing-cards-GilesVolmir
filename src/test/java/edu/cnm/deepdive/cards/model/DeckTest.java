package edu.cnm.deepdive.cards.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.random.RandomGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

class DeckTest {

  static RandomGenerator rng;
  @BeforeAll
  static void setRng() {
    rng = RandomGenerator.getDefault();
  }

  @Test
  public void sort() {
    Deck d = new Deck();
    try {
      d.shuffle(rng);
    } catch (Exception e) {
      fail("shuffle threw exception");
    }
    d.sort(null);
    Card last = d.deal();
    while (!d.isEmpty()) {
      System.out.println(last);
      Card current = d.deal();
      System.out.println(current);
      assertTrue(last.compareTo(current) < 0);
      last = current;
    }
  }

  @Test
  public void size_normalSize() {
    Deck d = new Deck();
    assertEquals(52, d.size());
  }

  @Test
  public void size_decreases() {
    Deck d = new Deck();
    int preSize = d.size();
    d.deal();
    int postSize = d.size();
    assertTrue(preSize > postSize);
  }

  @Test
  public void deal_drawOutException() {
    fail("NYI");
  }
}