package javatest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Java {


    @Test
    public void test() {
        permutation("abcdd");
    }

    /**
     * 输入一个字符串，打印出该字符串中字符的所有排列。
     * <p>
     * <p>
     * <p>
     * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
     * 输入：s = "abc"
     * 输出：["abc","acb","bac","bca","cab","cba"]
     */
    public String[] permutation(String s) {
        ArrayList<String> arrayList = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        boolean[] hasUseArray = new boolean[s.length()];
        findStr(s, 0, arrayList, builder, hasUseArray);
        String[] array = new String[arrayList.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = arrayList.get(i);
        }
        return array;
    }

    int index;

    private void findStr(String s, int i, ArrayList<String> arrayList, StringBuilder stringBuilder, boolean[] hasUse) {
        if (i == s.length()) {
            arrayList.add(stringBuilder.toString());
            return;
        }
        HashSet<Character> hashSet = new HashSet<>(s.length());
        for (int index = 0; index < s.length(); index++) {
            char c = s.charAt(index);
            if (hasUse[index] || hashSet.contains(c)) {
                continue;
            }
            hashSet.add(c);
            stringBuilder.append(c);
            hasUse[index] = true;
            findStr(s, i + 1, arrayList, stringBuilder, hasUse);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            hasUse[index] = false;
        }
    }

    /**
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     * <p>
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     * <p>
     * 进阶：
     * <p>
     * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    private int[] failResult = new int[]{-1, -1};

    @Test
    public void testRange() {
        int[] ints = searchRange(new int[]{1, 5, 5, 5, 5, 5, 7}, 5);
        for (int i : ints) {
            System.out.println(i);
        }
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return failResult;
        }
        int left = binarySearch(0, nums.length - 1, nums, target, true);
        int right = binarySearch(0, nums.length - 1, nums, target, false);
        return new int[]{left, right};
    }

    private int binarySearch(int startindex, int endindex, int[] nums, int target, boolean findLeft) {
        int left = startindex;
        int right = endindex;
        int index = -1;
        while (right >= left) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                index = mid;
                if (findLeft) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }

            } else {
                if (nums[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

        }
        return index;
    }


    /**
     * 国际象棋 长 m 宽 n
     * 起点左下角
     * 终点右下角
     * 所有路径的数量
     */
    int testi = 0;

    @Test
    public void test4() {
        ArrayList<Integer> list = new ArrayList<>();
        findRoutes(1, 1, 5, 5, list);
        System.out.println(list.size());
    }

    public void findRoutes(int currentX, int currentY, int totalX, int totalY, ArrayList<Integer> result) {
        if (currentX == totalX && currentY == totalY) {
            System.out.println("end" + (++testi));
            result.add(0);
            return;
        }
        if (currentX < totalX) {
            System.out.println("右移动" + (currentX + 1) + "  ---  " + (currentY));
            findRoutes(currentX + 1, currentY, totalX, totalY, result);
        }
        if (currentY < totalY) {
            System.out.println("下移动" + (currentX) + "  ---  " + (currentY + 1));
            findRoutes(currentX, currentY + 1, totalX, totalY, result);
        }
    }

    @Test
    public void test5() {
        int[][] array = new int[5][3];
        findRoutes(array);
        System.out.println(array[array.length - 1][array[array.length - 1].length - 1]);
    }

    public void findRoutes(int[][] result) {
        for (int x = 0; x < result.length; x++) {
            for (int y = 0; y < result[x].length; y++) {
                if (x == 0 || y == 0) {
                    result[x][y] = 1;
                    continue;
                }
                System.out.println("result x==" + x + " y== " + y + "  result " + result[x - 1][y] + result[x][y - 1]);
                result[x][y] = result[x - 1][y] + result[x][y - 1];
            }
        }
    }


    /**
     * todo
     * 416. 分割等和子集
     * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     */

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 2 != 0) {
            return false;
        }
        Arrays.sort(nums);
        int subNum = 0;
        for (int n : nums) {
            subNum += n;
            int count = subNum << 1;
            if (count == sum) {
                return true;
            } else if (count > sum) {
                return false;
            }
        }
        return false;
    }


    @Test

    public void test12() {
        int targetSumWays = findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3);
        System.out.println(targetSumWays);
    }

    /**
     * 给你一个整数数组 nums 和一个整数 target 。
     * <p>
     * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
     * <p>
     * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
     * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
     * <p>
     * 输入：nums = [1,1,1,1,1], target = 3
     * 输出：5
     * 解释：一共有 5 种方法让最终目标和为 3 。
     * -1 + 1 + 1 + 1 + 1 = 3
     * +1 - 1 + 1 + 1 + 1 = 3
     * +1 + 1 - 1 + 1 + 1 = 3
     * +1 + 1 + 1 - 1 + 1 = 3
     * +1 + 1 + 1 + 1 - 1 = 3
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/target-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (target > sum || target < -sum) {
            return 0;
        }

        int[][] dp = new int[nums.length][2 * sum + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < dp[i].length; j++)
                if (i == 0) {
                    dp[i][sum - nums[0]] = 1 + dp[i][sum - nums[0]];
                    dp[i][sum + nums[0]] = 1 + dp[i][sum + nums[0]];
                    break;
                } else {
                    if (j - nums[i] >= 0) {
                        dp[i][j] = dp[i - 1][j - nums[i]];
                    }
                    if (j + nums[i] < 2 * sum + 1) {
                        dp[i][j] += dp[i - 1][j + nums[i]];
                    }


                }
        }
        return dp[nums.length - 1][sum + target];
    }

    /***
     * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
     *
     * 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。
     *
     * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
     示例 1：

     输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
     输出：4
     解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
     其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
     示例 2：

     输入：strs = ["10", "0", "1"], m = 1, n = 1
     输出：2
     解释：最大的子集是 {"0", "1"} ，所以答案是 2 。

     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/ones-and-zeroes
     著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public int findMaxForm(String[] strs, int m, int n) {
        //  构造一个dp[i][j]  i是元素个数  j的是当前元素，每个元素根据选或不选
        //1 <= strs.length <= 600
        //1 <= strs[i].length <= 100
        //strs[i] 仅由 '0' 和 '1' 组成
        //1 <= m, n <= 100
        //todo 三维数组
        return 0;
    }

    private int mask = 65535;

    public int getSize(int i, boolean isZero) {
        if (isZero) {
            return i & mask;
        } else {
            return (i >> 16) & mask;
        }
    }


    /**
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
     * <p>
     * 你可以认为每种硬币的数量是无限的。
     * 示例 1：
     * <p>
     * 输入：coins = [1, 2, 5], amount = 11
     * 输出：3
     * 解释：11 = 5 + 5 + 1
     */

//    public int coinChange(int[] coins, int amount) {
//        Arrays.sort(coins);
//        for (int i = coins.length - 1; i >= 0; i--) {
//            int maxSize = amount / coins[i];
//            findResult(size, )
//
//        }
//    }


    /**
     * 给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
     * <p>
     * 子数组 是数组的一段连续部分
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 3 * 104
     * nums[i] 不是 0 就是 1
     * 0 <= goal <= nums.length
     */
//    public int numSubarraysWithSum(int[] nums, int goal) {
//
//    }
}
