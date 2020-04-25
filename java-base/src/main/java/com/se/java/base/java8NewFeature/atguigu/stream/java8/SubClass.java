package com.se.java.base.java8NewFeature.atguigu.stream.java8;

public class SubClass /*extends MyClass*/ implements MyFun, MyInterface{

	@Override
	public String getName() {
		return MyInterface.super.getName();
	}

}
