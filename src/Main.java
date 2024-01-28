public class Main {
    public static void main(String[] args) {
        MyBST< Integer, Integer> test  = new MyBST <>() ;
        test.put(50 , 1);
        test.put(25 , 2);
        test.put(75 , 3);
        test.put(10 , 4);
        test.put(30 , 5);
        test.put(60 , 6);
        test.put(8 , 7);

        System.out.println(test.size());
    }
}