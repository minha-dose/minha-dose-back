package com.minhadose.demo.controller;


import com.minhadose.demo.model.AppointmentModel;
import com.minhadose.demo.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Agendamentos", description = "Operações relacionadas a agendamentos de vacinação")
@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;


    @Operation(summary = "Criar agendamento", description = "Cria um novo agendamento de vacinação.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Agendamento criado com sucesso")
    })
    @PostMapping
    public AppointmentModel create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do agendamento a ser criado")
            @RequestBody AppointmentModel appointment) {
        return appointmentService.create(appointment);
    }


    @Operation(summary = "Listar agendamentos", description = "Retorna todos os agendamentos cadastrados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de agendamentos retornada com sucesso")
    })
    @GetMapping
    public List<AppointmentModel> listAll() {
        return appointmentService.listAll();
    }


    @Operation(summary = "Buscar agendamento por ID", description = "Retorna um agendamento pelo seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Agendamento encontrado"),
        @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    })
    @GetMapping("/{id}")
    public AppointmentModel getById(
        @Parameter(description = "ID do agendamento", example = "1")
        @PathVariable("id") Long id) {
        return appointmentService.getById(id);
    }


    @Operation(summary = "Atualizar agendamento", description = "Atualiza um agendamento existente pelo ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Agendamento atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    })
    @PutMapping("/{id}")
    public AppointmentModel update(
        @Parameter(description = "ID do agendamento", example = "1")
        @PathVariable("id") Long id,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados atualizados do agendamento")
        @RequestBody AppointmentModel updated) {
        return appointmentService.update(id, updated);
    }


    @Operation(summary = "Deletar agendamento", description = "Remove um agendamento pelo ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Agendamento removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    })
    @DeleteMapping("/{id}")
    public void delete(
        @Parameter(description = "ID do agendamento", example = "1")
        @PathVariable("id") Long id) {
        appointmentService.delete(id);
    }
}
