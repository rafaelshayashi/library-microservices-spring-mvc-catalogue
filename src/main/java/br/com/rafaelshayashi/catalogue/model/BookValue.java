package br.com.rafaelshayashi.catalogue.model;

import javax.persistence.Embeddable;

@Embeddable
public class BookValue {

    private String currency;
    private Integer amount;

    public BookValue() {
    }

    public BookValue(String currency, Integer amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public static BookValueBuilder builder() {
        return new BookValueBuilder();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer value) {
        this.amount = value;
    }

    public static final class BookValueBuilder {
        private String currency;
        private Integer amount;

        public BookValueBuilder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public BookValueBuilder amount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public BookValue build() {
            return new BookValue(currency, amount);
        }
    }
}
