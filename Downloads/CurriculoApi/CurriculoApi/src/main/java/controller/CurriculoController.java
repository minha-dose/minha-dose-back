package controller;

import model.Curriculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.CurriculoService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/curriculos")
public class CurriculoController {

    @Autowired

    CurriculoService curriculoService;

    @PostMapping
    public ResponseEntity<Curriculo> createCurriculo(@RequestBody Curriculo curriculo) {
        Curriculo savedCurriculo = curriculoService.saveCurriculo(curriculo);
        return ResponseEntity.ok(savedCurriculo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curriculo> getCurriculo(@PathVariable Long id) {
        Optional<Curriculo> curriculo = curriculoService.findCurriculoById(id);
        return curriculo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Curriculo>> getAllCurriculos() {
        List<Curriculo> curriculos = curriculoService.findAllCurriculos();
        return ResponseEntity.ok(curriculos);
    }
}