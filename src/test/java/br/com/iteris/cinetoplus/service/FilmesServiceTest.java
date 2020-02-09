package br.com.iteris.cinetoplus.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.iteris.cinetoplus.domain.entities.Filmes;
import br.com.iteris.cinetoplus.exception.DataNotFoundException;
import br.com.iteris.cinetoplus.repository.FilmesRepository;

@RunWith(MockitoJUnitRunner.class)
public class FilmesServiceTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Mock
    private FilmesRepository repositoryMock;

    @InjectMocks
    private FilmesService service;


    Filmes entity = Filmes.builder().idFilme(2).titulo("nome").
            ano(2000).duracao(1000).genero("genero").diretor("diretor").
            elenco("elenco").sinopse("sinpose top")
            .build();

    @Test
    public void should_ThrowDataNotFoundException_whenNotFound() {

        // given no data

        // then
        expected.expect(DataNotFoundException.class);
        expected.expectMessage("Filme Not found");

        // when
        service.findById(1);
    }

    @Test
    public void should_findById() {

        // given
        when(repositoryMock.findById(anyInt())).thenReturn(Optional.of(entity));

        // when
        Filmes model = service.findById(anyInt());

        // then
        verify(repositoryMock, times(1)).findById(anyInt());
        assertNotNull("Filme deve ser encontrado!", model);
    }


    @Test
    public void should_ListFilme() {

        List<Filmes> list = new ArrayList<>();
        list.add(entity);
        list.add(entity);

        when(repositoryMock.findAll()).thenReturn(list);

        List<Filmes> model = service.listFilmes();

        assertNotNull("lista n pode ser nula!", model);
    }

    @Test
    public void should_UpdateFilme() {

        when(repositoryMock.save(entity)).thenReturn(entity);
        should_findById();
        Filmes filme = service.filmePut(1, entity);
        assertNotNull("Model não deve ser nulo", filme);
        assertEquals(entity, filme);

    }

    @Test
    public void should_CreateOneItem(){
        //given
        when(repositoryMock.save(entity)).thenReturn(entity);

        //when
        Filmes model = service.createFilme(entity);

        //then
        assertNotNull("Filme deve ser encontrado!" , model);
        
    }



  
}