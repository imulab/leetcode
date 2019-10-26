package io.imulab.review.leetcode.problems;

import io.imulab.review.leetcode.support.Difficulty;
import io.imulab.review.leetcode.support.Question;
import io.imulab.review.leetcode.support.Tag;

@Question(
    index = "14",
    name = "Longest common prefix",
    links = {
        "https://leetcode-cn.com/problems/longest-common-prefix/",
        "classpath:/resources/problems/Q14.png"
    },
    level = Difficulty.EASY,
    tags = {
        Tag.STRING
    }
)
public class Q14 {

    interface Solution {
        String longestCommonPrefix(String[] strs);
    }

    // Simple thought: iterate from index 0, check if every string has the same character at the index.
    // Break if index is out of bounds, or some character is not the same.
    static class DefaultSolution implements Solution {
        @Override
        public String longestCommonPrefix(String[] strs) {
            if (strs.length == 0)
                return "";

            int i = 0;
            While:
            while (true) {
                char c = '?';
                for (String str : strs) {
                    if (i > str.length() - 1)
                        break While;

                    if (c == '?')
                        c = str.charAt(i);
                    else if (c != str.charAt(i))
                        break While;
                }
                i++;
            }

            return strs[0].substring(0, i);
        }
    }

    public static void main(String[] args) {
        Solution solution = new DefaultSolution();
        System.out.println(solution.longestCommonPrefix(new String[]{
            "flower", "flow", "flight"
        }));
        System.out.println(solution.longestCommonPrefix(new String[]{
            "dog", "racecar", "car"
        }));
    }
}
