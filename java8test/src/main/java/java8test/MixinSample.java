package java8test;


public class MixinSample implements B, D {

	public static void main(String[] args) {
		new MixinSample().greet(); // --> Hi C++
	}
}

interface A {
	default void greet() {
		System.out.println("Hi World!");
	}
}

interface B extends A {
	default void greet() {
		System.out.println("Hi Java!");
	}
}

interface C extends A {
	default void greet() {
		System.out.println("Hi C++");
	}
}

interface D extends B, C {
	default void greet() {
		C.super.greet();
	}
}
