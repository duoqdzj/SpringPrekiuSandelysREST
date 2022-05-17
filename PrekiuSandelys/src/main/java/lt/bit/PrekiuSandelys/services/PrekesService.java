package lt.bit.PrekiuSandelys.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.bit.PrekiuSandelys.entities.Preke;
import lt.bit.PrekiuSandelys.repositories.PrekeRepository;

@Service
public class PrekesService {
	@Autowired
	PrekeRepository prekeRepository;
	
	public List<Preke> getPrekes(){
		return prekeRepository.findAll();
	}
	
	public Preke savePreke(Preke preke) {
		return prekeRepository.save(preke);
	}
	
	
	public Preke getPreke(Integer id) {
		return prekeRepository.findById(id).orElse(null);
	}
	
	
	public Preke updatePreke(Preke s) {
		Preke old=this.getPreke(s.getId());
		old.setName(s.getName());
		old.setSummary(s.getSummary());
		old.setQuantity(s.getQuantity());
		old.setPrice(s.getPrice());
		old.setPic(s.getPic());
		
		prekeRepository.save(old);
		return old;
	}
	
	public void deletePreke(Integer id) {
		prekeRepository.deleteById(id);
	}
	
	public Preke getByName(String name) {
		Preke s=prekeRepository.findByName(name);
		return s;
	}
	


}
