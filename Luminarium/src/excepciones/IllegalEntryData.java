package excepciones;

/**
 * Excepción lanzada cuando se detectan datos de entrada incorrectos o inválidos.
 * Esta excepción se utiliza para indicar que los datos ingresados por el usuario no cumplen con ciertos criterios
 * o son incorrectos de alguna manera.
 */
public class IllegalEntryData extends Exception {
    
	private static final long serialVersionUID = 1L;

	public IllegalEntryData(String errorMesage){
        super(errorMesage);

    }

}