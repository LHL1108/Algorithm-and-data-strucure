package class23;
/*
要求： 给定一个数组arr, 请将其分成两部分，如果数组长度为偶数，把拆分后两部分含的数据个数必须一样，如果为奇数必须只差一个，在此前提下，使得两部分累加和最接近，返回较小的那个集合的累加和
思路： 此题是上一题的进阶版，难点在于两处，一是主函数的返回有两种情况，偶数的时候时候直接调，奇数个的时候，因为待选参数个数不同，有两个递归函数，要分别计算并比较谁是接近sum/2，也就是更大的那个作为返回值
      二是，因为多了一个待选数个数参数，看到最后有可能是异常的，即没有用掉所有待选数，这是返回-1，该结果不做使用，在后续选取该数的计算过程中，要先判断下后面是不是有效的
代码：
    主函数
        异常判断
            null
            个数小于2
        计算出总和的一半
        双数
            调用递归函数
        单数
            比小组大组返回更大的那个
    递归函数
        basecase
            看到越界
                剩余挑选个数有效
                    返回0
                剩余挑选个数无效
                    返回-1
        递归
            不要当前数
                直接往后跳
            要当前数
                前提是当前数能要
                当前数+后边
                    前提是后边有效
            返回二者较大的
*/

public class Code02_SplitSumClosedSizeHalf {

    public static int right(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int item : arr) {
            sum += item;
        }

        if (arr.length % 2 == 0) {
            return process(arr, arr.length / 2, 0, sum / 2);
        } else {
            return Math.max(process(arr, arr.length / 2, 0, sum / 2), process(arr, (arr.length / 2) + 1, 0, sum / 2));
        }
    }

    public static int process(int[] arr, int pick, int idx, int rest) {
        if (idx == arr.length) {
            return pick == 0 ? 0 : -1;
        }
        int p1 = -1;
        int p2 = -1;
        p1 = process(arr, pick, idx+1, rest);
        if (arr[idx] <= rest) {
            int next = process(arr, pick-1, idx+1, rest-arr[idx]);
            if (next != -1) {
                p2 = arr[idx] + next;
            }
        }
        return Math.max(p1, p2);

    }

    public static int dp(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;
        int M = arr.length + 1 / 2;
        int S = 0;
        for (int item : arr) {
            S += item;
        }
        S /= 2;
        int[][][] dp = new int[N+1][M+1][S+1];
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                for (int s = 0; s <= S; s++) {
                    dp[n][m][s] = -1;
                }
            }
        }
        for (int s = 0; s <= S; s++) {
            dp[N][0][s] = 0;
        }

        for (int n = N-1; n >= 0; n--) {
            for (int m = 0; m <= M; m++) {
                for (int s = 0; s <= S; s++) {
                    int p1 = -1;
                    int p2 = -1;
                    int next = -1;
                    p1 = dp[n+1][m][s];
                    if (arr[n] <= s && m >= 1) {
                        next = dp[n+1][m-1][s-arr[n]];
                    }
                    if (next != -1) {
                        p2 = arr[n] + next;
                    }
                    dp[n][m][s] = Math.max(p1, p2);
                }
            }
        }
        if ((arr.length & 1) == 0) {
            return dp[0][arr.length / 2][S];
        } else {
            return Math.max(dp[0][arr.length / 2][S], dp[0][(arr.length / 2) + 1][S]);
        }
    }

//	public static int right(int[] arr) {
//		if (arr == null || arr.length < 2) {
//			return 0;
//		}
//		int sum = 0;
//		for (int num : arr) {
//			sum += num;
//		}
//		return process(arr, 0, 0, sum >> 1);
//	}
//
//	public static int process(int[] arr, int i, int picks, int rest) {
//		if (i == arr.length) {
//			if ((arr.length & 1) == 0) {
//				return picks == (arr.length >> 1) ? 0 : -1;
//			} else {
//				return (picks == (arr.length >> 1) || picks == (arr.length >> 1) + 1) ? 0 : -1;
//			}
//		}
//		int p1 = process(arr, i + 1, picks, rest);
//		int p2 = -1;
//		int next2 = -1;
//		if (arr[i] <= rest) {
//			next2 = process(arr, i + 1, picks + 1, rest - arr[i]);
//		}
//		if (next2 != -1) {
//			p2 = arr[i] + next2;
//		}
//		return Math.max(p1, p2);
//	}
//
//	public static int dp1(int[] arr) {
//		if (arr == null || arr.length < 2) {
//			return 0;
//		}
//		int sum = 0;
//		for (int num : arr) {
//			sum += num;
//		}
//		sum >>= 1;
//		int N = arr.length;
//		int M = (arr.length + 1) >> 1;
//		int[][][] dp = new int[N + 1][M + 1][sum + 1];
//		for (int i = 0; i <= N; i++) {
//			for (int j = 0; j <= M; j++) {
//				for (int k = 0; k <= sum; k++) {
//					dp[i][j][k] = -1;
//				}
//			}
//		}
//		for (int k = 0; k <= sum; k++) {
//			dp[N][M][k] = 0;
//		}
//		if ((arr.length & 1) != 0) {
//			for (int k = 0; k <= sum; k++) {
//				dp[N][M - 1][k] = 0;
//			}
//		}
//		for (int i = N - 1; i >= 0; i--) {
//			for (int picks = 0; picks <= M; picks++) {
//				for (int rest = 0; rest <= sum; rest++) {
//					int p1 = dp[i + 1][picks][rest];
//					int p2 = -1;
//					int next2 = -1;
//					if (picks + 1 <= M && arr[i] <= rest) {
//						next2 = dp[i + 1][picks + 1][rest - arr[i]];
//					}
//					if (next2 != -1) {
//						p2 = arr[i] + next2;
//					}
//					dp[i][picks][rest] = Math.max(p1, p2);
//				}
//			}
//		}
//		return dp[0][0][sum];
//	}

    public static int dp2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        sum >>= 1;
        int N = arr.length;
        int M = (arr.length + 1) >> 1;
        int[][][] dp = new int[N][M + 1][sum + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= M; j++) {
                for (int k = 0; k <= sum; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int k = 0; k <= sum; k++) {
                dp[i][0][k] = 0;
            }
        }
        for (int k = 0; k <= sum; k++) {
            dp[0][1][k] = arr[0] <= k ? arr[0] : Integer.MIN_VALUE;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= Math.min(i + 1, M); j++) {
                for (int k = 0; k <= sum; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (k - arr[i] >= 0) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - 1][k - arr[i]] + arr[i]);
                    }
                }
            }
        }
        return Math.max(dp[N - 1][M][sum], dp[N - 1][N - M][sum]);
    }

    // for test
    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = right(arr);
            int ans2 = dp(arr);
            int ans3 = dp2(arr);
            if (ans1 != ans2 || ans1 != ans3) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

}