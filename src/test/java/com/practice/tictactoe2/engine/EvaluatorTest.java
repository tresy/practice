package com.practice.tictactoe2.engine;

import com.practice.tictactoe2.model.Cell;
import com.practice.tictactoe2.model.Coordinate;
import com.practice.tictactoe2.model.Player;
import com.practice.tictactoe2.model.Table;
import com.practice.tictactoe2.rule.ColumnRule;
import com.practice.tictactoe2.rule.CrossRule;
import com.practice.tictactoe2.rule.RowRule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;

@DisplayName("Testing tic tac toe table evaluation")
public class EvaluatorTest {

    @ParameterizedTest(name = "#{index} {arguments}")
    @MethodSource("initParams")
    public void testEvaluation(TestCase testCase) {
        // setup
        Evaluator evaluator = new Evaluator(Arrays.asList(new RowRule(), new ColumnRule(), new CrossRule()));

        // when
        boolean xIsTheWinner = evaluator.evaluate(Player.X, testCase.table);
        boolean yIsTheWinner = evaluator.evaluate(Player.O, testCase.table);

        // then
        Assert.assertEquals(testCase.getWinner() == Player.X, xIsTheWinner);
        Assert.assertEquals(testCase.getWinner() == Player.O, yIsTheWinner);
    }

    private static TestCase[] initParams() {
        return new TestCase[] {
                // X winning in row
                new TestCase(
                        new Table(Collections.singletonList(
                                new Cell(Player.X, new Coordinate(0, 0))
                        )),
                        Player.NONE,
                        "Test X winning in row - step 1"
                ),
                new TestCase(
                        new Table(Arrays.asList(
                                new Cell(Player.X, new Coordinate(0, 0)),
                                new Cell(Player.O, new Coordinate(0, 2))
                        )),
                        Player.NONE,
                        "Test X winning in row - step 2"
                ),
                new TestCase(
                        new Table(Arrays.asList(
                                new Cell(Player.X, new Coordinate(0, 0)),
                                new Cell(Player.O, new Coordinate(0, 2)),
                                new Cell(Player.X, new Coordinate(1, 0))
                        )),
                        Player.NONE,
                        "Test X winning in row - step 3"
                ),
                new TestCase(
                        new Table(Arrays.asList(
                                new Cell(Player.X, new Coordinate(0, 0)),
                                new Cell(Player.O, new Coordinate(0, 2)),
                                new Cell(Player.X, new Coordinate(1, 0)),
                                new Cell(Player.O, new Coordinate(1, 2))
                        )),
                        Player.NONE,
                        "Test X winning in row - step 4"
                ),
                new TestCase(
                        new Table(Arrays.asList(
                                new Cell(Player.X, new Coordinate(0, 0)),
                                new Cell(Player.O, new Coordinate(0, 2)),
                                new Cell(Player.X, new Coordinate(1, 0)),
                                new Cell(Player.O, new Coordinate(1, 2)),
                                new Cell(Player.X, new Coordinate(2, 0))
                        )),
                        Player.X,
                        "Test X winning in row - step 5"
                ),
                // O winning in column
                new TestCase(
                        new Table(Collections.singletonList(
                                new Cell(Player.X, new Coordinate(0, 0))
                        )),
                        Player.NONE,
                        "Test O winning in column - step 1"
                ),
                new TestCase(
                        new Table(Arrays.asList(
                                new Cell(Player.X, new Coordinate(0, 0)),
                                new Cell(Player.O, new Coordinate(2, 2))
                        )),
                        Player.NONE,
                        "Test O winning in column - step 2"
                ),
                new TestCase(
                        new Table(Arrays.asList(
                                new Cell(Player.X, new Coordinate(0, 0)),
                                new Cell(Player.O, new Coordinate(2, 2)),
                                new Cell(Player.X, new Coordinate(1, 0))
                        )),
                        Player.NONE,
                        "Test O winning in column - step 3"
                ),
                new TestCase(
                        new Table(Arrays.asList(
                                new Cell(Player.X, new Coordinate(0, 0)),
                                new Cell(Player.O, new Coordinate(2, 2)),
                                new Cell(Player.X, new Coordinate(1, 0)),
                                new Cell(Player.O, new Coordinate(2, 0))
                        )),
                        Player.NONE,
                        "Test O winning in column - step 4"
                ),
                new TestCase(
                        new Table(Arrays.asList(
                                new Cell(Player.X, new Coordinate(0, 0)),
                                new Cell(Player.O, new Coordinate(2, 2)),
                                new Cell(Player.X, new Coordinate(1, 0)),
                                new Cell(Player.O, new Coordinate(2, 0)),
                                new Cell(Player.X, new Coordinate(1, 1))
                        )),
                        Player.NONE,
                        "Test O winning in column - step 5"
                ),
                new TestCase(
                        new Table(Arrays.asList(
                                new Cell(Player.X, new Coordinate(0, 0)),
                                new Cell(Player.O, new Coordinate(2, 2)),
                                new Cell(Player.X, new Coordinate(1, 0)),
                                new Cell(Player.O, new Coordinate(2, 0)),
                                new Cell(Player.X, new Coordinate(1, 1)),
                                new Cell(Player.O, new Coordinate(2, 1))
                        )),
                        Player.O,
                        "Test O winning in column - step 6"
                ),
                // X winning in cross
                new TestCase(
                        new Table(Collections.singletonList(
                                new Cell(Player.X, new Coordinate(0, 0))
                        )),
                        Player.NONE,
                        "Test X winning in cross - step 1"
                ),
                new TestCase(
                        new Table(Arrays.asList(
                                new Cell(Player.X, new Coordinate(0, 0)),
                                new Cell(Player.O, new Coordinate(0, 2))
                        )),
                        Player.NONE,
                        "Test X winning in cross - step 2"
                ),
                new TestCase(
                        new Table(Arrays.asList(
                                new Cell(Player.X, new Coordinate(0, 0)),
                                new Cell(Player.O, new Coordinate(2, 0)),
                                new Cell(Player.X, new Coordinate(1, 1))
                        )),
                        Player.NONE,
                        "Test X winning in cross - step 3"
                ),
                new TestCase(
                        new Table(Arrays.asList(
                                new Cell(Player.X, new Coordinate(0, 0)),
                                new Cell(Player.O, new Coordinate(2, 0)),
                                new Cell(Player.X, new Coordinate(1, 1)),
                                new Cell(Player.O, new Coordinate(2, 1))
                        )),
                        Player.NONE,
                        "Test X winning in cross - step 4"
                ),
                new TestCase(
                        new Table(Arrays.asList(
                                new Cell(Player.X, new Coordinate(0, 0)),
                                new Cell(Player.O, new Coordinate(2, 0)),
                                new Cell(Player.X, new Coordinate(1, 1)),
                                new Cell(Player.O, new Coordinate(2, 1)),
                                new Cell(Player.X, new Coordinate(2, 2))
                        )),
                        Player.X,
                        "Test X winning in cross - step 5"
                ),
                // O winning in back-cross
                new TestCase(
                        new Table(Collections.singletonList(
                                new Cell(Player.X, new Coordinate(0, 0))
                        )),
                        Player.NONE,
                        "Test O winning in back-cross - step 1"
                ),
                new TestCase(
                        new Table(Arrays.asList(
                                new Cell(Player.X, new Coordinate(0, 0)),
                                new Cell(Player.O, new Coordinate(0, 2))
                        )),
                        Player.NONE,
                        "Test O winning in back-cross - step 2"
                ),
                new TestCase(
                        new Table(Arrays.asList(
                                new Cell(Player.X, new Coordinate(0, 0)),
                                new Cell(Player.O, new Coordinate(2, 0)),
                                new Cell(Player.X, new Coordinate(0, 1))
                        )),
                        Player.NONE,
                        "Test O winning in back-cross - step 3"
                ),
                new TestCase(
                        new Table(Arrays.asList(
                                new Cell(Player.X, new Coordinate(0, 0)),
                                new Cell(Player.O, new Coordinate(2, 0)),
                                new Cell(Player.X, new Coordinate(0, 1)),
                                new Cell(Player.O, new Coordinate(1, 1))
                        )),
                        Player.NONE,
                        "Test O winning in back-cross - step 4"
                ),
                new TestCase(
                        new Table(Arrays.asList(
                                new Cell(Player.X, new Coordinate(0, 0)),
                                new Cell(Player.O, new Coordinate(2, 0)),
                                new Cell(Player.X, new Coordinate(0, 1)),
                                new Cell(Player.O, new Coordinate(1, 1)),
                                new Cell(Player.X, new Coordinate(1, 0))
                        )),
                        Player.NONE,
                        "Test O winning in back-cross - step 5"
                ),
                new TestCase(
                        new Table(Arrays.asList(
                                new Cell(Player.X, new Coordinate(0, 0)),
                                new Cell(Player.O, new Coordinate(2, 0)),
                                new Cell(Player.X, new Coordinate(0, 1)),
                                new Cell(Player.O, new Coordinate(1, 1)),
                                new Cell(Player.X, new Coordinate(1, 0)),
                                new Cell(Player.O, new Coordinate(0, 2))
                        )),
                        Player.O,
                        "Test O winning in back-cross - step 6"
                ),
                // X winning in cross
                new TestCase(
                        new Table(Collections.singletonList(
                                new Cell(Player.X, new Coordinate(0, 0))
                        )),
                        Player.NONE,
                        "Test X winning in cross - step 1"
                ),
                new TestCase(
                        new Table(Arrays.asList(
                                new Cell(Player.X, new Coordinate(0, 0)),
                                new Cell(Player.O, new Coordinate(1, 0))
                        )),
                        Player.NONE,
                        "Test X winning in cross - step 2"
                ),
                new TestCase(
                        new Table(Arrays.asList(
                                new Cell(Player.X, new Coordinate(0, 0)),
                                new Cell(Player.O, new Coordinate(1, 0)),
                                new Cell(Player.X, new Coordinate(2, 0))
                        )),
                        Player.NONE,
                        "Test X winning in cross - step 3"
                ),
                new TestCase(
                        new Table(Arrays.asList(
                                new Cell(Player.X, new Coordinate(0, 0)),
                                new Cell(Player.O, new Coordinate(1, 0)),
                                new Cell(Player.X, new Coordinate(2, 0)),
                                new Cell(Player.O, new Coordinate(0, 1))
                        )),
                        Player.NONE,
                        "Test X winning in cross - step 4"
                ),
                new TestCase(
                        new Table(Arrays.asList(
                                new Cell(Player.X, new Coordinate(0, 0)),
                                new Cell(Player.O, new Coordinate(1, 0)),
                                new Cell(Player.X, new Coordinate(2, 0)),
                                new Cell(Player.O, new Coordinate(0, 1)),
                                new Cell(Player.X, new Coordinate(1, 1))
                        )),
                        Player.NONE,
                        "Test X winning in cross - step 5"
                ),
                new TestCase(
                        new Table(Arrays.asList(
                                new Cell(Player.X, new Coordinate(0, 0)),
                                new Cell(Player.O, new Coordinate(1, 0)),
                                new Cell(Player.X, new Coordinate(2, 0)),
                                new Cell(Player.O, new Coordinate(0, 1)),
                                new Cell(Player.X, new Coordinate(1, 1)),
                                new Cell(Player.O, new Coordinate(0, 2))
                        )),
                        Player.NONE,
                        "Test X winning in cross - step 6"
                ),
                new TestCase(
                        new Table(Arrays.asList(
                                new Cell(Player.X, new Coordinate(0, 0)),
                                new Cell(Player.O, new Coordinate(1, 0)),
                                new Cell(Player.X, new Coordinate(2, 0)),
                                new Cell(Player.O, new Coordinate(0, 1)),
                                new Cell(Player.X, new Coordinate(1, 1)),
                                new Cell(Player.O, new Coordinate(2, 1)),
                                new Cell(Player.X, new Coordinate(0, 2))
                        )),
                        Player.X,
                        "Test X winning in cross - step 7"
                )
        };
    }

    @Getter
    @AllArgsConstructor
    private static class TestCase {
        private Table table;
        private Player winner;
        private String name;

        @Override
        public String toString() {
            return name;
        }
    }

}
