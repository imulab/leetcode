package io.imulab.review.leetcode.problems;

import io.imulab.review.leetcode.support.Difficulty;
import io.imulab.review.leetcode.support.Question;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Question(
    index = "1",
    name = "Two Sum",
    links = {"https://leetcode-cn.com/problems/two-sum/", "classpath:/resources/problems/Q1.png"},
    tags = {"array", "hashtable"},
    level = Difficulty.EASY
)
public class Q1 {

    interface Solution {
        int[] twoSum(int[] nums, int target);
    }

    static class ComplementSolution implements Solution {
        public int[] twoSum(int[] nums, int target) {
            // keep mapping of number's complement to the index of the number.
            Map<Integer, Integer> complements = new HashMap<>();

            for (int i = 0; i < nums.length; i++) {
                // records show that the current number was the complement of a previous number,
                // indicating two numbers exist to sum to target
                if (complements.containsKey(nums[i]))
                    return new int[]{complements.get(nums[i]), i};

                // else, maintain record
                complements.put(target - nums[i], i);
            }

            return new int[]{};
        }
    }

    public static void main(String[] args) {
        Solution solution = new ComplementSolution();
        int[] indexes = solution.twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println(Arrays.toString(indexes));
    }
}
