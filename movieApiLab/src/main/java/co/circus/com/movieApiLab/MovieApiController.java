package co.circus.com.movieApiLab;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.circus.com.movieApiLab.dao.MovieApiDao;
import co.circus.com.movieApiLab.entity.Movie;

@RestController
public class MovieApiController {

	@Autowired
	MovieApiDao dao;

	@GetMapping("/movie")
	public List<Movie> listMovie(@RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "title", required = false) String title) {

		if (category == null || category.isEmpty() && title == null || title.isEmpty()) {
			return dao.findAll();

		} else if (title == null || title.isEmpty()) {
			return dao.findByCategory(category);
		}

		else {
			return dao.findByTitleContainsIgnoreCase(title);
		}
	}

	@GetMapping("/movie/random")
	public Movie randomMovie(@RequestParam(value = "category", required = true) String category) {

		if (category == null || category.isEmpty()) {
			Long rand = (long) (Math.random() * 10) + 1;
			return dao.findById(rand).get();

		} else {
			List<Movie> catlist = dao.findByCategory(category);
			int rand = (int) (Math.random() * catlist.size()) + 1;
			return catlist.get(rand - 1);

		}

	}
	
//	@GetMapping("/movies/random-list")
//	public List<Movie> listrandom(
//			@RequestParam(value = "quantity", required=true) Integer quantity){
//		    List<Movie> randomlist = dao.findAll();
//		    
//	}

	@GetMapping("/categories")
	public List<String> listCategories() {
		return dao.findAllCategories();
	}

	@GetMapping("/movie/{id}")
	public Movie listMovie(@PathVariable("id") Long id) {
		return dao.findById(id).get();
	}

}
