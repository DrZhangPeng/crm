package com.bjpowernode.bank.exceptions;

public class MoneyNotEnoughException extends Exception{
    public MoneyNotEnoughException(){}

    public MoneyNotEnoughException(String msg){
        super(msg);
    }
}
