public class Cliente {

    private String id;
    private String nome;
    private String cpf;

    public Cliente(String id, String nome, String cpf){

        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getID() {
        return id;}
    public void setID(String id) {
        this.id = id;}

    public String getNome() {
        return nome;}
    public void setNome(String nome) {
        this.nome = nome;}

    public String getCPF() {
        return cpf;}
    public void setCPF(String cpf) {
        this.cpf = cpf;}


    @Override
    public String toString(){

        return String.format("ID Cliente: %s \nNome: %s \nCPF: %s\n", id, nome, cpf);
    }



}
