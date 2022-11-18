package chp_2;

public class _2_6 {

    static void deleteRedundant(SqList sqList) {
        if (sqList == null) {
            return;
        }

        int k = 0;
        for (int i = 1; i < sqList.length; i++) {
            if (sqList.data[k] != sqList.data[i]) {
                sqList.data[++k] = sqList.data[i];
            }
        }
        k++;
        sqList.length = k;
    }



    public static void main(String[] args) {
        SqList sqList1 = new SqList(new int[]{1, 2, 3, 5, 6});
        SqList sqList2 = new SqList(new int[]{1, 1, 1, 1, 1, 1, 2});
        SqList sqList3 = new SqList(new int[]{1, 2, 3, 4, 6, 6, 6, 9, 9});

        deleteRedundant(sqList1);
        deleteRedundant(sqList2);
        deleteRedundant(sqList3);

        System.out.println(sqList1);
        System.out.println(sqList2);
        System.out.println(sqList3);
    }
}
