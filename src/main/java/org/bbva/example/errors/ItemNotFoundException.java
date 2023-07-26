package org.bbva.example.errors;

public class ItemNotFoundException extends RuntimeException{

    private Long id;

    public ItemNotFoundException(Long id) {
        super("Cloud not find item " + id);
    }

}
