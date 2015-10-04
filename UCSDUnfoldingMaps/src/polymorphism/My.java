package polymorphism;

public class My extends Test {
	My(){
		super();
	}
	

    public static void main(String[] args) {
        Test c = new My();
        c.printMethod(5);
    }
	
}