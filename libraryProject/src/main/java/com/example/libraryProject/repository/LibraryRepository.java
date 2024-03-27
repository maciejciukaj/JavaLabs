package com.example.libraryProject.repository;

import com.example.libraryProject.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Long> {
}
