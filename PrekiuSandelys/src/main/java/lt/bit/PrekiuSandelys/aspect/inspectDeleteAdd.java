package lt.bit.PrekiuSandelys.aspect;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import lt.bit.PrekiuSandelys.entities.Preke;
import lt.bit.PrekiuSandelys.services.PrekesService;

@Aspect
@Component
public class inspectDeleteAdd {

	
	@AfterReturning(pointcut = "execution(* getPrekes())", returning = "result")
	public void afterHideSurnames(List<Preke> result) {
		
		
			for(Preke s:result) {
				Double str = s.getPrice();
				Double cap = str*1.21;
				s.setPrice(cap);
			}
		
	}
	
	@Before("execution(* savePreke(..))")
	public void beforeNewSauktinisCorrect(JoinPoint jp) {
		Preke s=(Preke)jp.getArgs()[0];
		
		String str = s.getName();
		String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
		
		s.setName(cap);
	}
	
	@Before("execution(* updatePreke(..))")
	public void beforeNewSauktinisCorrectupdate(JoinPoint jp) {
		Preke s=(Preke)jp.getArgs()[0];
		
		String str = s.getName();
		String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
		
		s.setName(cap);
	}
	
	

	

}
