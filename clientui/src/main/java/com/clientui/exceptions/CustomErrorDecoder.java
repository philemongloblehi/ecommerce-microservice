package com.clientui.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * @author Philémon Globléhi <philemon.globlehi@gmail.com>
 */
public class CustomErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String invoqeur, Response response) {
        if (400 == response.status()) {
            return new ProductBadRequestException("Reqête incorrecte");
        } else if (404 == response.status()) {
            return new ProductNotFoundException(
                    "Produit non trouvé "
            );
        }

        return defaultErrorDecoder.decode(invoqeur, response);
    }
}
