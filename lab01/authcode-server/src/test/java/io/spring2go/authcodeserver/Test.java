package io.spring2go.authcodeserver;

/**
 * @author lijinde
 * @create 2022/5/16 11:31
 **/
public class Test {
    class A{
        public  int a ;
        public int b;
    }
    public void change1(A aa){
        aa.a = 2;
        System.out.println("1.    " + aa);
    }

    public void change2(A aa){
        A bb = new A();
        aa = bb;
        System.out.println("2.     "+aa);
        bb.a = 1 ;
        bb.b = 0;
    }

    public void changeString1(String cc){
        cc = "1";
    }

    public void changeString2(String cc){
        cc = "2";
    }

    @org.junit.Test
    public void testOject(){
        A ab = new A();
        System.out.println(ab);
        ab.a = 1;
        ab.b = 1;
        change1(ab);

        System.out.println(ab.a);
        System.out.println(ab.b);
        System.out.println("+++++++++++++++++++");
        change2(ab);
        System.out.println(ab.a);
        System.out.println(ab.b);

        String  c = "a";
        changeString1(c);
        System.out.println(c);
        changeString2(c);
        System.out.println(c);
        String d1 = ""+""+""+"";
        String d = "1"+"2"+"4"+"5";
    }

}
