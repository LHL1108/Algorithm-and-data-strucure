package lai01;


/*
[Question]
    find the idx of target in a unknown sized array
[Idea]
    expand search boundary twice each time to determine the minimum search boundary，then use binary search to solve
[Construction]
    determine the boundary
        R = 1
        while R item < target
            cur = R
            R *= 2
    binary search
        initialize L and R
        while (L <= R)
            get mid
            arr[mid] == target
                return idx
            arr[mid] == null || arr[mid] > target
                R = mid - 1
            arr[mid] < target
                L = mid + 1
*/
/*
[Code]
public class Solution {
  public int search(Dictionary dict, int target) {
    if (dict == null) {
        return -1;
    }
    int L = 0;
    int cur = 0;
    int R = 1;
    while (dict.get(R) != null && dict.get(R) < target) {
        cur = R;
        R *= 2;
    }
    int res = binarySearch(dict, target, L, R);
    return res;
  }

  public static int binarySearch(Dictionary dict, int target, int L, int R) {

    while (L <= R) {
        int mid = L + (R - L) / 2;
        if (dict.get(mid) == null || dict.get(mid) > target) {
            R = mid - 1;
        } else if (dict.get(mid) < target) {
            L = mid + 1;
        } else {
            return mid;
        }
    }
    return -1;
  }
}

*/

