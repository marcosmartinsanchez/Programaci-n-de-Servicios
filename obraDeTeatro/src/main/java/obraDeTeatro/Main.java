package obraDeTeatro;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        try {
            @SuppressWarnings("resource")
			ApplicationContext context = new ClassPathXmlApplicationContext("configBeans.xml");
            Obra ob = (Obra) context.getBean("obra");
            ob.comenzarObra();
            ob.descanso();
            ob.reanudarObra();
            ob.finObra();
        } catch (Exception e) {
            // La excepción ya es manejada por el aspecto
        }
    }
}
