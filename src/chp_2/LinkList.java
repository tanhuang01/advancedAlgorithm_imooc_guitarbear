package chp_2;

public class LinkList {
    int data;
    LinkList next;

    public LinkList(int[] array){
        if (array.length < 1) return;
        LinkList p = this;
        p.data = array[0];
        for (int i = 1; i < array.length; i++) {
            p.next = new LinkList(array[i]);
            p = p.next;
        }
    }

    private LinkList(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        LinkList p = this;
        while (p != null) {
            builder.append(p.data).append("->");
            p = p.next;
        }
        builder.delete(builder.length() - 2, builder.length());
        return builder.toString();
    }
}
