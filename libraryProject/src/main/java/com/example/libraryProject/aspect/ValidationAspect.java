package com.example.libraryProject.aspect;

import com.example.libraryProject.model.Book;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationAspect {

    @Before("execution(* com.example.libraryProject.service.BookService.saveBook(..)) && args(book)")
    public void validateBookBeforeSaving(Book book) {
        if (book.getTitle() == null || book.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Book title cannot be null or empty");
        }
        System.out.println("ValidationAspect: Book validated successfully");
    }
}
