package com.minhadose.demo.controller;

import com.minhadose.demo.model.UbsModel;
import com.minhadose.demo.service.UbsService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/ubs"})
public class UbsController {
    @Autowired
    UbsService ubsService;
    
    public UbsController() {
    }
    
    @PostMapping
    public ResponseEntity<UbsModel> createUbs(@RequestBody UbsModel ubs) {
        try {
            UbsModel newUbs = this.ubsService.createUbs(ubs);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUbs);
        } catch (Exception var3) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @PutMapping
    public ResponseEntity<UbsModel> updateUbs(@RequestParam Long ubsId, @RequestBody UbsModel ubs) {
        try {
            UbsModel updatedUbs = this.ubsService.updateById(ubsId, ubs);
            return ResponseEntity.status(HttpStatus.OK).body(updatedUbs);
        } catch (EntityNotFoundException var4) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception var5) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping({"/{id}"})
    public ResponseEntity<UbsModel> getUbsById(@PathVariable Long id) {
        try {
            UbsModel ubs = this.ubsService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(ubs);
        } catch (EntityNotFoundException var3) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<UbsModel>> getAllUbs() {
        List<UbsModel> ubsList = this.ubsService.listAllUbs();
        return ubsList.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.status(HttpStatus.OK).body(ubsList);
    }
    
    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteUbs(@PathVariable Long id) {
        try {
            this.ubsService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException var3) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/search")
    public ResponseEntity<List<UbsModel>> getUbsByName(@RequestParam String name) {
    List<UbsModel> result = ubsService.findByName(name);
    return result.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                            : ResponseEntity.ok(result);
    }      

}