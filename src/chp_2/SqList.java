package chp_2;

public class SqList {
    int[] data;
    int length;

    SqList() {
        data = new int[50];
        length = 0;
    }

    SqList(int[] array) {
        data = array;
        length = array.length;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(data[i]).append(" ");
        }
        builder.append("length:").append(length);
        return builder.toString();
    }
}
