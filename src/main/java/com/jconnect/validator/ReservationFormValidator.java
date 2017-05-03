package com.jconnect.validator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.jconnect.form.PiloteForm;
import com.jconnect.form.ReservationForm;
@Component ("reservationFormValidator")
public class ReservationFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return PiloteForm.class.equals( arg0);
	}
	@Override
	public void validate(Object obj, Errors errors) {
		ReservationForm reservationform = (ReservationForm) obj;
		
//		DateFormat df= new SimpleDateFormat("mm/dd/yyyy,HH:mm");
//		reservationform.setDateDepart(df.parse(reservationform.getDateDepart()));
//		
			//String dateDepart=reservationform.getDateDepart();
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "villeDepart", "reservation.villeDepart.required");
			
			
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "villeArrivee", "reservation.villeArrivee.required");
			
			
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateDepart", "reservation.dateDepart.required");
				
				
			
			
		
	}

}


