public class FileSystem {
    public static void main(String[] args) {
        Folder fo1 = new Folder("Root");
        Folder fo2 = new Folder("A");
        Folder fo3 = new Folder("B");

        File f1 = new File("a.txt", 10);
        File f2 = new File("b.txt", 20);
        File f3 = new File("c.txt", 30);

        fo1.addItem(fo2);
        fo2.addItem(fo3);
        fo1.addItem(f1);
        fo2.addItem(f2);
        fo3.addItem(f3);

        System.out.println(f1.getSize());
        System.out.println(fo2.getSize());
        System.out.println(fo1.getSize());

        fo1.command();
    }
}
