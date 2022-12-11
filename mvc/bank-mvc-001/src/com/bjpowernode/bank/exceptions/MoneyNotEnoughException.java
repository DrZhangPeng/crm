package com.bjpowernode.bank.exceptions;

import java.security.PublicKey;

public class MoneyNotEnoughException extends Exception{
    public MoneyNotEnoughException(){}
    public MoneyNotEnoughException(String msg){super(msg);}
}
