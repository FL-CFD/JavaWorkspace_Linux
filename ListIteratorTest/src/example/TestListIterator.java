package example;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class TestListIterator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		  List<String> staff = new LinkedList<>();
		  staff.add("zhuwei");
		  staff.add("xuezhangbin");
		  staff.add("taozhiwei");
		  ListIterator<String> iter = staff.listIterator();
		  String first = iter.next();
		  
		  //删除zhuwei
		  iter.remove();
		  
		  //把zhuwei改为simei
		  //iter.set("simei");
		  System.out.println("first:"+first);
		  
		  iter.add("xiaobai");
		  
		  //遍历List元素
		  System.out.println("遍历List中元素，方法一：");
		  for(String str : staff)
		   System.out.println(str+"  ");
		  
		  iter = staff.listIterator();
		  System.out.println("遍历List中元素，方法二：");
		  while(iter.hasNext())
		  {
		   System.out.println(iter.next());
		  }
		 
	}

}
