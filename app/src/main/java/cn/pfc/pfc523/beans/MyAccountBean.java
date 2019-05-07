package cn.pfc.pfc523.beans;

public class MyAccountBean {
    int id;
    int car_id;
    int money;
    String ren;
    String time;

    public MyAccountBean(int id, int car_id, int money, String ren, String time) {
        this.id = id;
        this.car_id = car_id;
        this.money = money;
        this.ren = ren;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public int getCar_id() {
        return car_id;
    }

    public int getMoney() {
        return money;
    }

    public String getRen() {
        return ren;
    }

    public String getTime() {
        return time;
    }
}
