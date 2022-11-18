package chp_2;

public class _2_3 {

    /**
     * remove all x from data in the sqList
     *
     * @param sqList
     * @param x
     */
    static void deleteRedundant(SqList sqList, int x) {
        if (sqList == null) {
            return;
        }
        int k = 0;
        for (int i = 0; i < sqList.length; i++) {
            if (sqList.data[i] != x) {
                sqList.data[k++] = sqList.data[i];
            }
        }
        sqList.length = k;
    }

    static void deleteRedundant2(SqList sqList, int x) {
        if (sqList == null) {
            return;
        }
        int k = 0;
        for (int i = 0; i < sqList.length; i++) {
            if (sqList.data[i] == x) {
                k++;
            } else {
                sqList.data[i - k] = sqList.data[i];
            }
        }
        sqList.length -= k;
    }

    public static void main(String[] args) {
        SqList sqList1 = new SqList(new int[]{1, 2, 3, 5, 6});
        SqList sqList2 = new SqList(new int[]{1, 1, 1, 1, 1, 1, 2});
        SqList sqList3 = new SqList(new int[]{1, 2, 3, 4, 6, 6, 6, 9, 9});

        deleteRedundant2(sqList1, 1);
        deleteRedundant2(sqList2, 1);
        deleteRedundant2(sqList3, 6);

        System.out.println(sqList1);
        System.out.println(sqList2);
        System.out.println(sqList3);
    }
}
