package com.se.java.base.java8NewFeature.atguigu.stream.java8;

public interface MyInterface {
	//java8只能有静态变量和抽象方法
	default String getName(){
		return "呵呵呵";
	}
	
	public static void show(){
		System.out.println("接口中的静态方法");
	}

}
