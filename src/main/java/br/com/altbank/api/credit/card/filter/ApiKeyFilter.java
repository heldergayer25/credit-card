package br.com.altbank.api.credit.card.filter;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

/**
 * Realiza o filtro de requisições para validação da ApiKeyFilter.
 */
@ApiFilter
@Provider
public class ApiKeyFilter implements ContainerRequestFilter {

    @Inject
    @ConfigProperty(name = "tracking.api.key", defaultValue = "")
    private String trackingApiKey;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String apiKey = requestContext.getHeaderString("X-API-Key");
        if (!this.trackingApiKey.equals(apiKey)) {
            requestContext.abortWith(Response.status(401).build());
        }
    }
}
