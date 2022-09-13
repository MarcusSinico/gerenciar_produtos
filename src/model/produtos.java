package model;

public class produtos {
    private String nome = "Produto";
    private double preco = 0;
    private int qtde = 0;

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public double getPreco() {
            return preco;
        }

        public void setPreco(double preco) {
            this.preco = preco;
        }

        public int getQtde() {
            return qtde;
        }

    public void addQtde(int qtde) {
            if (qtde >= 0)
                this.qtde = this.qtde + qtde;
        }

        public boolean tiraqtde(int qtde) {
            if (this.qtde - qtde >= 0 && qtde > 0) {
                this.qtde -= qtde;
                return true;
            } else {
                return false;
            }
        }
        public double getmostratotal(){
            return this.qtde * this.preco;
        }
}

