package com.practice.poker.engine;

import com.practice.poker.model.*;
import com.practice.poker.rules.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EvaluatorTest {

    private Evaluator evaluator = new Evaluator(
            Stream.of(
                    new PairRule(), new TwoPairRule(), new DrillRule(), new StraightRule(),
                    new FlushRule(), new PokerRule(), new RoyalFlushRule()
            ).collect(Collectors.toMap(Rule::getCombination, Function.identity()))
    );

    @Test
    public void testHighCard() {
        Hand hand = new Hand(
                Arrays.asList(
                        Card.builder().rank(Ranks.TWO).color(Colors.HEARTHS).build(),
                        Card.builder().rank(Ranks.FIVE).color(Colors.HEARTHS).build(),
                        Card.builder().rank(Ranks.SIX).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.JACK).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.NINE).color(Colors.CLUBS).build()
                )
        );

        Assert.assertEquals(Combinations.HIGH_CARD, evaluator.evaluate(hand));
    }

    @Test
    public void testPair() {
        Hand hand = new Hand(
                Arrays.asList(
                        Card.builder().rank(Ranks.TWO).color(Colors.HEARTHS).build(),
                        Card.builder().rank(Ranks.TWO).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.SIX).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.JACK).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.NINE).color(Colors.CLUBS).build()
                )
        );

        Assert.assertEquals(Combinations.PAIR, evaluator.evaluate(hand));
    }

    @Test
    public void testTwoPair() {
        Hand hand = new Hand(
                Arrays.asList(
                        Card.builder().rank(Ranks.TWO).color(Colors.HEARTHS).build(),
                        Card.builder().rank(Ranks.TWO).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.SIX).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.SIX).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.NINE).color(Colors.CLUBS).build()
                )
        );

        Assert.assertEquals(Combinations.TWO_PAIR, evaluator.evaluate(hand));
    }

    @Test
    public void testDrill() {
        Hand hand = new Hand(
                Arrays.asList(
                        Card.builder().rank(Ranks.TWO).color(Colors.HEARTHS).build(),
                        Card.builder().rank(Ranks.TWO).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.TWO).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.SIX).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.NINE).color(Colors.CLUBS).build()
                )
        );

        Assert.assertEquals(Combinations.DRILL, evaluator.evaluate(hand));
    }

    @Test
    public void testStraightStartsWithAce() {
        Hand hand = new Hand(
                Arrays.asList(
                        Card.builder().rank(Ranks.ACE).color(Colors.HEARTHS).build(),
                        Card.builder().rank(Ranks.TWO).color(Colors.HEARTHS).build(),
                        Card.builder().rank(Ranks.THREE).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.FOUR).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.FIVE).color(Colors.DIAMONDS).build()
                )
        );

        Assert.assertEquals(Combinations.STRAIGHT, evaluator.evaluate(hand));
    }

    @Test
    public void testStraightEndsWithAce() {
        Hand hand = new Hand(
                Arrays.asList(
                        Card.builder().rank(Ranks.TEN).color(Colors.HEARTHS).build(),
                        Card.builder().rank(Ranks.JACK).color(Colors.HEARTHS).build(),
                        Card.builder().rank(Ranks.QUEEN).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.KING).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.ACE).color(Colors.DIAMONDS).build()
                )
        );

        Assert.assertEquals(Combinations.STRAIGHT, evaluator.evaluate(hand));
    }

    @Test
    public void testStraight() {
        Hand hand = new Hand(
                Arrays.asList(
                        Card.builder().rank(Ranks.THREE).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.FOUR).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.FIVE).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.SIX).color(Colors.HEARTHS).build(),
                        Card.builder().rank(Ranks.SEVEN).color(Colors.CLUBS).build()
                )
        );

        Assert.assertEquals(Combinations.STRAIGHT, evaluator.evaluate(hand));
    }

    @Test
    public void testFlush() {
        Hand hand = new Hand(
                Arrays.asList(
                        Card.builder().rank(Ranks.THREE).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.FOUR).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.KING).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.JACK).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.SEVEN).color(Colors.DIAMONDS).build()
                )
        );

        Assert.assertEquals(Combinations.FLUSH, evaluator.evaluate(hand));
    }

    @Test
    public void testFullHouse() {
        Hand hand = new Hand(
                Arrays.asList(
                        Card.builder().rank(Ranks.TWO).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.TWO).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.KING).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.KING).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.KING).color(Colors.CLUBS).build()
                )
        );

        Assert.assertEquals(Combinations.FULL_HOUSE, evaluator.evaluate(hand));
    }

    @Test
    public void testPoker() {
        Hand hand = new Hand(
                Arrays.asList(
                        Card.builder().rank(Ranks.TWO).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.KING).color(Colors.HEARTHS).build(),
                        Card.builder().rank(Ranks.KING).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.KING).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.KING).color(Colors.CLUBS).build()
                )
        );

        Assert.assertEquals(Combinations.POKER, evaluator.evaluate(hand));
    }

    @Test
    public void testStraightFlush() {
        Hand hand = new Hand(
                Arrays.asList(
                        Card.builder().rank(Ranks.ACE).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.TWO).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.THREE).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.FIVE).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.FOUR).color(Colors.SPADES).build()
                )
        );

        Assert.assertEquals(Combinations.STRAIGHT_FLUSH, evaluator.evaluate(hand));
    }

    @Test
    public void testRoyalFlush() {
        Hand hand = new Hand(
                Arrays.asList(
                        Card.builder().rank(Ranks.TEN).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.JACK).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.QUEEN).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.KING).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.ACE).color(Colors.SPADES).build()
                )
        );

        Assert.assertEquals(Combinations.ROYAL_FLUSH, evaluator.evaluate(hand));
    }

}
