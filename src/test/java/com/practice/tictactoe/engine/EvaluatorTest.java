package com.practice.tictactoe.engine;

import com.practice.tictactoe.model.Cell;
import com.practice.tictactoe.model.Coordinate;
import com.practice.tictactoe.model.Player;
import com.practice.tictactoe.model.Table;
import com.practice.tictactoe.rules.ColumnRule;
import com.practice.tictactoe.rules.CrossRule;
import com.practice.tictactoe.rules.RowRule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;

@DisplayName("Testing Tic Tac Toe table evaluations")
public class EvaluatorTest {

    private Evaluator evaluator = new Evaluator(Arrays.asList(new RowRule(), new ColumnRule(), new CrossRule()));

    @ParameterizedTest(name = "#{index} {0}")
    @MethodSource(value = "initParameters")
    public void testEvaluate(TestCase testCase) {
        Assert.assertEquals(testCase.getWinner() == Player.X, evaluator.evaluate(Player.X, testCase.getTable()));
        Assert.assertEquals(testCase.getWinner() == Player.O, evaluator.evaluate(Player.O, testCase.getTable()));
    }

    private static TestCase[] initParameters() {
        return new TestCase[] {
                new TestCase(
                        new Table(
                                Arrays.asList(
                                        // ROW 1
                                        Cell.builder().player(Player.X).coordinate(Coordinate.builder().x(0).y(0).build()).build(),
                                        Cell.builder().player(Player.X).coordinate(Coordinate.builder().x(1).y(0).build()).build(),
                                        Cell.builder().player(Player.X).coordinate(Coordinate.builder().x(2).y(0).build()).build(),
                                        // ROW 2
                                        Cell.builder().player(Player.O).coordinate(Coordinate.builder().x(0).y(1).build()).build(),
                                        Cell.builder().player(Player.O).coordinate(Coordinate.builder().x(1).y(1).build()).build()
                                )
                        ),
                        Player.X,
                        "X should win in first row"
                ),
                new TestCase(
                        new Table(
                                Arrays.asList(
                                        // ROW 1
                                        Cell.builder().player(Player.X).coordinate(Coordinate.builder().x(0).y(0).build()).build(),
                                        Cell.builder().player(Player.X).coordinate(Coordinate.builder().x(1).y(0).build()).build(),
                                        // ROW 2
                                        Cell.builder().player(Player.O).coordinate(Coordinate.builder().x(0).y(1).build()).build(),
                                        Cell.builder().player(Player.O).coordinate(Coordinate.builder().x(1).y(1).build()).build(),
                                        Cell.builder().player(Player.O).coordinate(Coordinate.builder().x(2).y(1).build()).build()
                                )
                        ),
                        Player.O,
                        "O should win in second row"
                ),
                new TestCase(
                        new Table(
                                Arrays.asList(
                                        // COL 1
                                        Cell.builder().player(Player.X).coordinate(Coordinate.builder().x(0).y(0).build()).build(),
                                        Cell.builder().player(Player.X).coordinate(Coordinate.builder().x(0).y(1).build()).build(),
                                        Cell.builder().player(Player.X).coordinate(Coordinate.builder().x(0).y(2).build()).build(),
                                        // COL 2
                                        Cell.builder().player(Player.O).coordinate(Coordinate.builder().x(1).y(1).build()).build(),
                                        // COL 3
                                        Cell.builder().player(Player.O).coordinate(Coordinate.builder().x(2).y(1).build()).build()
                                )
                        ),
                        Player.X,
                        "X should win in first column"
                ),
                new TestCase(
                        new Table(
                                Arrays.asList(
                                        // COL 1
                                        Cell.builder().player(Player.X).coordinate(Coordinate.builder().x(0).y(0).build()).build(),
                                        // COL 2
                                        Cell.builder().player(Player.O).coordinate(Coordinate.builder().x(1).y(0).build()).build(),
                                        Cell.builder().player(Player.O).coordinate(Coordinate.builder().x(1).y(1).build()).build(),
                                        Cell.builder().player(Player.O).coordinate(Coordinate.builder().x(1).y(2).build()).build(),
                                        // COL 3
                                        Cell.builder().player(Player.X).coordinate(Coordinate.builder().x(2).y(0).build()).build()
                                )
                        ),
                        Player.O,
                        "O should win in second column"
                ),
                new TestCase(
                        new Table(
                                Arrays.asList(
                                        // COL 1
                                        Cell.builder().player(Player.X).coordinate(Coordinate.builder().x(0).y(0).build()).build(),
                                        Cell.builder().player(Player.O).coordinate(Coordinate.builder().x(0).y(1).build()).build(),
                                        // COL 2
                                        Cell.builder().player(Player.X).coordinate(Coordinate.builder().x(1).y(1).build()).build(),
                                        Cell.builder().player(Player.O).coordinate(Coordinate.builder().x(1).y(2).build()).build(),
                                        // COL 3
                                        Cell.builder().player(Player.X).coordinate(Coordinate.builder().x(2).y(2).build()).build()
                                )
                        ),
                        Player.X,
                        "X should win in cross"
                ),
                new TestCase(
                        new Table(
                                Arrays.asList(
                                        // COL 1
                                        Cell.builder().player(Player.X).coordinate(Coordinate.builder().x(0).y(0).build()).build(),
                                        Cell.builder().player(Player.X).coordinate(Coordinate.builder().x(0).y(1).build()).build(),
                                        Cell.builder().player(Player.O).coordinate(Coordinate.builder().x(0).y(2).build()).build(),
                                        // COL 2
                                        Cell.builder().player(Player.O).coordinate(Coordinate.builder().x(1).y(1).build()).build(),
                                        Cell.builder().player(Player.X).coordinate(Coordinate.builder().x(1).y(2).build()).build(),
                                        // COL 3
                                        Cell.builder().player(Player.O).coordinate(Coordinate.builder().x(2).y(0).build()).build()
                                )
                        ),
                        Player.O,
                        "O should win in back-cross"
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
