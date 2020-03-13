package lv.accenture.bootcamp.rardb.controller;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lv.accenture.bootcamp.rardb.model.Movie;


@Repository
public interface MovieRepository extends CrudRepository<Movie,String>{
	
} 

