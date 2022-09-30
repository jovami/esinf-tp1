package esinf.model;

public class Fruto {

    private String nome;
    private int id;


    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o){

        if(this==o){
            return true;

        }else if((o instanceof Fruto)){
            Fruto outroFruto= (Fruto)o;

            //Apenas sao iguais caso o seu id seja o mesmo, uma vez que é possível ter
            //frutos com o mesmo nome representando frutos diferentesº

            if(this.getId()==outroFruto.getId()){
                return true;
            }
        }
        return false;

    }

}
