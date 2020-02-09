package br.com.iteris.cinetoplus.service;

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

import br.com.iteris.cinetoplus.domain.entities.Trilhas;
import br.com.iteris.cinetoplus.exception.DataNotFoundException;
import br.com.iteris.cinetoplus.repository.TrilhaRepository;

@RunWith(MockitoJUnitRunner.class)
public class TrilhaServiceTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Mock
    private TrilhaRepository repositoryMock;

    @InjectMocks
    private TrilhaService service;


    Trilhas entity = Trilhas.builder().album("album").capa("capa").compositor("compositor").
    diretorio("diretorio").duracao("duracao").nome("nome")
    .idTrilha(1).build();

    @Test
    public void should_ThrowDataNotFoundException_whenNotFound() {

        // given no data

        // then
        expected.expect(DataNotFoundException.class);
        expected.expectMessage("Trilha Not found");

        // when
        service.findById(1);
    }

    @Test
    public void should_findById() {

        // given
        when(repositoryMock.findById(anyInt())).thenReturn(Optional.of(entity));

        // when
        Trilhas model = service.findById(anyInt());

        // then
        verify(repositoryMock, times(1)).findById(anyInt());
        assertNotNull("Filme deve ser encontrado!", model);
    }


    @Test
    public void should_ListFilme() {

        List<Trilhas> list = new ArrayList<>();
        list.add(entity);
        list.add(entity);

        when(repositoryMock.findAll()).thenReturn(list);

        List<Trilhas> model = service.listTrilha();

        assertNotNull("lista n pode ser nula!", model);
    }


    @Test
    public void should_CreateOneItem(){
        //given
        when(repositoryMock.save(entity)).thenReturn(entity);

        //when
        Trilhas model = service.createTrilha(entity);

        //then
        assertNotNull("Filme deve ser encontrado!" , model);
        
    }



  
}