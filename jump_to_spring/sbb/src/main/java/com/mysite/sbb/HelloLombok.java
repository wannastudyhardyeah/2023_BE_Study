package com.mysite.sbb;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/*
    public HelloLombok(String hello, int lombok) {
        this.hello = hello;
        this.lombok = lombok;
    }
위와 같다. */
// 이 애너테이션은 이후에 의존성 주입(DI) 시 사용됨.
// (DI: 스프링이 객체를 대신 생성하여 주입 )
@RequiredArgsConstructor
@Getter
public class HelloLombok {
    private final String hello;
    private final int lombok;


    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok("헬로", 5);

        System.out.println(helloLombok.getHello());
        System.out.println(helloLombok.getLombok());

    }
}
