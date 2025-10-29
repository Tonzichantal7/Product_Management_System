package org.example.productmanagementsystem.Exception;

public class ProductNotFoundException extends RuntimeException{
    private String message;
    public ProductNotFoundException(){}
    public ProductNotFoundException(String msg){
        super(msg);
        this.message =msg;
    }
}
