package Model;

public class Order {

    private Customer customer;
    private String order_date;
    private String dalivery_date;
    private String office;
    private String factory;
    private Car car;

    public Order(Customer customer, String order_date, String dalivery_date, String office, String factory, Car car) {
        this.customer = customer;
        this.order_date = order_date;
        this.dalivery_date = dalivery_date;
        this.office = office;
        this.factory = factory;
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getDalivery_date() {
        return dalivery_date;
    }

    public void setDalivery_date(String dalivery_date) {
        this.dalivery_date = dalivery_date;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
