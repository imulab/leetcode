package io.imulab.review.leetcode.problems;

import io.imulab.review.leetcode.support.Difficulty;
import io.imulab.review.leetcode.support.Question;
import io.imulab.review.leetcode.support.Tag;

@Question(
    index = "8",
    name = "String to integer atoi",
    links = {
        "https://leetcode-cn.com/problems/string-to-integer-atoi/",
        "classpath:/resources/problems/Q8.png"
    },
    level = Difficulty.MEDIUM,
    tags = {
        Tag.STRING,
        Tag.MATH
    }
)
public class Q8 {

    interface Solution {
        int myAtoi(String str);
    }

    static class DefaultSolution implements Solution {
        @Override
        public int myAtoi(String str) {
            int start = -1, end = -1;

            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                // SPACE
                // if we have started recording number, SPACE should be treated as the terminal
                // if we have not started, then skip to next
                if (c == ' ') {
                    if (start != -1) {
                        end = i;
                        break;
                    } else {
                        continue;
                    }
                }

                // MINUS SIGN
                // if we have started recording number, terminate
                // if we have not started, start now
                if (c == '-') {
                    if (start != -1) {
                        end = i;
                        break;
                    } else {
                        start = i;
                        continue;
                    }
                }

                // PLUS SIGN
                // if we have started recording number, terminate
                // if we have not started, start now
                if (c == '+') {
                    if (start != -1) {
                        end = i;
                        break;
                    } else {
                        start = i;
                        continue;
                    }
                }

                // NUMBER
                // if we have not started, start recording.
                // in any case, move to next
                if (c >= '0' && c <= '9') {
                    if (start == -1) {
                        start = i;
                    }
                    continue;
                }

                // DEFAULT CASE
                // If we have not started recording numbers, it is illegal to have this character
                // If we have started, terminate number recording
                if (start == -1) {
                    return 0;
                } else {
                    end = i;
                    break;
                }
            }

            if (start == -1)
                return 0;
            if (end == -1)
                end = str.length();

            // check for edge case where effective region is only plus or minus sign without numbers
            if (end - start == 1 && (str.charAt(start) == '-' || str.charAt(start) == '+'))
                return 0;

            try {
                return Integer.parseInt(str.substring(start, end));
            } catch (NumberFormatException e) {
                return (str.charAt(start) == '-') ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new DefaultSolution();
        System.out.println(solution.myAtoi("4193 with words"));
    }
}
