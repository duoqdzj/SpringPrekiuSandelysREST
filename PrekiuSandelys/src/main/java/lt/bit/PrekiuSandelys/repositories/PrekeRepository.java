package lt.bit.PrekiuSandelys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.bit.PrekiuSandelys.entities.Preke;

public interface PrekeRepository extends JpaRepository<Preke, Integer>{
	
	Preke findByName(String name);
	


}
