/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospedagem.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Favero
 */
@Entity
@Table(name="vagas")
@SequenceGenerator(name="seq", initialValue=1, allocationSize=1)
public class Vaga implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private Long id;
    
    @Column(name="titulo")
    private String titulo;
    
    @Column(name="descricao")
    private String descricao;
    
    @Column(name="valor")
    private double valor;
    
    @Column(name="capacidade")
    private int capacidade;
    
    @Column(name="regras")
    private String regras;
    
    @Column(name="localizacao")
    private String localizacao;
    
    @Column(name="reservado")
    private boolean reservado;
    
    @Column(name="cidade")
    private String cidade;
    
    @ManyToMany
    @JoinTable(name = "vaga_servicos",
            joinColumns = {@JoinColumn(name = "id_vaga")},
            inverseJoinColumns = {@JoinColumn(name = "id_servico")})
    private List<Servico> servicos = new ArrayList<Servico>();
    
    @OneToMany(mappedBy = "vaga")
    private List<Avaliacao> avaliacoes;
    
    @ManyToOne
    @JoinColumn(name = "id_dono", nullable = false)
    private Pessoa dono;
    
    @OneToOne
    @JoinColumn(name = "id_hospede", nullable = true)
    private Pessoa hospede;
    
    @Column(name="ativo")
    private boolean ativo;
    
    @Temporal(TemporalType.DATE)
    @Column(name="periodo_inicial")
    private Date periodoInicial;
    
    @Temporal(TemporalType.DATE)
    @Column(name="periodo_final")
    private Date periodoFinal;
    
    @OneToMany(mappedBy = "vaga")
    private List<HistoricoVaga> historicoVagas;
    
    public double getMediaAvaliacoes(){
        double total = 0;
        for(Avaliacao s : this.avaliacoes){
            total = total + s.getValor();
        }
        return total / this.avaliacoes.size();
    }
    
    public void addHistorico(HistoricoVaga historico){
        this.historicoVagas.add(historico);
    }
    
    public int getQtAvaliacoes(){
        return this.avaliacoes.size();
    }

    public Date getPeriodoInicial() {
        return periodoInicial;
    }

    public void setPeriodoInicial(Date periodoInicial) {
        this.periodoInicial = periodoInicial;
    }

    public Date getPeriodoFinal() {
        return periodoFinal;
    }

    public void setPeriodoFinal(Date periodoFinal) {
        this.periodoFinal = periodoFinal;
    }
    
    

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }
    
    

    public Vaga(String titulo, String descricao, double valor, int capacidade, String regras, String localizacao, boolean reservado) {
        this.historicoVagas = new ArrayList<>();
        this.titulo = titulo;
        this.descricao = descricao;
        this.valor = valor;
        this.capacidade = capacidade;
        this.regras = regras;
        this.localizacao = localizacao;
        this.reservado = reservado;
    }

    public Vaga() {
        this.historicoVagas = new ArrayList<>();
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public Pessoa getDono() {
        return dono;
    }

    public void setDono(Pessoa dono) {
        this.dono = dono;
    }

    public Pessoa getHospede() {
        return hospede;
    }

    public void setHospede(Pessoa hospede) {
        this.hospede = hospede;
    }
    
    

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getRegras() {
        return regras;
    }

    public void setRegras(String regras) {
        this.regras = regras;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }
    
    
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vaga)) {
            return false;
        }
        Vaga other = (Vaga) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vaga{" + "id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", valor=" + valor + ", capacidade=" + capacidade + ", regras=" + regras + ", localizacao=" + localizacao + ", reservado=" + reservado + '}';
    }

    
    
}
