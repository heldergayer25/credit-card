package br.com.altbank.api.credit.card.filter;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.ws.rs.NameBinding;

/**
 * Anotação para filtro dos endpoints que devem usam a ApiKeyFilter.
 */
@NameBinding
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface ApiFilter {}

