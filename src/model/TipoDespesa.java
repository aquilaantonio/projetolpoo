package model;

public enum TipoDespesa {
SAQUE("SAQUE"),
ALUGUEL("ALUGUEL"),
CONTAS_RESIDENCIAS("CONTAS_RESIDENCIAS"),
COMPRAS("COMPRAS"),
TRANSPORTE("TRANSPORTE"),
ESTUDOS("ESTUDOS"),
OUTROS("OUTROS");
	private String label;

	TipoDespesa(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
    

}
