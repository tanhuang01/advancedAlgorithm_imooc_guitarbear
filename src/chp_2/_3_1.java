package chp_2;

import com.sun.jdi.connect.spi.TransportService;

public class _3_1 {

    static LinkList removeSpecified(LinkList linkList, int x) {
        if (linkList == null) {
            return null;
        }

        if (linkList.data != x) {
            linkList.next = removeSpecified(linkList.next, x);
            return linkList;
        } else {
            linkList = removeSpecified(linkList.next, x);
            return linkList;
        }
    }


    public static void main(String[] args) {
        LinkList linkList = new LinkList(new int[]{1, 2, 3, 4, 5, 6});
        LinkList linkList2 = new LinkList(new int[]{2, 4, 6, 8, 9, 10, 6, 6, 9, 8, 12});
        LinkList linkList3 = new LinkList(new int[]{2, 4});
        LinkList linkList4 = new LinkList(new int[]{2});

        linkList = removeSpecified(linkList, 2);
        linkList2 = removeSpecified(linkList2, 6);
        linkList3 = removeSpecified(linkList3, 4);
        linkList4 = removeSpecified(linkList4, 2);

        System.out.println(linkList);
        System.out.println(linkList2);
        System.out.println(linkList3);
        System.out.println(linkList4);

    }
}
