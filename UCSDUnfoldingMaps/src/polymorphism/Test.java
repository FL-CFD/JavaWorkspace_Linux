package polymorphism;


public class Test extends S {
	
	
    public void printMethod(int n) {
        System.out.format("Print %d in subclass\n", n);
        if (n <= 0) {
            super.printMethod(n);
        }
        else {
            this.printMethod(n-1);
        }
    }
    

    public static void main(String[] args) {
        S c = new Test();
        c.printMethod(5);
    }
    
   
    
}
