package model;

public enum Tipo {
SALARIO("SALARIO"),
MESADA("MESADA"),
LUCRO("LUCRO"),
DOACAO("DOACAO"),
PREMIOS("PREMIOS"),
OUTROS("OUTROS");
	private String label;

	Tipo(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
    

}
