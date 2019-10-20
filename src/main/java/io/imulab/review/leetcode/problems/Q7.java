package io.imulab.review.leetcode.problems;

import io.imulab.review.leetcode.support.Difficulty;
import io.imulab.review.leetcode.support.Question;
import io.imulab.review.leetcode.support.Tag;

import java.util.Stack;

@Question(
    index = "7",
    name = "Reverse integer",
    links = {
        "https://leetcode-cn.com/problems/reverse-integer/",
        "classpath:/resources/problems/Q7.png"
    },
    level = Difficulty.EASY,
    tags = {
        Tag.MATH
    }
)
public class Q7 {

    interface Solution {
        int reverse(int x);
    }

    static class DefaultSolution implements Solution {
        @Override
        public int reverse(int x) {
            int result = 0;
            while (x != 0) {
                // get each least significant digit
                int n = x % 10;
                x = x / 10;

                // check for integer overflow, MAX=2147483647, MIN=-2147483648
                if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && n > 7))
                    return 0;
                if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && n < -8))
                    return 0;

                // build
                result = result * 10 + n;
            }

            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }
}
