/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Endereco;
import br.com.senac.entidade.PessoaFisica;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;
import static br.com.senac.util.GeradorUtil.*;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;

/**
 *
 * @author maria.sousa9
 */
public class PessoaFisicaDaoImplTest {

    private PessoaFisica pessoaFisica;
    private PessoaFisicaDao pessoaFisicaDao;
    private Session sessao;

    public PessoaFisicaDaoImplTest() {
        pessoaFisicaDao = new PessoaFisicaDaoImpl();
    }
  

    @Test
    public void testSalvar() {
        System.out.println("salvar");
        
        ProfissaoDaoImplTest pdit = new ProfissaoDaoImplTest();//prova pertece a entidade profissao
        
        pessoaFisica = new PessoaFisica(gerarNome(), gerarLogin(), gerarCpf(), gerarNumero(7));
        Endereco endereco = gerarEndereco();
        pessoaFisica.setEndereco(endereco);
        endereco.setCliente(pessoaFisica);
        
        pessoaFisica.setProfissao(pdit.buscarProffissaoBd());//prova pertece a entidade profissao
        
        sessao = HibernateUtil.abrirConexao();
        pessoaFisicaDao.salvarOuAlterar(pessoaFisica, sessao);
        sessao.close();;
        assertNotNull(pessoaFisica.getId());
    }
    
    private Endereco gerarEndereco(){
        
        Endereco end = new Endereco("Rua Solange da SIlva", "Centro", gerarNumero(3), gerarCidade(), "SC", "casa", gerarCep());
        return end;
    }

//    @Test
    public void testAlterar() {
        System.out.println("alterar");
        buscarPessoaFisicaBd();
        pessoaFisica.setNome(gerarNome());
        pessoaFisica.setCpf(gerarCpf());
        pessoaFisica.getEndereco().setLogradouro("Rua nova");
        sessao = HibernateUtil.abrirConexao();
        pessoaFisicaDao.salvarOuAlterar(pessoaFisica, sessao);
        sessao.close();

        sessao = HibernateUtil.abrirConexao();
        PessoaFisica pesFisicaAlt = pessoaFisicaDao.pesquisarPorId(pessoaFisica.getId(), sessao);
        sessao.close();
        assertEquals(pessoaFisica.getNome(), pesFisicaAlt.getNome());
        assertEquals(pessoaFisica.getCpf(), pesFisicaAlt.getCpf());
        assertEquals(pessoaFisica.getEndereco().getLogradouro(),pesFisicaAlt.getEndereco().getLogradouro());
    }
    //   @Test

    public void testExcluir() {
        System.out.println("excluir");
        buscarPessoaFisicaBd();
        sessao = HibernateUtil.abrirConexao();
        pessoaFisicaDao.excluir(pessoaFisica, sessao);

        PessoaFisica pesFisicaExc = pessoaFisicaDao.pesquisarPorId(pessoaFisica.getId(), sessao);
        sessao.close();
        assertNull(pesFisicaExc);
    }

//    @Test
    public void testPesquisarPorNome() {
        System.out.println("pesquisarPorNome");
        buscarPessoaFisicaBd();
        sessao = HibernateUtil.abrirConexao();

        List<PessoaFisica> fisicas = pessoaFisicaDao.pesquisarPorNome(pessoaFisica.getNome(), sessao);
        sessao.close();;
        assertTrue(!fisicas.isEmpty());
    }

//    @Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
        buscarPessoaFisicaBd();
        sessao = HibernateUtil.abrirConexao();
        PessoaFisica pesFisica = pessoaFisicaDao.pesquisarPorId(pessoaFisica.getId(), sessao);
        sessao.close();
        assertNotNull(pesFisica);
    }

    public PessoaFisica buscarPessoaFisicaBd() {
        String hql = "from PessoaFisica pf";//buscar da tabela cliente e pessoa física
        sessao = HibernateUtil.abrirConexao();
        Query<PessoaFisica> consulta = sessao.createQuery(hql);
        List<PessoaFisica> pessoaFisicas = consulta.getResultList();
        sessao.close();
        if (pessoaFisicas.isEmpty()) {
            testSalvar();
        } else {
            pessoaFisica = pessoaFisicas.get(0);
        }
        return pessoaFisica;
    }

}
