package cn.pfc.pfc523.beans;

import android.graphics.Bitmap;

public class T63Bean {
    Bitmap bitmap;
    String name;
    String money;
    int stars;
    String tel;
    String infor;

    public T63Bean(Bitmap bitmap, String name, String money) {
        this.bitmap = bitmap;
        this.name = name;
        this.money = money;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getInfor() {
        return infor;
    }

    public void setInfor(String infor) {
        this.infor = infor;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public String getName() {
        return name;
    }

    public String getMoney() {
        return money;
    }

    public T63Bean() {
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
