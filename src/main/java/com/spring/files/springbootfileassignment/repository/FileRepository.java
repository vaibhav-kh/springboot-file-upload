package com.spring.files.springbootfileassignment.repository;

import org.springframework.data.repository.CrudRepository;

import com.spring.files.springbootfileassignment.model.FileInfo;

public interface FileRepository extends CrudRepository<FileInfo, String>{

}
