package com.pis1.entities;

public class Service {
    private long id;
    private String name;
    private long price;
    private String master_title;

    private long master_id;

    public Service(){

    }

    public Service(String name, long price, String master_title, long master_id) {
        this.name = name;
        this.price = price;
        this.master_title = master_title;
        this.master_id = master_id;
    }

    public Service(long id, String name, long price, String master_title, long master_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.master_title = master_title;
        this.master_id = master_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getMaster_title() {
        return master_title;
    }

    public void setMaster_title(String master_title) {
        this.master_title = master_title;
    }

    public long getMaster_id() {
        return master_id;
    }

    public void setMaster_id(long master_id) {
        this.master_id = master_id;
    }

    public void printService(){
        System.out.println(this.getId()+"  "+this.getName()+"  "+this.getPrice()+" UAH "+this.getMaster_title()+"  "+this.getMaster_id());

    }
}
