package io.imulab.review.leetcode.problems;

import io.imulab.review.leetcode.support.Difficulty;
import io.imulab.review.leetcode.support.Question;
import io.imulab.review.leetcode.support.Tag;

@Question(
    index = "11",
    name = "Container with most water",
    links = {
        "https://leetcode-cn.com/problems/container-with-most-water/",
        "classpath:/resources/problems/Q11.png"
    },
    level = Difficulty.MEDIUM,
    tags = {
        Tag.ARRAY,
        Tag.TWO_POINTERS
    }
)
public class Q11 {

    interface Solution {
        int maxArea(int[] height);
    }

    static class DefaultSolution implements Solution {
        @Override
        public int maxArea(int[] height) {
            int i = 0, j = height.length - 1;
            int area = 0;
            while (i < j) {
                area = Math.max(area, Math.min(height[i], height[j]) * (j - i));
                if (height[i] < height[j])
                    i++;
                else
                    j--;
            }
            return area;
        }
    }

    public static void main(String[] args) {
        Solution solution = new DefaultSolution();
        System.out.println(solution.maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }
}
