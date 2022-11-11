package moviepackage;

/**
 *  Enumeration of All Possible Age Restrictions for Movies.
 *  Used in Customer and Staff Classes.
 */
public enum AgeRestriction {
	GENERAL("General"),
	PG("PG"),
	PG13("PG13"),
	M18("M18"),
	R21("R21");
	private final String textRepresenatation;
    private AgeRestriction(String textRepresentation){
        this.textRepresenatation = textRepresentation;
    }
    @Override
    public String toString(){
        return textRepresenatation;
    }
}