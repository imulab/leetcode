package io.imulab.review.leetcode.problems;

import io.imulab.review.leetcode.support.Difficulty;
import io.imulab.review.leetcode.support.Question;
import io.imulab.review.leetcode.support.Tag;

import java.util.Arrays;

@Question(
    index = "9",
    name = "Palindrome number",
    links = {
        "https://leetcode-cn.com/problems/palindrome-number/",
        "classpath:/resources/problems/Q9.png"
    },
    level = Difficulty.EASY,
    tags = {
        Tag.MATH
    }
)
public class Q9 {

    interface Solution {
        boolean isPalindrome(int x);
    }

    // This solution will fail INT_MAX and INT_MIN
    static class DefaultSolution implements Solution {
        @Override
        public boolean isPalindrome(int x) {
            if (x < 0)
                return false;

            // shortcut: single digit number is palindrome
            if (x < 10)
                return true;

            // detect how many digits
            int n = 1;
            while (x / (int) Math.pow(10, n) > 0)
                n++;

            // break number down to individual digits
            int[] digits = new int[n];
            for (int i = 0; i < digits.length; i++) {
                digits[i] = x / (int) Math.pow(10, n - i - 1);
                x = x % (int) Math.pow(10, n - i - 1);
            }

            // check palindrome
            int i = 0, j = digits.length - 1;
            while (i <= j) {
                if (digits[i++] != digits[j--])
                    return false;
            }

            return true;
        }
    }

    static class ReverseNumber implements Solution {
        @Override
        public boolean isPalindrome(int x) {
            // shortcut: negative numbers or positive numbers that ends with 0
            // cannot be palindrome
            if (x < 0 || (x % 10 == 0 && x != 0))
                return false;

            // keep moving the least significant digit from x to rev
            int rev = 0;
            while (x > rev) {
                rev = rev * 10 + x % 10;
                x /= 10;
            }

            // if palindrome and even digits: i.e. 1221, x=12, rev=12
            // if palindrome and odd digits: i.e. 12321, x=12, rev=123
            return x == rev || x == rev / 10;
        }
    }

    public static void main(String[] args) {
        Solution solution = new ReverseNumber();
        System.out.println(solution.isPalindrome(2147483647));
    }
}
