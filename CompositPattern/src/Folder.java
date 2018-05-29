import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Folder extends BaseSystem {
    private List<BaseSystem> baseSystems;

    Folder(String title) {
        super(title);
        this.baseSystems = new ArrayList<>();
    }

    void addItem(BaseSystem file) {
        baseSystems.add(file);
    }

    @Override
    int getSize() {
        int folderSize = 0;

        for (BaseSystem file : baseSystems)
            folderSize += file.getSize();
        return folderSize;
    }

    @Override
    public void command() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int size = baseSystems.size();
            for (int i = 0; i < size; ++i) {
                System.out.printf("%2d. %s\n", i + 1,
                        baseSystems.get(i).getTitle());
            }
            System.out.printf("%2d. 상위 메뉴로\n", size + 1);
            System.out.print("메뉴를 선택하세요 >> ");

            int cmd = scanner.nextInt();

            if (cmd < 1 || cmd > size + 1)
                continue;
            if (cmd == size + 1)
                break;
            baseSystems.get(cmd - 1).command();
        }
    }
}