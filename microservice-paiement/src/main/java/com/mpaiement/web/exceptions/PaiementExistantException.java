package com.mpaiement.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Philémon Globléhi <philemon.globlehi@gmail.com>
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class PaiementExistantException extends RuntimeException {

    public PaiementExistantException(String message) {
        super(message);
    }
}
