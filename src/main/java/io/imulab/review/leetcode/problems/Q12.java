package io.imulab.review.leetcode.problems;

import io.imulab.review.leetcode.support.Difficulty;
import io.imulab.review.leetcode.support.Question;
import io.imulab.review.leetcode.support.Tag;

@Question(
    index = "12",
    name = "Integer to roman",
    links = {
        "https://leetcode-cn.com/problems/integer-to-roman/",
        "classpath:/resources/problems/Q12.png"
    },
    level = Difficulty.MEDIUM,
    tags = {
        Tag.MATH,
        Tag.STRING
    }
)
public class Q12 {

    interface Solution {
        String intToRoman(int num);
    }

    static class DefaultSolution implements Solution {
        @Override
        public String intToRoman(int num) {
            // possible number units and their values in descending order.
            String[] combos = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
            int[] values = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

            // We use greedy algorithm. Iterate to the largest number unit that will fit
            // and deduct the value.
            StringBuilder sb = new StringBuilder();
            while (num > 0) {
                int i = 0;
                while (num / values[i] == 0)
                    i++;

                num -= values[i];
                sb.append(combos[i]);
            }

            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Solution solution = new DefaultSolution();
        System.out.println(solution.intToRoman(3));
        System.out.println(solution.intToRoman(4));
        System.out.println(solution.intToRoman(9));
        System.out.println(solution.intToRoman(58));
        System.out.println(solution.intToRoman(1994));
    }
}
