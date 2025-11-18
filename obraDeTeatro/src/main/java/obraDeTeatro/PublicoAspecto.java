package obraDeTeatro;

import org.aspectj.lang.annotation.*;

@Aspect
public class PublicoAspecto {

    private Publico publico = new Publico();

    // Antes de comenzar la obra
    @Before("execution(* Obra.comenzarObra(..))")
    public void sentarseAntesDeComenzar() {
        publico.sentarse();
    }

    // Después del descanso
    @AfterReturning("execution(* Obra.descanso(..))")
    public void irAlBar() {
        publico.descanso();
    }

    // Antes de reanudar la obra
    @Before("execution(* Obra.reanudarObra(..))")
    public void sentarseAntesDeReanudar() {
        publico.sentarse();
    }

    // Después de terminar la obra exitosamente
    @AfterReturning("execution(* Obra.finObra(..))")
    public void aplaudirYACasa() {
        publico.aplaudir();
        publico.aCasa();
    }

    // Si ocurre una excepción al terminar la obra
    @AfterThrowing(pointcut = "execution(* Obra.finObra(..))", throwing = "e")
    public void abuchearYACasa(Exception e) {
        publico.abuchear();
        publico.aCasa();
    }
}
