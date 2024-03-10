package com.example.libraryProject.controller;

import com.example.libraryProject.bookReport.BookReportFactory;
import com.example.libraryProject.interfaces.BookReport;
import com.example.libraryProject.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BookReportController {

    @Autowired
    private BookReportFactory bookReportFactory;

    @GetMapping("/books/report/{genre}")
    public String generateReport(@PathVariable("genre") String genre, Model model) {
        BookReport report = bookReportFactory.createBookReport(genre);
        List<Book> books = report.generateReport();
        model.addAttribute("books", books);
        model.addAttribute("genre", genre);
        return "book-report";
    }
}

