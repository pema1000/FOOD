package com.example.food;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Getterforview implements Comparable<Getterforview>{


    String productName,productDoe;
    Integer idnumber,productAmount;

    public Getterforview() {

    }

    public Getterforview(Integer idnumber,String productName, Integer productAmount, String productDoe) {
        this.idnumber=idnumber;
        this.productName = productName;
        this.productAmount = productAmount;
        this.productDoe = productDoe;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Integer productAmount) {
        this.productAmount = productAmount;
    }

    public String getProductDoe() {
        return productDoe;
    }

    public void setProductDoe(String productDoe) {
        this.productDoe = productDoe;
    }
    public Integer getId() {
        return idnumber;
    }

    public void setId(Integer idnumber) { this.idnumber = idnumber;
    }

    @Override
    public int compareTo(Getterforview o) {
        return this.idnumber- o.getId();
    }
 /*
    @Override
    public int compareTo(Getterforview o) {
        return Integer.valueOf(this.idnumber)- Integer.valueOf(o.getId());
    }*/

    public static Comparator<Getterforview> pname=new Comparator<Getterforview>() {
        @Override
        public int compare(Getterforview p1, Getterforview p2) {
            return p1.getProductName().compareTo(p2.getProductName());
        }
    };

    public static Comparator<Getterforview> pamount=new Comparator<Getterforview>() {
        @Override
        public int compare(Getterforview a1, Getterforview a2) {
            return Integer.valueOf(a1.getProductAmount())-(Integer.valueOf(a2.getProductAmount()));
        }
    };
public static Comparator<Getterforview> pdoe=new Comparator<Getterforview>() {
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    Date d1,d2;
    @Override
    public int compare(Getterforview c1, Getterforview c2) {

        try {
            d1 = dateFormat.parse(c1.getProductDoe());
            d2=dateFormat.parse(c2.getProductDoe());
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return d1.compareTo(d2);
      //  return c1.getProductName().compareTo(c2.getProductName());
    }
};
}
