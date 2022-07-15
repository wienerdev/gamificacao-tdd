package com.ita;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ita.models.Pontuacao;
import com.ita.models.Usuario;
import com.ita.service.GamificacaoService;

public class GamificacaoTest {

    GamificacaoService armazenamentoTeste;

    Usuario usuario;
    Usuario usuario2;

    Pontuacao pontuacao;

    List<Pontuacao> listaPontos;
    List<Usuario> listaDeUsuarios;

    @BeforeEach
    public void setup() {
        pontuacao = new Pontuacao();
        listaPontos = new ArrayList<>();
        
        listaDeUsuarios = new ArrayList<>();
    
        pontuacao.setPontosTipoEstrela(10);
        pontuacao.setPontosTipoMoeda(5);
        listaPontos.add(pontuacao);

        usuario = new Usuario("Matheus", listaPontos);
        usuario2 = new Usuario("Sara", listaPontos);

        listaDeUsuarios.add(usuario);
        listaDeUsuarios.add(usuario2);

        armazenamentoTeste = new GamificacaoService(listaDeUsuarios);

    } 
   
    @Test
    public void testReceberPontosTipoEstrela() {
        assertEquals(
            "O usuário Matheus recebeu 10 pontos do tipo estrela",
            armazenamentoTeste.receberPontos("Matheus", 10, "estrela")
        );
    }

    @Test
    public void testReceberPontosTipoMoeda() {
        assertEquals(
            "O usuário Matheus recebeu 5 pontos do tipo moeda",
            armazenamentoTeste.receberPontos("Matheus", 5, "moeda")
        );
    }

    @Test
    public void testReceberPontosUsuarioInvalido() {
        Assertions.assertThrows(
            RuntimeException.class,
            () -> armazenamentoTeste.receberPontos("Banana", 5, "moeda")
        );
    }

    @Test
    public void testReceberPontosTipoInvalido() {
        Assertions.assertThrows(
            RuntimeException.class,
            () -> armazenamentoTeste.receberPontos("Matheus", 5, "banana")
        );
    }

    @Test
    public void testRecuperarPontosTipoEstrela() {
        assertEquals(
            "Pontos do tipo estrela: 10",
            armazenamentoTeste.recuperarPontos("Matheus", "estrela"));
    }

    @Test
    public void testRecuperarPontosTipoMoeda() {
        assertEquals(
            "Pontos do tipo moeda: 5",
            armazenamentoTeste.recuperarPontos("Matheus", "moeda"));
    }

    @Test
    public void testRecuperarPontosTipoInvalido() {
        Assertions.assertThrows(
            RuntimeException.class,
            () -> armazenamentoTeste.recuperarPontos("Matheus", "banana"));
    }

    @Test
    public void testRecuperarPontosUsuarioInvalido() {
        Assertions.assertThrows(
            RuntimeException.class,
            () -> armazenamentoTeste.recuperarPontos("Banana", "moeda"));
    }

    @Test
    public void testRecuperarTodosUsuariosQuePossuemPontos() {

        armazenamentoTeste.printarTodosUsuariosQuePossuemPontos();
        assertTrue(armazenamentoTeste.isUsuarioPossuiPontos(usuario));
        assertTrue(armazenamentoTeste.isUsuarioPossuiPontos(usuario2));
    }

    @Test
    public void testRecuperarTiposPontosDeUsuarioQuePossuiTodos() {
        assertEquals(
            "O usuário Matheus possui pontos do tipo estrela e moeda",
            armazenamentoTeste.recuperarTodosPontosRegistradosPorUsuario("Matheus")
        );
    }

    @Test
    public void testRecuperarRankingTipoPonto() {
        armazenamentoTeste.receberPontos("Matheus", 10, "moeda");
        System.out.println(armazenamentoTeste.recuperarRankingTipoPontos("moeda"));
    }

}
