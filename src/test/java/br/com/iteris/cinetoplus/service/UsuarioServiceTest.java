package br.com.iteris.cinetoplus.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.iteris.cinetoplus.domain.dto.request.UsuarioAvatarRequest;
import br.com.iteris.cinetoplus.domain.entities.Usuario;
import br.com.iteris.cinetoplus.exception.DataNotFoundException;
import br.com.iteris.cinetoplus.repository.UsuarioRepository;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceTest {

	@Rule
	public ExpectedException expected = ExpectedException.none();

	@Mock
	private UsuarioRepository repositoryMock;

	@InjectMocks
	private UsuarioService service;

	Usuario entity = Usuario.builder().administrador(true).ativo(true).email("email").nome("nome").senha("senha")
			.sobrenome("sobrenome").idUsuario(1).imagem("imagem").build();

	@Test
	public void should_ThrowDataNotFoundException_whenNotFound() {

		// given no data

		// then
		expected.expect(DataNotFoundException.class);
		expected.expectMessage("Usuário não encontrado");

		// when
		service.findUsuarioById(1);
	}

	@Test
	public void should_findById() {

		when(repositoryMock.findById(anyInt())).thenReturn(Optional.of(entity));

    Usuario model = service.findUsuarioById(anyInt());

    verify(repositoryMock, times(1)).findById(anyInt());
    assertNotNull("Usuario deve ser encontrado!", model);
	}

	@Test
	public void should_createUsuario() {
		// given
		when(repositoryMock.save(entity)).thenReturn(entity);

		// when
		Usuario usuario = service.createUsuario(entity);

		// then
		assertTrue("campo imagem deve ter o texto 'Usuário novo'",
								usuario.getImagem() == "Usuário novo");
		assertTrue("campo administrador deve ser false'",
								usuario.getAdministrador() == false);
		assertTrue("campo ativo deve ser true",
								usuario.getAtivo() == true);
		assertNotNull("Usuario não deve ser nulo!", usuario);
	}

	@Test
	public void should_UpdateFoto() {

		when(repositoryMock.findById(anyInt())).thenReturn(Optional.of(entity));

		Usuario usuario = service.findUsuarioById(anyInt());

		String fotoBefore = usuario.getImagem();

		UsuarioAvatarRequest usuarioAvatarRequest = UsuarioAvatarRequest.builder()
																																		.foto("novaImagem")
																																		.build();

		service.editAvatarUsuario(usuario.getIdUsuario(), usuarioAvatarRequest);

		assertNotNull("Model não deve ser nulo", usuarioAvatarRequest);
		assertFalse("Imagem não foi alterada", fotoBefore == usuarioAvatarRequest.getFoto() ? true : false);
	}

}
