package io.imulab.review.leetcode.problems;

import io.imulab.review.leetcode.support.Difficulty;
import io.imulab.review.leetcode.support.Question;
import io.imulab.review.leetcode.support.Tag;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Question(
    index = "15",
    name = "3sum",
    links = {
        "https://leetcode-cn.com/problems/3sum/",
        "classpath:/resources/problems/Q15.png"
    },
    level = Difficulty.MEDIUM,
    tags = {
        Tag.ARRAY,
        Tag.TWO_POINTERS,
    }
)
public class Q15 {

    interface Solution {
        List<List<Integer>> threeSum(int[] nums);
    }

    // We observe that the solution must contain three numbers that crosses zero, or be zero themselves.
    static class DefaultSolution implements Solution {
        @Override
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            if (nums.length < 3)
                return result;

            // sort array in ascending order
            Arrays.sort(nums);

            // There would be no solution if the array does not cross zero (contain
            // both negative and positive numbers)
            if (nums[0] > 0)
                return result;

            for (int left = 0; left < nums.length - 2; left++) {

                // if the left most number is positive, then the rest is also positive, there
                // could be no solution here.
                if (nums[left] > 0)
                    break;

                // if it is equal to the previous number, skip it so we don't perform
                // the same calculation again
                if (left > 0 && nums[left] == nums[left-1])
                    continue;

                // the initial position of the middle and the right number
                int mid = left + 1;
                int right = nums.length - 1;

                while (mid < right) {
                    int sum = nums[left] + nums[mid] + nums[right];
                    if (sum == 0) {
                        result.add(Arrays.asList(nums[left], nums[mid], nums[right]));
                    }

                    if (sum >= 0) {
                        while (mid < right && nums[right] == nums[--right]);
                    } else {
                        while (mid < right && nums[mid] == nums[++mid]);
                    }
                }
            }

            return result;
        }
    }

    public static void main(String[] args) {
        Solution solution = new DefaultSolution();
        System.out.println(solution.threeSum(new int[]{0, 0, 0}));
        System.out.println(solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }
}
