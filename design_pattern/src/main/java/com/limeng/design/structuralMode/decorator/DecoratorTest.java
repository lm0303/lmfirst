package com.limeng.design.structuralMode.decorator;

public class DecoratorTest {
    public static void main(String[] args) {
        LoginSsoDecorator loginSsoDecorator = new LoginSsoDecorator(new SsoInterceptor());
        loginSsoDecorator.preHandle("1successhuahua","ewcdqwt40liuiu","hander" );
    }
}
