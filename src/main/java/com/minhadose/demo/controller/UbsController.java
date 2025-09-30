package com.minhadose.demo.controller;


import com.minhadose.demo.model.UbsModel;
import com.minhadose.demo.service.UbsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Unidades Básicas de Saúde", description = "Operações relacionadas às Unidades Básicas de Saúde")
@RestController
@RequestMapping({"/ubs"})
public class UbsController {
    @Autowired
    UbsService ubsService;
    
    public UbsController() {
    }
    

    @Operation(summary = "Criar UBS", description = "Cria uma nova Unidade Básica de Saúde.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "UBS criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos para criação da UBS")
    })
    @PostMapping
    public ResponseEntity<UbsModel> createUbs(
        @RequestBody(description = "Dados da UBS a ser criada")
        @org.springframework.web.bind.annotation.RequestBody UbsModel ubs) {
        try {
            UbsModel newUbs = this.ubsService.createUbs(ubs);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUbs);
        } catch (Exception var3) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    

    @Operation(summary = "Atualizar UBS", description = "Atualiza uma UBS existente pelo ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "UBS atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "UBS não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno ao atualizar UBS")
    })
    @PutMapping("/{ubsId}")
    public ResponseEntity<UbsModel> updateUbs(
        @Parameter(description = "ID da UBS", example = "1")
        @PathVariable("ubsId") Long ubsId,
        @RequestBody(description = "Dados atualizados da UBS")
        @org.springframework.web.bind.annotation.RequestBody UbsModel ubs) {
        try {
            UbsModel updatedUbs = this.ubsService.updateById(ubsId, ubs);
            return ResponseEntity.status(HttpStatus.OK).body(updatedUbs);
        } catch (EntityNotFoundException var4) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception var5) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    

    @Operation(summary = "Buscar UBS por ID", description = "Retorna uma UBS pelo seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "UBS encontrada"),
        @ApiResponse(responseCode = "404", description = "UBS não encontrada")
    })
    @GetMapping({"/{id}"})
    public ResponseEntity<UbsModel> getUbsById(
        @Parameter(description = "ID da UBS", example = "1")
        @PathVariable("id") Long id) {
        try {
            UbsModel ubs = this.ubsService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(ubs);
        } catch (EntityNotFoundException var3) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    

    @Operation(summary = "Listar todas as UBS", description = "Retorna uma lista de todas as UBS cadastradas.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de UBS retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Nenhuma UBS encontrada")
    })
    @GetMapping
    public ResponseEntity<List<UbsModel>> getAllUbs() {
        List<UbsModel> ubsList = this.ubsService.listAllUbs();
        return ubsList.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.status(HttpStatus.OK).body(ubsList);
    }
    

    @Operation(summary = "Deletar UBS", description = "Remove uma UBS pelo ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "UBS removida com sucesso"),
        @ApiResponse(responseCode = "404", description = "UBS não encontrada")
    })
    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteUbs(
        @Parameter(description = "ID da UBS", example = "1")
        @PathVariable("id") Long id) {
        try {
            this.ubsService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException var3) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Buscar UBS por nome", description = "Retorna uma lista de UBS pelo nome.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "UBS encontrada(s)"),
        @ApiResponse(responseCode = "404", description = "Nenhuma UBS encontrada com esse nome")
    })
    @GetMapping("/search")
    public ResponseEntity<List<UbsModel>> getUbsByName(
            @Parameter(description = "Nome da UBS para busca", example = "UBS Central")
            @RequestParam String name) {
        List<UbsModel> result = ubsService.findByName(name);
        return result.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                                : ResponseEntity.ok(result);
    }

}