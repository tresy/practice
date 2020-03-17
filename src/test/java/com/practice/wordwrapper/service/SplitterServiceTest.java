package com.practice.wordwrapper.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

@DisplayName("Test splitter service")
public class SplitterServiceTest {

    @ParameterizedTest(name = "#{index} {arguments}")
    @MethodSource("initParams")
    public void testSplitting(TestCase testCase) {
        // setup
        SplitterService splitterService = new SplitterServiceImpl(testCase.getMaxLength());

        // when
        List<String> lines = splitterService.split(testCase.getText());

        // then
        Assert.assertEquals(testCase.getLines(), lines);
    }

    private static TestCase[] initParams() {
        return new TestCase[] {
                new TestCase(
                        10,
                        "One Two Three Four Five Six Seven Eight Nine Ten",
                        Arrays.asList("One Two", "Three Four", "Five Six", "Seven", "Eight Nine", "Ten"),
                        "Test splitting when lines maximum size is 10"
                ),
                new TestCase(
                        15,
                        "One Two Three Four Five Six Seven Eight Nine Ten",
                        Arrays.asList("One Two Three", "Four Five Six", "Seven Eight", "Nine Ten"),
                        "Test splitting when lines maximum size is 15"
                ),
                new TestCase(
                        15,
                        "     One Two Three Four Five Six Seven Eight Nine Ten     ",
                        Arrays.asList("One Two Three", "Four Five Six", "Seven Eight", "Nine Ten"),
                        "Test splitting when text starts and ends with additional spaces"
                ),
                new TestCase(
                        15,
                        "One Two Three     Four Five Six Seven Eight Nine Ten",
                        Arrays.asList("One Two", "Three     Four", "Five Six Seven", "Eight Nine Ten"),
                        "Test splitting when five spaces are unbreakable"
                ),
                new TestCase(
                        15,
                        "One Two Threeee     Fou Five Six Seven Eight Nine Ten",
                        Arrays.asList("One Two", "Threeee     Fou", "Five Six Seven", "Eight Nine Ten"),
                        "Test splitting when break at the begining of five spaces"
                ),
                new TestCase(
                        15,
                        "One Two Thr     Four Five Six Seven Eight Nine Ten",
                        Arrays.asList("One Two", "Thr     Four", "Five Six Seven", "Eight Nine Ten"),
                        "Test splitting when break at the ending of five spaces"
                )
        };
    }

    @Getter
    @AllArgsConstructor
    private static class TestCase {
        private int maxLength;
        private String text;
        private List<String> lines;
        private String name;

        @Override
        public String toString() {
            return name;
        }
    }

}
