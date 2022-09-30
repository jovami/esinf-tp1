package esinf.model;

public class Fruto {

    private String nome;
    private int id;

    public Fruto(String nome, int id) {
        setNome(nome);
        setId(id);
    }

    private void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty())
            throw new IllegalArgumentException("erro: fruto com nome invalido");
        this.nome = nome;
    }
    private void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o){

        if (this == o)
            return true;
        else if (o == null || this.getClass() != o.getClass())
            return false;

        Fruto outroFruto = (Fruto) o;

        //Apenas sao iguais caso o seu id seja o mesmo, uma vez que é possível ter
        //frutos com o mesmo nome representando frutos diferentesº

        return this.getId() == outroFruto.getId();
    }

    @Override
    public int hashCode() {
        /* De acordo com
         * https://medium.com/codelog/overriding-hashcode-method-effective-java-notes-723c1fedf51c
         */
        final int multiplier = 31;
        int hash = 17;

        hash = multiplier * hash + this.id;
        hash = multiplier * hash + this.nome.hashCode();

        return hash;
    }
}
