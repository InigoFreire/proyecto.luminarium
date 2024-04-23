package excepciones;

public class IllegalEntryData extends Exception {
    
	private static final long serialVersionUID = 1L;

	public IllegalEntryData(String errorMesage){
        super(errorMesage);

    }

}