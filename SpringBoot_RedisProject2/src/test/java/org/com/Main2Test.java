package org.com;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class Main2Test {

    @Test
    public void test1(){
        int[] args={21,95,68,54,13,72,100,45,8,37};
        for (int i=0;i<args.length;i++){
//            System.out.println(args[i]);
            for (int j=0;j<args.length-i-1;j++){
                if (args[j]>args[j+1]){
                   int temp=args[j+1];
                    args[j+1]=args[j];
                    args[j]=temp;
                }
//                System.out.println(args[j]+"=="+args[j+1]);
            }
        }
        for (int k=0;k<args.length;k++){
            System.out.println(args[k]);
        }
    }
    @Test
    public void test2(){
        int[] a={25,32,83,99,74,52,87,3};
        int b=74;
        for (int i=0;i<a.length;i++) {
            if (b == a[i]) {
                System.out.println(b);
            }
        }
        for (int i=0;i<a.length;i++){
            for (int j=0;j<a.length-i-1;j++){
//                System.out.println(a[j]+"==="+a[j+1]);
                if (a[j]<a[j+1]){
                    int tem=a[j];
                    a[j]=a[j+1];
                    a[j+1]=tem;
                }
            }
        }
        for (int k=0;k<a.length;k++){
            System.out.println(a[1]);
            break;
        }
    }
}
