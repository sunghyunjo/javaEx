class File extends BaseSystem {
    private int size;

    File(String title, int size) {
        super(title);
        this.size = size;
    }

    @Override
    int getSize() {
        return size;
    }

    @Override
    void command() {
        System.out.println("선택된 파일은 " + getTitle() +"입니다.");
    }

}