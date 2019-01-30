package cdct.dummyprovider.model;

public class HelloInput {

    private String name;

    public HelloInput()
    {

    }
    public HelloInput(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
