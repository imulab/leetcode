package io.imulab.review.leetcode.problems;

import io.imulab.review.leetcode.support.Difficulty;
import io.imulab.review.leetcode.support.Question;
import io.imulab.review.leetcode.support.Tag;

@Question(
    index = "6",
    name = "Zigzag conversion",
    links = {
        "https://leetcode-cn.com/problems/zigzag-conversion/",
        "classpath:/resources/problems/Q6.png"
    },
    level = Difficulty.MEDIUM,
    tags = {
        Tag.STRING
    }
)
public class Q6 {

    interface Solution {
        String convert(String s, int numRows);
    }

    // We separate the problems into append 'zig lines' and 'zag' lines:
    // - The step between zig lines are constant: (numRows - 1) * 2
    // - The step between zag lines are variant, it decreases by 2
    // We will not consider appending zag lines when zagStep = zigStep or zagStep = 0
    static class DefaultSolution implements Solution {
        @Override
        public String convert(String s, int numRows) {
            if (numRows <= 1)
                return s;

            StringBuilder sb = new StringBuilder();
            int zigStep = (numRows - 1) * 2;

            for (int i = 0, zagStep = (numRows - 1) * 2; i < numRows; i++) {
                for (int k = i; k < s.length(); k += zigStep) {
                    // append the zig line character
                    sb.append(s.charAt(k));

                    // append the zag line character if step is not 0 or collide with zigStep.
                    if (zagStep != 0 && zagStep != zigStep && k + zagStep < s.length())
                        sb.append(s.charAt(k + zagStep));
                }

                // adjust zagStep for next line.
                zagStep -= 2;
                if (zagStep <= 0)
                    zagStep = (numRows - 1) * 2;
            }

            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Solution solution = new DefaultSolution();
        System.out.println(solution.convert("leetcodeishiring", 3));
        System.out.println(solution.convert("leetcodeishiring", 4));

    }
}
