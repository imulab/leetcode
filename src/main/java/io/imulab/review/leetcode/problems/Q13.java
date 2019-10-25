package io.imulab.review.leetcode.problems;

import io.imulab.review.leetcode.support.Difficulty;
import io.imulab.review.leetcode.support.Question;
import io.imulab.review.leetcode.support.Tag;

import java.util.HashMap;
import java.util.Map;

@Question(
    index = "13",
    name = "Roman to integer",
    links = {
        "https://leetcode-cn.com/problems/roman-to-integer/",
        "classpath:/resources/problems/Q13.png"
    },
    level = Difficulty.EASY,
    tags = {
        Tag.MATH,
        Tag.STRING
    }
)
public class Q13 {

    interface Solution {
        int romanToInt(String s);
    }

    static class DefaultSolution implements Solution {
        @Override
        public int romanToInt(String s) {
            int num = 0;

            // lookup for values
            Map<Character, Integer> values = new HashMap<>();
            values.put('I', 1);
            values.put('V', 5);
            values.put('X', 10);
            values.put('L', 50);
            values.put('C', 100);
            values.put('D', 500);
            values.put('M', 1000);

            // value for the last character just for comparison.
            // If the last value is smaller than the current value, then
            // we know the last value was actually meant for deducting from
            // the current value.
            int lastValue = Integer.MAX_VALUE;

            for (Character c : s.toCharArray()) {
                int currentValue = values.get(c);
                if (lastValue < currentValue)
                    num = num - 2 * lastValue + currentValue;
                else
                    num += currentValue;
                lastValue = currentValue;
            }

            return num;
        }
    }

    public static void main(String[] args) {
        Solution solution = new DefaultSolution();
        System.out.println(solution.romanToInt("III"));
        System.out.println(solution.romanToInt("IV"));
        System.out.println(solution.romanToInt("IX"));
        System.out.println(solution.romanToInt("LVIII"));
        System.out.println(solution.romanToInt("MCMXCIV"));
    }
}
