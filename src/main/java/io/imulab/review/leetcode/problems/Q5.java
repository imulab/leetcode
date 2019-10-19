package io.imulab.review.leetcode.problems;

import io.imulab.review.leetcode.support.Difficulty;
import io.imulab.review.leetcode.support.Question;
import io.imulab.review.leetcode.support.Tag;

@Question(
    index = "5",
    name = "Longest palindromic substring",
    links = {
        "https://leetcode-cn.com/problems/longest-palindromic-substring/",
        "classpath:/resources/problems/Q5.png"
    },
    level = Difficulty.MEDIUM,
    tags = {
        Tag.STRING,
        Tag.DYNAMIC_PROGRAMMING
    }
)
public class Q5 {

    interface Solution {
        String longestPalindrome(String s);
    }

    static class DefaultSolution implements Solution {
        @Override
        public String longestPalindrome(String s) {
            if (s.length() <= 1)
                return s;

            // left array records the substring to the left of the character at index.
            String[] left = new String[s.length()];
            left[0] = "";
            for (int i = 1; i < s.length(); i++) {
                left[i] = left[i-1] + s.charAt(i-1);
            }

            // right array records the substring to the right of the character at index.
            String[] right = new String[s.length()];
            right[s.length()-1] = "";
            for (int j = s.length() - 2; j >= 0; j--) {
                right[j] = s.charAt(j+1) + right[j+1];
            }

            // Initialize answer to be the first character.
            // In case no palindrome, use this as answer.
            String longest = s.substring(0, 1);

            for (int k = 0; k < s.length(); k++) {
                // try the mode 'xyx', treating the current character as pivot, and expand around
                if (left[k].length() > 0 && right[k].length() > 0) {
                    StringBuilder candidate = new StringBuilder();
                    candidate.append(s.charAt(k));

                    int p = left[k].length() - 1, q = 0;
                    while (p >= 0 && q < right[k].length() &&
                        left[k].charAt(p) == right[k].charAt(q)) {
                        p--;
                        q++;
                    }

                    candidate.insert(0, left[k].substring(p+1));
                    candidate.append(right[k], 0, q);

                    // update answer
                    if (candidate.length() > longest.length())
                        longest = candidate.toString();
                }

                // try the mode 'xyyx', treating this element and its right element as pivot
                if (right[k].length() > 0 && s.charAt(k) == right[k].charAt(0)) {
                    StringBuilder candidate = new StringBuilder();
                    candidate.append(s.charAt(k));
                    candidate.append(right[k].charAt(0));

                    int p = left[k].length() - 1, q = 1;
                    while (p >= 0 && q < right[k].length() &&
                        left[k].charAt(p) == right[k].charAt(q)) {
                        p--;
                        q++;
                    }

                    candidate.insert(0, left[k].substring(p+1));
                    candidate.append(right[k], 1, q);   // watch out to start substring at 1

                    // update answer
                    if (candidate.length() > longest.length())
                        longest = candidate.toString();
                }
            }

            return longest;
        }
    }

    public static void main(String[] args) {
        Solution solution = new DefaultSolution();
        System.out.println(solution.longestPalindrome("babad"));
        System.out.println(solution.longestPalindrome("cbbd"));
        System.out.println(solution.longestPalindrome("ac"));
    }
}
