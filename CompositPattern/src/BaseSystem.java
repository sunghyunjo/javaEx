abstract class BaseSystem {
    private String title;

    BaseSystem(String title) {
        this.title = title;
    }

    abstract int getSize();

    abstract void command();

    String getTitle() {
        return title;
    }
}
