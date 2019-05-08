package cn.pfc.pfc523.beans;

public class BusQueryBean {
    String num,time,distance;

    public BusQueryBean(String num, String time, String distance) {
        this.num = num;
        this.time = time;
        this.distance = distance;
    }

    public BusQueryBean(String time, String distance) {
        this.time = time;
        this.distance = distance;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNum() {
        return num;
    }

    public String getTime() {
        return time;
    }

    public String getDistance() {
        return distance;
    }
}
