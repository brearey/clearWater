package ru.oktemsec.clearwater_admin_2;

public class ZakazData {

    private int day;
    private int month;
    private int reis;
    private int skolko;
    private int summa;
    private boolean tara;
    private int timeHours;
    private int timeMinutes;
    private boolean urlico;
    private String address;
    private String selo;
    private  long phone;

    public ZakazData(){

    }

    public ZakazData(int day, int month, int reis, int skolko, int summa, boolean tara, int timeHours, int timeMinutes, boolean urlico, String address, String selo, long phone) {
        this.day = day;
        this.month = month;
        this.reis = reis;
        this.skolko = skolko;
        this.summa = summa;
        this.tara = tara;
        this.timeHours = timeHours;
        this.timeMinutes = timeMinutes;
        this.urlico = urlico;
        this.address = address;
        this.selo = selo;
        this.phone = phone;
    }

    //getters

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getReis() {
        return reis;
    }

    public int getSkolko() {
        return skolko;
    }

    public int getSumma() {
        return summa;
    }

    public boolean isTara() {
        return tara;
    }

    public int getTimeHours() {
        return timeHours;
    }

    public int getTimeMinutes() {
        return timeMinutes;
    }

    public boolean isUrlico() {
        return urlico;
    }

    public String getAddress() {
        return address;
    }

    public String getSelo() {
        return selo;
    }

    public long getPhone() {
        return phone;
    }

    //setters


    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setReis(int reis) {
        this.reis = reis;
    }

    public void setSkolko(int skolko) {
        this.skolko = skolko;
    }

    public void setSumma(int summa) {
        this.summa = summa;
    }

    public void setTara(boolean tara) {
        this.tara = tara;
    }

    public void setTimeHours(int timeHours) {
        this.timeHours = timeHours;
    }

    public void setTimeMinutes(int timeMinutes) {
        this.timeMinutes = timeMinutes;
    }

    public void setUrlico(boolean urlico) {
        this.urlico = urlico;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSelo(String selo) {
        this.selo = selo;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }
}
