package io.imulab.review.leetcode.problems;

import io.imulab.review.leetcode.support.Difficulty;
import io.imulab.review.leetcode.support.Question;
import io.imulab.review.leetcode.support.Tag;

import java.util.Arrays;

@Question(
    index = "16",
    name = "3sum closest",
    links = {
        "https://leetcode-cn.com/problems/3sum-closest/",
        "classpath:/resources/problems/Q16.png"
    },
    level = Difficulty.MEDIUM,
    tags = {
        Tag.ARRAY,
        Tag.TWO_POINTERS
    }
)
public class Q16 {

    interface Solution {
        int threeSumClosest(int[] nums, int target);
    }

    // The solution is very similar to Q15.
    static class DefaultSolution implements Solution {
        @Override
        public int threeSumClosest(int[] nums, int target) {
            if (nums.length < 3)
                return 0;

            Arrays.sort(nums);

            int ans = 0, distance = Integer.MAX_VALUE;

            for (int left = 0; left < nums.length - 2; left++) {
                // avoid repeated processing
                if (left > 0 && nums[left] == nums[left-1])
                    continue;

                int mid = left + 1;
                int right = nums.length - 1;

                while (mid < right) {
                    int sum = nums[left] + nums[mid] + nums[right];
                    if (Math.abs(sum - target) < distance) {
                        distance = Math.abs(sum - target);
                        ans = sum;
                    }

                    // if bigger than target, move right pointer; else move mid pointer
                    if (sum >= target) {
                        while (mid < right && nums[right] == nums[--right]);
                    } else {
                        while (mid < right && nums[mid] == nums[++mid]);
                    }
                }
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        Solution solution = new DefaultSolution();
        System.out.println(solution.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
        System.out.println(solution.threeSumClosest(new int[]{1, 1, -1, -1, 3}, 3));
    }
}
