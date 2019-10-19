package io.imulab.review.leetcode.problems;

import io.imulab.review.leetcode.support.Difficulty;
import io.imulab.review.leetcode.support.Question;
import io.imulab.review.leetcode.support.Tag;

import java.util.HashMap;
import java.util.Map;

@Question(
    index = "3",
    name = "Longest substring without repeating characters",
    links = {
        "https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/",
        "classpath:/resources/problems/Q3.png"
    },
    level = Difficulty.MEDIUM,
    tags = {Tag.HASH_TABLE, Tag.TWO_POINTERS, Tag.STRING, Tag.SLIDING_WINDOW}
)
public class Q3 {

    interface Solution {
        int lengthOfLongestSubstring(String s);
    }

    static class DefaultSolution implements Solution {
        @Override
        public int lengthOfLongestSubstring(String s) {
            // lastIndex keeps track of the index that a character last appeared at.
            Map<Character, Integer> lastIndex = new HashMap<>();
            int longest = 0;

            // go from left to right, and try to expand the window [i, j]
            for (int i = 0, j = 0; j < s.length(); j++) {
                Character c = s.charAt(j);

                // shrink the index i to plus one of the last occurring index of the character
                if (lastIndex.containsKey(c))
                    i = Math.max(i, lastIndex.get(c) + 1);

                // update answer
                longest = Math.max(longest, j - i + 1);

                // record/update index of the character
                lastIndex.put(s.charAt(j), j);
            }

            return longest;
        }
    }

    public static void main(String[] args) {
        Solution solution = new DefaultSolution();

        System.out.println(solution.lengthOfLongestSubstring("bbbbb"));
        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
    }
}
