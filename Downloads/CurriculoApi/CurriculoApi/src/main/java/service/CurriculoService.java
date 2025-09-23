package service;

import model.Curriculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CurriculoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurriculoService {

    private final CurriculoRepository curriculoRepository;

    @Autowired
    public CurriculoService(CurriculoRepository curriculoRepository) {
        this.curriculoRepository = curriculoRepository;
    }

    public Curriculo saveCurriculo(Curriculo curriculo) {
        return curriculoRepository.save(curriculo);
    }

    public Optional<Curriculo> findCurriculoById(Long id) {
        return curriculoRepository.findById(id);
    }

    public List<Curriculo> findAllCurriculos() {
        return curriculoRepository.findAll();
    }

    public void deleteCurriculo(Long id) {
        curriculoRepository.deleteById(id);
    }
}