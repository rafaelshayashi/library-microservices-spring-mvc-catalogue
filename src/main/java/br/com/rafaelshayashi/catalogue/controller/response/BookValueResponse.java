package br.com.rafaelshayashi.catalogue.controller.response;

import br.com.rafaelshayashi.catalogue.model.BookValue;

public class BookValueResponse {

    private final String currency;
    private final Integer amount;

    public BookValueResponse() {
        this.currency = null;
        this.amount = null;
    }

    public BookValueResponse(String currency, Integer amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public static BookValueResponse of(BookValue value) {
        if(value == null){
            return new BookValueResponse();
        }
        return new BookValueResponse(value.getCurrency(), value.getAmount());
    }

    public String getCurrency() {
        return currency;
    }

    public Integer getAmount() {
        return amount;
    }

}
