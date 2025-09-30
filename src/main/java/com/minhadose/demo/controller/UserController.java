package com.minhadose.demo.controller;


import com.minhadose.demo.dto.request.UserRequest;
import com.minhadose.demo.model.UserModel;
import com.minhadose.demo.service.UserService;
import com.minhadose.demo.dto.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@Tag(name = "Usuários", description = "Operações relacionadas aos usuários do sistema")
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }


    @Operation(summary = "Criar usuário", description = "Cria um novo usuário no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos para criação do usuário")
    })
    @PostMapping
    public ResponseEntity<Void> postMethodName(
        @RequestBody(description = "Dados do usuário a ser criado")
        @org.springframework.web.bind.annotation.RequestBody UserRequest createUserDTO) {
        Long id = userService.createUser(createUserDTO);
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).build();
    }


    @Operation(summary = "Listar usuários", description = "Retorna todos os usuários cadastrados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso"),
        @ApiResponse(responseCode = "204", description = "Nenhum usuário encontrado")
    })
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário pelo seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(
            @Parameter(description = "ID do usuário", example = "1")
            @PathVariable Long id) {
    return userService.getUserById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Atualizar usuário", description = "Atualiza um usuário existente pelo ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
        @Parameter(description = "ID do usuário", example = "1")
        @PathVariable Long id,
        @RequestBody(description = "Dados atualizados do usuário")
        @org.springframework.web.bind.annotation.RequestBody UserRequest createUserDTO) {
        UserResponse updatedUser = userService.updateUser(id, createUserDTO);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUser);
    }


    @Operation(summary = "Deletar usuário", description = "Remove um usuário pelo ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "ID do usuário", example = "1")
            @PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar usuário por e-mail", description = "Retorna um usuário pelo e-mail.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/email")
    public ResponseEntity<UserModel> getUserByEmail(
            @Parameter(description = "E-mail do usuário", example = "usuario@email.com")
            @RequestParam String email) {
        return userService.getUserByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @Operation(summary = "Verificar existência de e-mail", description = "Verifica se um e-mail já está cadastrado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Resultado da verificação retornado com sucesso")
    })
    @GetMapping("/email/exists")
    public ResponseEntity<Boolean> checkEmailExists(
            @Parameter(description = "E-mail do usuário", example = "usuario@email.com")
            @RequestParam String email) {
        return ResponseEntity.ok(userService.emailExists(email));
    }


    @Operation(summary = "Buscar usuários por cidade", description = "Retorna todos os usuários de uma cidade.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuários encontrados")
    })
    @GetMapping("/city/{city}")
    public ResponseEntity<List<UserModel>> getUsersByCity(
            @Parameter(description = "Nome da cidade", example = "São Paulo")
            @PathVariable String city) {
        return ResponseEntity.ok(userService.getUsersByCity(city));
    }


    @Operation(summary = "Buscar usuários por CEP", description = "Retorna todos os usuários de um determinado CEP.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuários encontrados")
    })
    @GetMapping("/cep/{cep}")
    public ResponseEntity<List<UserModel>> getUsersByCep(
            @Parameter(description = "CEP do usuário", example = "01001-000")
            @PathVariable String cep) {
        return ResponseEntity.ok(userService.getUsersByCep(cep));
    }
}
