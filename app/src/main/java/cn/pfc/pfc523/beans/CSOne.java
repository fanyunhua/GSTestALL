package cn.pfc.pfc523.beans;

public class CSOne {
    private String way,time1,time2;
    private int money,mileage;

    public CSOne(String way, String time1, String time2, int money, int mileage) {
        this.way = way;
        this.time1 = time1;
        this.time2 = time2;
        this.money = money;
        this.mileage = mileage;
    }

    public String getWay() {
        return way;
    }

    public String getTime1() {
        return time1;
    }

    public String getTime2() {
        return time2;
    }

    public int getMoney() {
        return money;
    }

    public int getMileage() {
        return mileage;
    }
}
