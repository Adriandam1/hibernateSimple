package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "gatos")
public class Gatos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "raza", length = 100)
    private String raza;

    @Column(name = "idade")
    private Integer idade;

    @Column(name = "cor", length = 50)
    private String cor;

    @Column(name = "peso", precision = 5, scale = 2)
    private BigDecimal peso;

    @Column(name = "data_adopcion")
    @Temporal(TemporalType.DATE)
    private Date dataAdopcion;

    @Column(name = "vacinado")
    private Boolean vacinado = false;

    @Column(name = "nome_dono", length = 100)
    private String nomeDono;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public Date getDataAdopcion() {
        return dataAdopcion;
    }

    public void setDataAdopcion(Date dataAdopcion) {
        this.dataAdopcion = dataAdopcion;
    }

    public Boolean getVacinado() {
        return vacinado;
    }

    public void setVacinado(Boolean vacinado) {
        this.vacinado = vacinado;
    }

    public String getNomeDono() {
        return nomeDono;
    }

    public void setNomeDono(String nomeDono) {
        this.nomeDono = nomeDono;
    }

    @Override
    public String toString() {
        return "Gatos{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", raza='" + raza + '\'' +
                ", idade=" + idade +
                ", cor='" + cor + '\'' +
                ", peso=" + peso +
                ", dataAdopcion=" + dataAdopcion +
                ", vacinado=" + vacinado +
                ", nomeDono='" + nomeDono + '\'' +
                '}';
    }
}

