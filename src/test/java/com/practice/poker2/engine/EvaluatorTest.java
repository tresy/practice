package com.practice.poker2.engine;

import com.practice.poker2.model.*;
import com.practice.poker2.rules.*;
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

    @ParameterizedTest(name = "#{index} {arguments}")
    @MethodSource("initParams")
    public void testEvaluation(TestCase testCase) {
        // setup
        Evaluator evaluator = new Evaluator(
                Stream.of(
                    new PairRule(), new TwoPairRule(), new DrillRule(), new PokerRule(),
                    new StraightRule(), new FlushRule(), new RoyalFlushRule()
                ).collect(Collectors.toMap(Rule::getCombination, Function.identity()))
        );

        // when
        Combinations combination = evaluator.evaluate(testCase.getHand());

        // then
        Assert.assertEquals(testCase.getCombination(), combination);
    }

    private static TestCase[] initParams() {
        return new TestCase[] {
                new TestCase(
                        new Hand(Arrays.asList(
                                new Card(Ranks.TWO, Colors.HEARTHS),
                                new Card(Ranks.THREE, Colors.CLUBS),
                                new Card(Ranks.KING, Colors.SPADES),
                                new Card(Ranks.JACK, Colors.DIAMONDS),
                                new Card(Ranks.NINE, Colors.HEARTHS)
                        )),
                        Combinations.HIGH_CARD,
                        "Testing high card"
                ),
                new TestCase(
                        new Hand(Arrays.asList(
                                new Card(Ranks.TWO, Colors.HEARTHS),
                                new Card(Ranks.TWO, Colors.CLUBS),
                                new Card(Ranks.KING, Colors.SPADES),
                                new Card(Ranks.JACK, Colors.DIAMONDS),
                                new Card(Ranks.NINE, Colors.HEARTHS)
                        )),
                        Combinations.PAIR,
                        "Testing pair"
                ),
                new TestCase(
                        new Hand(Arrays.asList(
                                new Card(Ranks.TWO, Colors.HEARTHS),
                                new Card(Ranks.TWO, Colors.CLUBS),
                                new Card(Ranks.KING, Colors.SPADES),
                                new Card(Ranks.KING, Colors.DIAMONDS),
                                new Card(Ranks.NINE, Colors.HEARTHS)
                        )),
                        Combinations.TWO_PAIR,
                        "Testing two pair"
                ),
                new TestCase(
                        new Hand(Arrays.asList(
                                new Card(Ranks.TWO, Colors.HEARTHS),
                                new Card(Ranks.TWO, Colors.CLUBS),
                                new Card(Ranks.TWO, Colors.SPADES),
                                new Card(Ranks.KING, Colors.DIAMONDS),
                                new Card(Ranks.NINE, Colors.HEARTHS)
                        )),
                        Combinations.DRILL,
                        "Testing drill"
                ),
                new TestCase(
                        new Hand(Arrays.asList(
                                new Card(Ranks.TWO, Colors.HEARTHS),
                                new Card(Ranks.TWO, Colors.CLUBS),
                                new Card(Ranks.TWO, Colors.SPADES),
                                new Card(Ranks.TWO, Colors.DIAMONDS),
                                new Card(Ranks.NINE, Colors.HEARTHS)
                        )),
                        Combinations.POKER,
                        "Testing poker"
                ),
                new TestCase(
                        new Hand(Arrays.asList(
                                new Card(Ranks.EIGHT, Colors.HEARTHS),
                                new Card(Ranks.SIX, Colors.CLUBS),
                                new Card(Ranks.SEVEN, Colors.SPADES),
                                new Card(Ranks.FIVE, Colors.DIAMONDS),
                                new Card(Ranks.NINE, Colors.HEARTHS)
                        )),
                        Combinations.STRAIGHT,
                        "Testing straight"
                ),
                new TestCase(
                        new Hand(Arrays.asList(
                                new Card(Ranks.ACE, Colors.HEARTHS),
                                new Card(Ranks.TWO, Colors.CLUBS),
                                new Card(Ranks.FOUR, Colors.SPADES),
                                new Card(Ranks.FIVE, Colors.DIAMONDS),
                                new Card(Ranks.THREE, Colors.HEARTHS)
                        )),
                        Combinations.STRAIGHT,
                        "Testing straight with low ace"
                ),
                new TestCase(
                        new Hand(Arrays.asList(
                                new Card(Ranks.ACE, Colors.HEARTHS),
                                new Card(Ranks.KING, Colors.CLUBS),
                                new Card(Ranks.TEN, Colors.SPADES),
                                new Card(Ranks.JACK, Colors.DIAMONDS),
                                new Card(Ranks.QUEEN, Colors.HEARTHS)
                        )),
                        Combinations.STRAIGHT,
                        "Testing straight with high ace"
                ),
                new TestCase(
                        new Hand(Arrays.asList(
                                new Card(Ranks.TWO, Colors.SPADES),
                                new Card(Ranks.KING, Colors.SPADES),
                                new Card(Ranks.FOUR, Colors.SPADES),
                                new Card(Ranks.JACK, Colors.SPADES),
                                new Card(Ranks.QUEEN, Colors.SPADES)
                        )),
                        Combinations.FLUSH,
                        "Testing flush"
                ),
                new TestCase(
                        new Hand(Arrays.asList(
                                new Card(Ranks.TWO, Colors.SPADES),
                                new Card(Ranks.TWO, Colors.DIAMONDS),
                                new Card(Ranks.FIVE, Colors.DIAMONDS),
                                new Card(Ranks.FIVE, Colors.SPADES),
                                new Card(Ranks.FIVE, Colors.HEARTHS)
                        )),
                        Combinations.FULL_HOUSE,
                        "Testing full house"
                ),
                new TestCase(
                        new Hand(Arrays.asList(
                                new Card(Ranks.TWO, Colors.SPADES),
                                new Card(Ranks.THREE, Colors.SPADES),
                                new Card(Ranks.FOUR, Colors.SPADES),
                                new Card(Ranks.FIVE, Colors.SPADES),
                                new Card(Ranks.ACE, Colors.SPADES)
                        )),
                        Combinations.STRAIGHT_FLUSH,
                        "Testing straight flush"
                ),
                new TestCase(
                        new Hand(Arrays.asList(
                                new Card(Ranks.ACE, Colors.SPADES),
                                new Card(Ranks.JACK, Colors.SPADES),
                                new Card(Ranks.KING, Colors.SPADES),
                                new Card(Ranks.QUEEN, Colors.SPADES),
                                new Card(Ranks.TEN, Colors.SPADES)
                        )),
                        Combinations.ROYAL_FLUSH,
                        "Testing royal flush"
                )
        };
    }

    @Getter
    @AllArgsConstructor
    private static class TestCase {
        private Hand hand;
        private Combinations combination;
        private String name;

        @Override
        public String toString() {
            return name;
        }
    }

}
