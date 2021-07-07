package com.example.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.document.entity.Document;

@Repository
public interface DocumentRepo extends JpaRepository<Document, Long> {

}
