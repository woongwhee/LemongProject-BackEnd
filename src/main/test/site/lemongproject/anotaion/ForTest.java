package site.lemongproject.anotaion;

import org.junit.Test;
import site.lemongproject.web.feed.model.vo.Feed;
import site.lemongproject.web.feed.model.vo.Reply;
import site.lemongproject.web.member.model.vo.Member;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class ForTest {

    public int[]  getArray(){
        int[] arr=new int[100000];
        for (int i = 0; i < 100000; i++) {
            arr[i]=(int)(Math.random()+1)*10000;
        }
        return arr;
    }
    public LinkedList getLinkedList(){
        LinkedList<Integer> list=new LinkedList<>();
        for (int i = 0; i < 100000; i++) {
            list.add((int)(Math.random()+1)*10000);
        }
        return list;
    }
    public ArrayList getArrayList(){
        ArrayList<Integer> list=new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list.add((int)(Math.random()+1)*10000);
        }
        return list;
    }

    @Test
    public void  LinkedListforEach(){
        List<Integer> l=getLinkedList();
        int result=0;
        double s=System.currentTimeMillis();

        for (Integer i: l) {
            result+=i;
        }
        double e=System.currentTimeMillis();

        System.out.println("result:"+result+"\ntime"+(e-s));
    }
    @Test
    public void  LinkedListfor(){
        List<Integer> l=getLinkedList();
        int result=0;
        double s=System.currentTimeMillis();

        for (int i=0; i<l.size();i++) {
            result+=l.get(i);
        }
        double e=System.currentTimeMillis();

        System.out.println("result:"+result+"\ntime"+(e-s));
    }
    @Test
    public void  ArrayListforEach(){
        List<Integer> l=getArrayList();
        int result=0;
        double s=System.currentTimeMillis();

        for (Integer i: l) {
            result+=i;
        }
        double e=System.currentTimeMillis();

        System.out.println("result:"+result+"\ntime"+(e-s));
    }
    @Test
    public void  ArrayListfor(){
        List<Integer> l=getArrayList();
        int result=0;
        double s=System.currentTimeMillis();

        for (int i=0; i<l.size();i++) {
            result+=l.get(i);
        }
        double e=System.currentTimeMillis();

        System.out.println("result:"+result+"\ntime"+(e-s));
    }
    @Test
    public void  ArrayforEach(){
        int[] arr=getArray();
        int result=0;
        double s=System.currentTimeMillis();
        for (int i: arr) {
            result+=i;
        }
        double e=System.currentTimeMillis();

        System.out.println("result:"+result+"\ntime"+(e-s));
    }
    @Test
    public void  Arrayfor(){
        int[] arr=getArray();
        int result=0;
        double s=System.currentTimeMillis();
        for (int i=0; i<arr.length;i++) {
            result+=arr[i];
        }
        double e=System.currentTimeMillis();
        System.out.println("result:"+result+"\ntime"+(e-s));
    }

    @Test
    public void genericTest(){
    }




}
