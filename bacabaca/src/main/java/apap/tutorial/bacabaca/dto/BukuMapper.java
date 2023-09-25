package apap.tutorial.bacabaca.dto;
import apap.tutorial.bacabaca.dto.request.CreateBukuRequestDTO;
import apap.tutorial.bacabaca.dto.request.ReadBukuResponseDTO;
import apap.tutorial.bacabaca.dto.request.UpdateBukuRequestDTO;
import apap.tutorial.bacabaca.model.Buku;
import apap.tutorial.bacabaca.model.Penerbit;
import org.hibernate.sql.Update;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BukuMapper {
    Buku createBukuRequestDTOToBuku(CreateBukuRequestDTO createBukuRequestDTO);
    Buku updateBukuRequestDTOToBuku(UpdateBukuRequestDTO updateBukuRequestDTO);
    UpdateBukuRequestDTO bukuToUpdateBukuRequestDTO (Buku buku);

    ReadBukuResponseDTO bukuToReadBukuResponseDTO (Buku buku);

    @AfterMapping
    default void mapNamaPenerbit(Buku buku, @MappingTarget ReadBukuResponseDTO responseDTO) {
        responseDTO.setNamaPenerbit(buku.getPenerbit().getNamaPenerbit());
    }
}
