public abstract class Communication {
    String number1;
    String number2;
    int date;
    int month;
    int year;

    public Communication(String number1, String number2, int date, int month, int year){
        this.number1 = number1;
        this.number2 = number2;
        this.date = date;
        this.month = month;
        this.year = year;
    }

    public  abstract void printInfo();
}
