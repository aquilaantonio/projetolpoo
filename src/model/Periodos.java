package model;

public enum Periodos {
NAOAPLICA("NAOAPLICA"),
DIARIA("DIARIA"),
SEMANAL("SEMANAL"),
MENSAL("MENSAL"),
TRISMESTRAL("TRISMESTRAL"),
SEMESTRAL("SEMESTRAL"),
ANUAL("ANUAL");
	
	private String label;

	Periodos(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}
