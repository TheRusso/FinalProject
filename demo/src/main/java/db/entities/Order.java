package db.entities;

public class Order extends Entity{
    private Long user_id;
    private String address;
    private String city;
    private Long country_id;
    private Long delivery_type_id;
    private Long status_id;


    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Long country_id) {
        this.country_id = country_id;
    }

    public Long getDelivery_type_id() {
        return delivery_type_id;
    }

    public void setDelivery_type_id(Long delivery_type_id) {
        this.delivery_type_id = delivery_type_id;
    }

    public Long getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Long status_id) {
        this.status_id = status_id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "user_id=" + user_id +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country_id=" + country_id +
                ", delivery_type_id=" + delivery_type_id +
                ", status_id=" + status_id +
                '}';
    }
}
