/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author maria.sousa9
 */
@Entity
@Table(name = "pessoa_juridica")
@PrimaryKeyJoinColumn(name = "Id_cliente")
public class PessoaJuridica extends Cliente{
    
    @Column(nullable = false, unique = true)
    private String cnpj;
    
    @Column(nullable = false)
    private String inscricaoEstadual;

    public PessoaJuridica() {
    }

    public PessoaJuridica(String cnpj, String inscricaoEstadual, String nome, String email) {
        super(nome, email);
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }
    
    
    
}
