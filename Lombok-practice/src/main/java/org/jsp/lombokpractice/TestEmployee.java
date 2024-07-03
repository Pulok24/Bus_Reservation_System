package org.jsp.lombokpractice;

public class TestEmployee {
public static void main(String[] args) {
	Employee e=new Employee(1,"sonu","Manager",80000,888777,"sonu@123");
	e.setDesg("Project Manager");
	System.out.println(e);
	
	Employee e2=new Employee();
	e2.setDesg("Engineer");
	e2.setSalary(90000);
	
	Employee e3=Employee.builder().name("Pulok").desg("trainer").salary(100000).password("pulok@123").id(1).phone(7777777).build();
	System.out.println(e3);
}
}
