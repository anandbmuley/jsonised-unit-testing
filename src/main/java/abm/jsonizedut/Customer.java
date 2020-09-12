package abm.jsonizedut;

import java.util.function.Consumer;

public class Customer {

    private String id;
    private String name;
    private String contact;

    private Customer(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.contact = builder.contact;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public static class Builder {

        private String id;
        private String name;
        private String contact;

        private Builder() {
        }

        private Customer build() {
            return new Customer(this);
        }

        public static Customer with(Consumer<Builder> builderConsumer) {
            Builder builder = new Builder();
            builderConsumer.accept(builder);
            return builder.build();
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

    }

}
