package co.circus.com.movieApiLab.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.circus.com.movieApiLab.entity.Movie;


public interface MovieApiDao extends JpaRepository<Movie, Long> {
	
	List<Movie> findByTitleContainsIgnoreCase(String titleMatch); //#8 Title
	
	List<Movie> findByCategory(String categoryMatch); //#2 Category
			
		@Query("SELECT DISTINCT category FROM movie")
		List<String> findAllCategories();
	
	
	
	

}
