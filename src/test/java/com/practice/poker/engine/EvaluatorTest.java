package com.practice.poker.engine;

import com.practice.poker.model.*;
import com.practice.poker.rules.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
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
        // setup
        Hand hand = new Hand(
                Arrays.asList(
                        Card.builder().rank(Ranks.TWO).color(Colors.HEARTHS).build(),
                        Card.builder().rank(Ranks.FIVE).color(Colors.HEARTHS).build(),
                        Card.builder().rank(Ranks.SIX).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.JACK).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.NINE).color(Colors.CLUBS).build()
                )
        );

        // when
        HandRank actual = evaluator.evaluate(hand);

        // then
        Assert.assertEquals(Combinations.HIGH_CARD, actual.getCombination());
        Assert.assertEquals(Ranks.JACK, actual.getHighestRank());
    }

    @Test
    public void testPair() {
        // setup
        Hand hand = new Hand(
                Arrays.asList(
                        Card.builder().rank(Ranks.TWO).color(Colors.HEARTHS).build(),
                        Card.builder().rank(Ranks.TWO).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.SIX).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.JACK).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.NINE).color(Colors.CLUBS).build()
                )
        );

        // when
        HandRank actual = evaluator.evaluate(hand);

        // then
        Assert.assertEquals(Combinations.PAIR, actual.getCombination());
        Assert.assertEquals(Ranks.TWO, actual.getHighestRank());
    }

    @Test
    public void testTwoPair() {
        // setup
        Hand hand = new Hand(
                Arrays.asList(
                        Card.builder().rank(Ranks.TWO).color(Colors.HEARTHS).build(),
                        Card.builder().rank(Ranks.TWO).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.SIX).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.SIX).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.NINE).color(Colors.CLUBS).build()
                )
        );

        // when
        HandRank actual = evaluator.evaluate(hand);

        // then
        Assert.assertEquals(Combinations.TWO_PAIR, actual.getCombination());
        Assert.assertEquals(Ranks.SIX, actual.getHighestRank());
    }

    @Test
    public void testDrill() {
        // setup
        Hand hand = new Hand(
                Arrays.asList(
                        Card.builder().rank(Ranks.TWO).color(Colors.HEARTHS).build(),
                        Card.builder().rank(Ranks.TWO).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.TWO).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.SIX).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.NINE).color(Colors.CLUBS).build()
                )
        );

        // when
        HandRank actual = evaluator.evaluate(hand);

        // then
        Assert.assertEquals(Combinations.DRILL, actual.getCombination());
        Assert.assertEquals(Ranks.TWO, actual.getHighestRank());
    }

    @Test
    public void testStraightStartsWithAce() {
        // setup
        Hand hand = new Hand(
                Arrays.asList(
                        Card.builder().rank(Ranks.ACE).color(Colors.HEARTHS).build(),
                        Card.builder().rank(Ranks.TWO).color(Colors.HEARTHS).build(),
                        Card.builder().rank(Ranks.THREE).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.FOUR).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.FIVE).color(Colors.DIAMONDS).build()
                )
        );

        // when
        HandRank actual = evaluator.evaluate(hand);

        //then
        Assert.assertEquals(Combinations.STRAIGHT, actual.getCombination());
        Assert.assertEquals(Ranks.FIVE, actual.getHighestRank());
    }

    @Test
    public void testStraightEndsWithAce() {
        // setup
        Hand hand = new Hand(
                Arrays.asList(
                        Card.builder().rank(Ranks.TEN).color(Colors.HEARTHS).build(),
                        Card.builder().rank(Ranks.JACK).color(Colors.HEARTHS).build(),
                        Card.builder().rank(Ranks.QUEEN).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.KING).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.ACE).color(Colors.DIAMONDS).build()
                )
        );

        // when
        HandRank actual = evaluator.evaluate(hand);

        //then
        Assert.assertEquals(Combinations.STRAIGHT, actual.getCombination());
        Assert.assertEquals(Ranks.ACE, actual.getHighestRank());
    }

    @Test
    public void testStraight() {
        // setup
        Hand hand = new Hand(
                Arrays.asList(
                        Card.builder().rank(Ranks.THREE).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.FOUR).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.FIVE).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.SIX).color(Colors.HEARTHS).build(),
                        Card.builder().rank(Ranks.SEVEN).color(Colors.CLUBS).build()
                )
        );

        // when
        HandRank actual = evaluator.evaluate(hand);

        //then
        Assert.assertEquals(Combinations.STRAIGHT, actual.getCombination());
        Assert.assertEquals(Ranks.SEVEN, actual.getHighestRank());
    }

    @Test
    public void testFlush() {
        // setup
        Hand hand = new Hand(
                Arrays.asList(
                        Card.builder().rank(Ranks.THREE).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.FOUR).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.KING).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.JACK).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.SEVEN).color(Colors.DIAMONDS).build()
                )
        );

        // when
        HandRank actual = evaluator.evaluate(hand);

        // then
        Assert.assertEquals(Combinations.FLUSH, actual.getCombination());
        Assert.assertEquals(Ranks.KING, actual.getHighestRank());
    }

    @Test
    public void testFullHouse() {
        // setup
        Hand hand = new Hand(
                Arrays.asList(
                        Card.builder().rank(Ranks.TWO).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.TWO).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.KING).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.KING).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.KING).color(Colors.CLUBS).build()
                )
        );

        // when
        HandRank actual = evaluator.evaluate(hand);

        // then
        Assert.assertEquals(Combinations.FULL_HOUSE, actual.getCombination());
        Assert.assertEquals(Ranks.KING, actual.getHighestRank());
    }

    @Test
    public void testPoker() {
        // setup
        Hand hand = new Hand(
                Arrays.asList(
                        Card.builder().rank(Ranks.ACE).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.KING).color(Colors.HEARTHS).build(),
                        Card.builder().rank(Ranks.KING).color(Colors.DIAMONDS).build(),
                        Card.builder().rank(Ranks.KING).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.KING).color(Colors.CLUBS).build()
                )
        );

        // when
        HandRank actual = evaluator.evaluate(hand);

        // then
        Assert.assertEquals(Combinations.POKER, actual.getCombination());
        Assert.assertEquals(Ranks.KING, actual.getHighestRank());
    }

    @Test
    public void testStraightFlush() {
        // setup
        Hand hand = new Hand(
                Arrays.asList(
                        Card.builder().rank(Ranks.ACE).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.TWO).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.THREE).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.FIVE).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.FOUR).color(Colors.SPADES).build()
                )
        );

        // when
        HandRank actual = evaluator.evaluate(hand);

        // then
        Assert.assertEquals(Combinations.STRAIGHT_FLUSH, actual.getCombination());
        Assert.assertEquals(Ranks.FIVE, actual.getHighestRank());
    }

    @Test
    public void testRoyalFlush() {
        // setup
        Hand hand = new Hand(
                Arrays.asList(
                        Card.builder().rank(Ranks.TEN).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.JACK).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.QUEEN).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.KING).color(Colors.SPADES).build(),
                        Card.builder().rank(Ranks.ACE).color(Colors.SPADES).build()
                )
        );

        // when
        HandRank actual = evaluator.evaluate(hand);

        // then
        Assert.assertEquals(Combinations.ROYAL_FLUSH, actual.getCombination());
        Assert.assertEquals(Ranks.ACE, actual.getHighestRank());
    }

}
