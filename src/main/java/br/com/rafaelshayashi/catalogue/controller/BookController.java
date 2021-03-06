package br.com.rafaelshayashi.catalogue.controller;

import br.com.rafaelshayashi.catalogue.controller.request.BookRequest;
import br.com.rafaelshayashi.catalogue.controller.response.BookResponse;
import br.com.rafaelshayashi.catalogue.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('librarian')")
    public ResponseEntity<BookResponse> create(@RequestBody @Valid BookRequest request,
                                               @AuthenticationPrincipal Jwt jwt,
                                               UriComponentsBuilder uriBuilder) {
        request.setUserId(jwt.getSubject());
        var bookResponse = BookResponse.of(service.create(request));
        var uri = uriBuilder.path("/books/{uuid}").buildAndExpand(bookResponse.getUuid()).toUri();
        return ResponseEntity.created(uri).body(bookResponse);
    }

    @GetMapping
    public Page<BookResponse> list(Pageable pageable) {
        List<BookResponse> bookResponseList = service.list(pageable)
                .stream()
                .map(BookResponse::new)
                .collect(Collectors.toList());
        return new PageImpl<>(bookResponseList);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<BookResponse> details(@PathVariable String uuid) {
        return service.find(UUID.fromString(uuid))
                .map(book -> ResponseEntity.ok().body(BookResponse.of(book)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable String uuid,
                                                   @AuthenticationPrincipal Jwt jwt,
                                                   @RequestBody BookRequest request){
        request.setUserId(jwt.getSubject());
        return service.update(UUID.fromString(uuid), request)
                .map(book -> ResponseEntity.ok().body(BookResponse.of(book)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
