package lt.bit.PrekiuSandelys.restControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;



import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import lt.bit.PrekiuSandelys.Exeptions.ErrorResponse;
import lt.bit.PrekiuSandelys.Exeptions.PrekeException;
import lt.bit.PrekiuSandelys.entities.Preke;
import lt.bit.PrekiuSandelys.services.PrekesService;

@RestController 
@RequestMapping("/prekes")
public class PrekesController {
	@Autowired
	PrekesService prekesService;
	
	@ExceptionHandler(value = {JacksonException.class})
	public ResponseEntity<ErrorResponse> invalidData() {
		return new ResponseEntity<>(new ErrorResponse("Invalid input", "",400), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value= {PrekeException.class})
	public ResponseEntity<ErrorResponse> prekeException(PrekeException e){
		return new ResponseEntity<>(new ErrorResponse(e.getMessage(),"",e.getCode()), HttpStatus.valueOf(e.getCode()));
	}
	
	@ExceptionHandler(value= {Exception.class})
	public ResponseEntity<ErrorResponse> defaultException(Exception e){
		return new ResponseEntity<>(new ErrorResponse("Unknown error ","",500), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@CrossOrigin 
	@GetMapping("/")
	public List<Preke> index() throws InterruptedException{
		return prekesService.getPrekes();
	}
	
	
	@CrossOrigin
	@GetMapping("/{id}")
	public Preke get(@PathVariable Integer id) {
		Preke s=prekesService.getPreke(id);
		if (s==null) {
			 throw new PrekeException("Preke su nurodytu ID nerasta",404);
		}
		return s;
	}
	
	@CrossOrigin
	@PostMapping("/")
	public Preke add(@RequestBody Preke s ){
		Preke se=prekesService.getByName(s.getName());
		if (se==null) {
			return prekesService.savePreke(s);
		}
		throw new PrekeException("Preke su nurodytu pavadinimu egzistuoja", 400);
	}
	
	
	
	
	@CrossOrigin
	@PatchMapping("/{id}")
	public Preke update(@PathVariable Integer id, @RequestBody Preke s) {
		if (s.getQuantity()< 0) {
			throw new PrekeException("kiekis yra neigiamas - lygus 0",400);
		}
		
		if (s.getPrice()< 0) {
			throw new PrekeException("kaina negali buti neigiama",400);
		}
		
		Preke se=prekesService.getByName(s.getName());
		if (se==null || se.getId()==s.getId()) {
			return prekesService.updatePreke(s);
		}
		
		throw new PrekeException("Preke su nurodytu pavadinimu jau  egzistuoja", 400);
	}
	
	@CrossOrigin
	@DeleteMapping("/{id}")
	public Boolean delete(@PathVariable Integer id) {
		Preke s=prekesService.getPreke(id);
		if (s.getQuantity()> 0) {
			throw new PrekeException("Negalima pašalinti jei kiekis - ne lygus 0",400);
			
		}
		prekesService.deletePreke(id);
		 return true;
	}
	
	@CrossOrigin
	@GetMapping("/name/{name}")
	public Boolean newEmail(@PathVariable String name) {
		  return (prekesService.getByName(name)==null);
	}
	
	@CrossOrigin
	@GetMapping("/{id}/name/{name}")
	public Boolean updateName(@PathVariable Integer id, @PathVariable String name ) {
		  Preke s= prekesService.getByName(name);
		  if (s!=null) {
			  if (s.getId()==id) {
				  return true;
			  }else {
				  return false;
			  }
		  }else {
			  return true;
		  }
	}


}
