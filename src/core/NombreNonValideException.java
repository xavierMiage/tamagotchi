package core;

public class NombreNonValideException extends Exception{  
	
	/** 
	* Crée une nouvelle instance de NombreNonValideException 
	*/  
	public NombreNonValideException() {}  
	
	/** 
	* Crée une nouvelle instance de NombreNonValideException 
	* @param message Le message détaillant exception 
	*/  
	public NombreNonValideException(String message) {  
		super(message); 
	}  
	
	/** 
	* Crée une nouvelle instance de NombreNonValideException 
	* @param cause L'exception à l'origine de cette exception 
	*/  
	public NombreNonValideException(Throwable cause) {  
		super(cause); 
	}  
	
	/** 
	* Crée une nouvelle instance de NombreNonValideException 
	* @param message Le message détaillant exception 
	* @param cause L'exception à l'origine de cette exception 
	*/  
	public NombreNonValideException(String message, Throwable cause) {  
		super(message, cause); 
	}
}
