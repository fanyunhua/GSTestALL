package cn.pfc.pfc523.beans;

public class CarWzBean {
    int url=0;
    String name="";

    public CarWzBean(int url, String name) {
        this.url = url;
        this.name = name;
    }

    public int getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }
}
