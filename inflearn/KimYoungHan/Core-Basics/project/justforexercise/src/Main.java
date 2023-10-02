public class Main {
    public static void main(String[] args) {
        ExampleBean exampleBean = new ExampleBean();
        AnotherBean beanOne = new AnotherBean();
        exampleBean.setBeanOne(beanOne);
        exampleBean.setIntegerProperty(1);
        System.out.println(exampleBean.getI());
    }
}