package com.algaworks.socialbooks.app.client;

import com.algaworks.socialbooks.app.domain.Livro;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

/**
 * @author: Murillo Cesar
 */

public class LivrosClient {

    private RestTemplate restTemplate;
    private String URI_BASE;
    private final String URN_BASE = "/livros";
    private String credencial;

    public LivrosClient(String url, String usuario, String senha) {
        this.restTemplate = new RestTemplate();
        URI_BASE = url.concat(URN_BASE);

        String credencialAux = usuario + ":" + senha;
        credencial = "Basic " + Base64.getEncoder().encodeToString(credencialAux.getBytes());

    }


    public List<Livro> listar() {

        RequestEntity<Void> requestEntity = RequestEntity.get(URI.create(URI_BASE))
                .header("Authorization", credencial).build();

        ResponseEntity<Livro[]> responseEntity = restTemplate.exchange(requestEntity, Livro[].class);

        return Arrays.asList(responseEntity.getBody());

    }


    public String salvar(Livro livro) {


        RequestEntity<Livro> requestEntity = RequestEntity
                .post(URI.create(URI_BASE))
                .header("Authorization", credencial)
                .body(livro);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(requestEntity, Void.class);

        return Objects.requireNonNull(responseEntity.getHeaders().getLocation()).toString();
    }


    public Livro buscar(String uri) {

        RequestEntity<Void> requestEntity = RequestEntity
                .get(URI.create(uri))
                .header("Authorization", credencial)
                .build();
        ResponseEntity<Livro> responseEntity = restTemplate.exchange(requestEntity, Livro.class);
        return responseEntity.getBody();
    }
}
