package br.com.rafaelshayashi.catalogue.service;

import br.com.rafaelshayashi.catalogue.controller.request.BookRequest;
import br.com.rafaelshayashi.catalogue.model.Book;
import br.com.rafaelshayashi.catalogue.repository.BookRepository;
import br.com.rafaelshayashi.catalogue.exception.ResourceAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book create(BookRequest request) {

        Consumer<Book> bookConsumer = book -> {
            logger.info("Book already exists - ISBN [{}]", request.getIsbn());
            throw new ResourceAlreadyExistsException("isbn", book.getUuid().toString(), "Book already exists");
        };

        repository.findByIsbn(request.getIsbn()).ifPresent(bookConsumer);

        logger.info("Book created with success - ISBN [{}]", request.getIsbn());
        return repository.save(request.toModel());
    }

    @Override
    public Optional<Book> find(UUID bookUuid) {
        return repository.findByUuid(bookUuid);
    }

    @Override
    public Page<Book> list(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
