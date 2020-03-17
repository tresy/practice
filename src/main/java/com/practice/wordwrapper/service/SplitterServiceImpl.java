package com.practice.wordwrapper.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class SplitterServiceImpl implements SplitterService {

    private int maxLength;

    @Override
    public List<String> split(String text) {
        StringBuilder sb = new StringBuilder(text.trim());

        List<String> lines = new ArrayList<>();
        while (sb.length() > maxLength) {
            sb = breakLine(sb, lines, getSplitIndex(sb.toString(), maxLength));
        }
        lines.add(sb.toString());

        return lines;
    }

    private StringBuilder breakLine(StringBuilder sb, List<String> lines, int index) {
        lines.add(sb.substring(0, index));
        return new StringBuilder(sb.substring(index).trim());
    }

    private int getSplitIndex(String text, int index) {
        if (text.charAt(index) == ' ' && !isMemberOfMultipleSpaces(text, index, 5)) {
            return index;
        }

        String subStr = text.substring(0, index);
        while (subStr.charAt(subStr.lastIndexOf(' ') - 1) == ' ') {
            subStr = subStr.substring(0, subStr.lastIndexOf(' ') - 1);
        }
        return getSplitIndex(subStr, subStr.lastIndexOf(' '));
    }

    private boolean isMemberOfMultipleSpaces(String text, int index, int expectedLength) {
        StringBuilder testSb = new StringBuilder();
        for (int i = 0; i < expectedLength; i ++) {
            testSb.append(" ");
        }

        int min = index - expectedLength > -1 ? index - expectedLength : 0;
        int max = index + expectedLength < text.length() ? index + expectedLength : text.length() - 1;

        return text.substring(min, max).contains(testSb.toString());
    }

}
