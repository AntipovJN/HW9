public class App {

    public static void main(String[] args) {
        OwnLinkedList<String> stringOwnLinkedList = new OwnLinkedList<>();
        System.out.println(stringOwnLinkedList.isEmpty());
        stringOwnLinkedList.add("Hello");
        stringOwnLinkedList.add("bye");
        stringOwnLinkedList.add("second", 0);
        OwnLinkedList<String> stringOwnLinkedList2 = new OwnLinkedList<>();
        stringOwnLinkedList2.add("123");
        stringOwnLinkedList2.add("321");
        stringOwnLinkedList2.add("444");
        stringOwnLinkedList.addAll(stringOwnLinkedList2);
        stringOwnLinkedList.set("change", 4);
        stringOwnLinkedList.remove(0);
        System.out.println(stringOwnLinkedList.toString());
    }
}
