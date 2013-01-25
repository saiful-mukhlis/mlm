package z.gen;

import java.lang.reflect.Field;

import org.basic.comp.base.TextField;

import com.basic.annotation.db.Type;
import com.basic.db.FUsr;
import com.google.common.base.CaseFormat;

public class CreateSave {

	private String nama;
	
	public CreateSave(Class klas) {
		super();
		StringBuilder s=new StringBuilder();
		
		
		Field[]  x=klas.getDeclaredFields();
		for (Field f : x) {
			
			String konstatnta=f.getName();
			String lc=CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, konstatnta);
			String uc=CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, konstatnta);
			
			
			
			s.append("\n");
			//d.setCode(model, code);
			s.append("d.set"+uc+"(model,"+lc+");");
			
		}
		
		System.out.println(s.toString());
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}
	
	

}
