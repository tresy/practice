package com.practice.poker.engine;

import com.practice.poker.model.*;
import com.practice.poker.rules.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@DisplayName("Testing poker hand evaluation")
public class EvaluatorTest {

    private Evaluator evaluator = new Evaluator(
            Stream.of(
                    new PairRule(), new TwoPairRule(), new DrillRule(), new StraightRule(),
                    new FlushRule(), new PokerRule(), new RoyalFlushRule()
            ).collect(Collectors.toMap(Rule::getCombination, Function.identity()))
    );

    @ParameterizedTest(name = "{index}# {0}")
    @MethodSource(value = "getParameters")
    public void testHandEvaluation(TestCase testCase) {
        // Setup
        Hand hand = testCase.getInput();
        HandRank expected = testCase.getOutput();

        // when
        HandRank actual = evaluator.evaluate(hand);

        // then
        Assert.assertEquals(expected.getCombination(), actual.getCombination());
        Assert.assertEquals(expected.getHighestRank(), actual.getHighestRank());
    }

    public static TestCase[] getParameters() {
        return new TestCase[] {
                // HIGH CARD
                new TestCase(
                        new Hand(
                                Arrays.asList(
                                        Card.builder().rank(Ranks.TWO).color(Colors.HEARTHS).build(),
                                        Card.builder().rank(Ranks.FIVE).color(Colors.HEARTHS).build(),
                                        Card.builder().rank(Ranks.SIX).color(Colors.SPADES).build(),
                                        Card.builder().rank(Ranks.JACK).color(Colors.DIAMONDS).build(),
                                        Card.builder().rank(Ranks.NINE).color(Colors.CLUBS).build()
                                )
                        ),
                        HandRank.builder().combination(Combinations.HIGH_CARD).highestRank(Ranks.JACK).build(),
                        "Testing High Card"
                ),
                // PAIR
                new TestCase(
                        new Hand(
                                Arrays.asList(
                                        Card.builder().rank(Ranks.TWO).color(Colors.HEARTHS).build(),
                                        Card.builder().rank(Ranks.TWO).color(Colors.SPADES).build(),
                                        Card.builder().rank(Ranks.SIX).color(Colors.SPADES).build(),
                                        Card.builder().rank(Ranks.JACK).color(Colors.DIAMONDS).build(),
                                        Card.builder().rank(Ranks.NINE).color(Colors.CLUBS).build()
                                )
                        ),
                        HandRank.builder().combination(Combinations.PAIR).highestRank(Ranks.TWO).build(),
                        "Testing Pair"
                ),
                // TWO PAIR
                new TestCase(
                        new Hand(
                                Arrays.asList(
                                        Card.builder().rank(Ranks.TWO).color(Colors.HEARTHS).build(),
                                        Card.builder().rank(Ranks.TWO).color(Colors.SPADES).build(),
                                        Card.builder().rank(Ranks.SIX).color(Colors.SPADES).build(),
                                        Card.builder().rank(Ranks.SIX).color(Colors.DIAMONDS).build(),
                                        Card.builder().rank(Ranks.NINE).color(Colors.CLUBS).build()
                                )
                        ),
                        HandRank.builder().combination(Combinations.TWO_PAIR).highestRank(Ranks.SIX).build(),
                        "Testing Two Pair"
                ),
                // DRILL
                new TestCase(
                        new Hand(
                                Arrays.asList(
                                        Card.builder().rank(Ranks.TWO).color(Colors.HEARTHS).build(),
                                        Card.builder().rank(Ranks.TWO).color(Colors.SPADES).build(),
                                        Card.builder().rank(Ranks.TWO).color(Colors.DIAMONDS).build(),
                                        Card.builder().rank(Ranks.SIX).color(Colors.DIAMONDS).build(),
                                        Card.builder().rank(Ranks.NINE).color(Colors.CLUBS).build()
                                )
                        ),
                        HandRank.builder().combination(Combinations.DRILL).highestRank(Ranks.TWO).build(),
                        "Testing Drill"
                ),
                // STRAIGHT STARTS WITH ACE
                new TestCase(
                        new Hand(
                                Arrays.asList(
                                        Card.builder().rank(Ranks.ACE).color(Colors.HEARTHS).build(),
                                        Card.builder().rank(Ranks.TWO).color(Colors.HEARTHS).build(),
                                        Card.builder().rank(Ranks.THREE).color(Colors.SPADES).build(),
                                        Card.builder().rank(Ranks.FOUR).color(Colors.DIAMONDS).build(),
                                        Card.builder().rank(Ranks.FIVE).color(Colors.DIAMONDS).build()
                                )
                        ),
                        HandRank.builder().combination(Combinations.STRAIGHT).highestRank(Ranks.FIVE).build(),
                        "Testing Straight starts with an Ace"
                ),
                // STRAIGHT ENDS WITH ACE
                new TestCase(
                        new Hand(
                                Arrays.asList(
                                        Card.builder().rank(Ranks.TEN).color(Colors.HEARTHS).build(),
                                        Card.builder().rank(Ranks.JACK).color(Colors.HEARTHS).build(),
                                        Card.builder().rank(Ranks.QUEEN).color(Colors.SPADES).build(),
                                        Card.builder().rank(Ranks.KING).color(Colors.DIAMONDS).build(),
                                        Card.builder().rank(Ranks.ACE).color(Colors.DIAMONDS).build()
                                )
                        ),
                        HandRank.builder().combination(Combinations.STRAIGHT).highestRank(Ranks.ACE).build(),
                        "Testing Straight ends with an Ace"
                ),
                // STRAIGHT
                new TestCase(
                        new Hand(
                                Arrays.asList(
                                        Card.builder().rank(Ranks.THREE).color(Colors.SPADES).build(),
                                        Card.builder().rank(Ranks.FOUR).color(Colors.DIAMONDS).build(),
                                        Card.builder().rank(Ranks.FIVE).color(Colors.DIAMONDS).build(),
                                        Card.builder().rank(Ranks.SIX).color(Colors.HEARTHS).build(),
                                        Card.builder().rank(Ranks.SEVEN).color(Colors.CLUBS).build()
                                )
                        ),
                        HandRank.builder().combination(Combinations.STRAIGHT).highestRank(Ranks.SEVEN).build(),
                        "Testing Straight"
                ),
                // FLUSH
                new TestCase(
                        new Hand(
                                Arrays.asList(
                                        Card.builder().rank(Ranks.THREE).color(Colors.DIAMONDS).build(),
                                        Card.builder().rank(Ranks.FOUR).color(Colors.DIAMONDS).build(),
                                        Card.builder().rank(Ranks.KING).color(Colors.DIAMONDS).build(),
                                        Card.builder().rank(Ranks.JACK).color(Colors.DIAMONDS).build(),
                                        Card.builder().rank(Ranks.SEVEN).color(Colors.DIAMONDS).build()
                                )
                        ),
                        HandRank.builder().combination(Combinations.FLUSH).highestRank(Ranks.KING).build(),
                        "Testing Flush"
                ),
                // FULL HOUSE
                new TestCase(
                        new Hand(
                                Arrays.asList(
                                        Card.builder().rank(Ranks.TWO).color(Colors.SPADES).build(),
                                        Card.builder().rank(Ranks.TWO).color(Colors.DIAMONDS).build(),
                                        Card.builder().rank(Ranks.KING).color(Colors.DIAMONDS).build(),
                                        Card.builder().rank(Ranks.KING).color(Colors.SPADES).build(),
                                        Card.builder().rank(Ranks.KING).color(Colors.CLUBS).build()
                                )
                        ),
                        HandRank.builder().combination(Combinations.FULL_HOUSE).highestRank(Ranks.KING).build(),
                        "Testing Full House"
                ),
                // POKER
                new TestCase(
                        new Hand(
                                Arrays.asList(
                                        Card.builder().rank(Ranks.ACE).color(Colors.SPADES).build(),
                                        Card.builder().rank(Ranks.KING).color(Colors.HEARTHS).build(),
                                        Card.builder().rank(Ranks.KING).color(Colors.DIAMONDS).build(),
                                        Card.builder().rank(Ranks.KING).color(Colors.SPADES).build(),
                                        Card.builder().rank(Ranks.KING).color(Colors.CLUBS).build()
                                )
                        ),
                        HandRank.builder().combination(Combinations.POKER).highestRank(Ranks.KING).build(),
                        "Testing Poker"
                ),
                // STRAIGHT FLUSH
                new TestCase(
                        new Hand(
                                Arrays.asList(
                                        Card.builder().rank(Ranks.ACE).color(Colors.SPADES).build(),
                                        Card.builder().rank(Ranks.TWO).color(Colors.SPADES).build(),
                                        Card.builder().rank(Ranks.THREE).color(Colors.SPADES).build(),
                                        Card.builder().rank(Ranks.FIVE).color(Colors.SPADES).build(),
                                        Card.builder().rank(Ranks.FOUR).color(Colors.SPADES).build()
                                )
                        ),
                        HandRank.builder().combination(Combinations.STRAIGHT_FLUSH).highestRank(Ranks.FIVE).build(),
                        "Testing Straight Flush"
                ),
                // ROYAL FLUSH
                new TestCase(
                        new Hand(
                                Arrays.asList(
                                        Card.builder().rank(Ranks.TEN).color(Colors.SPADES).build(),
                                        Card.builder().rank(Ranks.JACK).color(Colors.SPADES).build(),
                                        Card.builder().rank(Ranks.QUEEN).color(Colors.SPADES).build(),
                                        Card.builder().rank(Ranks.KING).color(Colors.SPADES).build(),
                                        Card.builder().rank(Ranks.ACE).color(Colors.SPADES).build()
                                )
                        ),
                        HandRank.builder().combination(Combinations.ROYAL_FLUSH).highestRank(Ranks.ACE).build(),
                        "Testing Royal Card"
                )
        };
    }

    @Getter
    @AllArgsConstructor
    private static class TestCase {
        private Hand input;
        private HandRank output;
        private String name;

        @Override
        public String toString() {
            return name;
        }
    }

}
