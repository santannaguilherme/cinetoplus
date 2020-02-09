package br.com.iteris.cinetoplus.domain.dto.request;
import javax.validation.constraints.NotEmpty;
//import com.example.project.domain.validators.Phone;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrilhaCreateRequest {

    @NotEmpty(message = "nome is required")
    private String nome;

    @NotEmpty(message = "album is required")
    private String album;
   
    //@Phone(message = "phone is invalid")
   // @NotEmpty(message = "date is required")
    //@Date(message = "date is required")
    
    @NotEmpty(message = "compositor is required")
    private String compositor;

    private String duracao;


    @NotEmpty(message = "diretorio is required")
    private String diretorio;

    private String capa;
}