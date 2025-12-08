package src.basic;
public class Address {
    private Long id;
    private String city;
    private String street;
    private String zipCode;

    // Konstruktor
    public Address(Long id, String city, String street, String zipCode) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    // Gettery (zgodnie z prostą semantyką piszemy je ręcznie)
    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }

    // Opcjonalnie toString, żeby ładnie wypisywać w konsoli
    @Override
    public String toString() {
        return street + ", " + city + " " + zipCode;
    }
}
