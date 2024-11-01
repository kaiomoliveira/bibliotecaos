package com.curso.services;

import com.curso.domains.Autor;
import com.curso.domains.dtos.AutorDTO;
import com.curso.repositories.AutorRepository;
import com.curso.services.exceptions.DataIntegrityViolationException;
import com.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepo;

    public List<AutorDTO> findAll(){

        return autorRepo.findAll().stream()
                .map(obj -> new AutorDTO(obj))
                .collect(Collectors.toList());
    }

    public Autor findById(int id){
        Optional<Autor> obj = autorRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("autor não encontrado! Id:" + id));
    }

    public Autor create(AutorDTO dto){
        dto.setId(null);
        ValidaAutor(dto);
        Autor obj = new Autor(dto);
        return autorRepo.save(obj);
    }

    private void ValidaAutor(AutorDTO dto){
        Optional<Autor> obj = autorRepo.findBydocumentoPessoal(dto.getDocumentoPessoal());
        if(obj.isPresent() && obj.get().getId() != dto.getId()){
            throw new DataIntegrityViolationException("Documento pessoal já cadastrado");
        }

    }

    public Autor update(Integer id,AutorDTO objDto){
        objDto.setId(id);
        Autor oldObj = findById(id);
        oldObj = new Autor(objDto);
        return autorRepo.save(oldObj);
    }

    public void delete(Integer id){
        Autor obj = findById(id);
        if(obj.getLivros().size()>0){
            throw new DataIntegrityViolationException("Autor não pode ser excluido pois possui Livro vinculado");
        }
        autorRepo.deleteById(id);
    }

}