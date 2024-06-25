package classes;

public class Itens {
    private long codigoItem;
    private int quantidade;
    private double valorParcial;
    private long produtoId;
    private long vendaId;

    public int getQuantidade() {
        return quantidade;
    }

    
    public long getItensCodigo() {
        return codigoItem;
    }

    public void setCodigoItem(long codigoItem) {
        this.codigoItem = codigoItem;
    }

    public int getItensQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorParcial() {
        return valorParcial;
    }

    public void setValorParcial(double valorParcial) {
        this.valorParcial = valorParcial;
    }

    public long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(long produtoId) {
        this.produtoId = produtoId;
    }

    public long getVendaId() {
        return vendaId;
    }

    public void setVendaId(long vendaId) {
        this.vendaId = vendaId;
    }
}
