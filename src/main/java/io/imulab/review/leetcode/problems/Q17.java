package io.imulab.review.leetcode.problems;

import io.imulab.review.leetcode.support.Difficulty;
import io.imulab.review.leetcode.support.Question;
import io.imulab.review.leetcode.support.Tag;

import java.util.*;
import java.util.stream.Collectors;

@Question(
    index = "17",
    name = "Letter combinations of a phone number",
    links = {
        "https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/",
        "classpath:/resources/problems/Q17.png"
    },
    level = Difficulty.MEDIUM,
    tags = {
        Tag.STRING,
        Tag.BACK_TRACK
    }
)
public class Q17 {

    interface Solution {
        List<String> letterCombinations(String digits);
    }

    static class DefaultSolution implements Solution {

        private static Map<Character, List<String>> mapping = new HashMap<>();
        static {
            mapping.put('2', Arrays.asList("a", "b", "c"));
            mapping.put('3', Arrays.asList("d", "e", "f"));
            mapping.put('4', Arrays.asList("g", "h", "i"));
            mapping.put('5', Arrays.asList("j", "k", "l"));
            mapping.put('6', Arrays.asList("m", "n", "o"));
            mapping.put('7', Arrays.asList("p", "q", "r", "s"));
            mapping.put('8', Arrays.asList("t", "u", "v"));
            mapping.put('9', Arrays.asList("w", "x", "y", "z"));
        }

        @Override
        public List<String> letterCombinations(String digits) {
            return combos(digits, 0);
        }

        private List<String> combos(String digits, int d) {
            if (d >= digits.length())
                return Collections.emptyList();

            List<String> prefixes = mapping.get(digits.charAt(d));
            if (prefixes == null || prefixes.isEmpty())
                return Collections.emptyList();

            // step to the next digit and calculate possibilities
            List<String> next = combos(digits, d + 1);
            if (next.isEmpty())
                return prefixes;

            // merge result
            List<String> result = new ArrayList<>();
            for (String prefix : prefixes) {
                for (String n : next) {
                    result.add(prefix + n);
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        Solution solution = new DefaultSolution();
        System.out.println(solution.letterCombinations("23"));
    }
}
