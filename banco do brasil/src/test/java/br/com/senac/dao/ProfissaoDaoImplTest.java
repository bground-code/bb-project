/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Profissao;
import br.com.senac.util.GeradorUtil;
import static br.com.senac.util.GeradorUtil.gerarProfissao;
import com.github.javafaker.Faker;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author maria.sousa9
 */
public class ProfissaoDaoImplTest {
    
    private Profissao profissao;
    private ProfissaoDao profissaoDao;
    private Session sessao;
    
    public ProfissaoDaoImplTest() {
        profissaoDao = new ProfissaoDaoImpl();
    }

    @Test
    public void testSalvar() {
        System.out.println("salvar");
        Faker faker = new Faker();
        profissao = new Profissao(faker.job().title(), faker.lorem().sentence());
        sessao =HibernateUtil.abrirConexao();
        profissaoDao.salvarOuAlterar(profissao, sessao);
        sessao.close();
        assertNotNull(profissao.getId());

    }
//    fazer em casa
//    @Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");

    }
//    fazer em casa não fazer exclui só alterar
//    @Test
    public void testPesquisarPorNome() {
        System.out.println("pesquisarPorNome");

    }
//    fazer em casa não fazer exclui só alterar
    
    public Profissao buscarProffissaoBd(){
        sessao = HibernateUtil.abrirConexao();
        Query<Profissao> consulta = sessao.createQuery("from Profissao p");
        List<Profissao> profissaos = consulta.list();
        sessao.close();
        if(profissaos.isEmpty()){
            testSalvar();
        }else{
            profissao = profissaos.get(0);
        }
        
        return profissao;
    }
}
