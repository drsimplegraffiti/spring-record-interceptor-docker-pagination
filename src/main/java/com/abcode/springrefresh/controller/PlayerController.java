package com.abcode.springrefresh.controller;

import com.abcode.springrefresh.dto.PlayerDto;
import com.abcode.springrefresh.exception.ConflictException;
import com.abcode.springrefresh.model.Player;
import com.abcode.springrefresh.repository.PlayerRepository;
import com.abcode.springrefresh.response.ApiResponse;
import com.abcode.springrefresh.utility.ResponseUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PlayerController {

    private final PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @PostMapping("/all")
    public ResponseEntity<ApiResponse<PlayerDto>> getAll(@RequestBody PlayerDto request) {

        Player pExit = playerRepository.findByName(request.getName());
        if(pExit != null)
            throw new ConflictException("already exist");

        Player player = new Player();
        player.setName(request.getName());

        playerRepository.save(player);

        PlayerDto playerDto = new PlayerDto();
        playerDto.setName(player.getName());

        return ResponseEntity.ok(ResponseUtil.success("success",playerDto, null));
    }

    @GetMapping("/get/all")
    public ResponseEntity<ApiResponse<List<Player>>> getAllPlayers(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String name) {

        int pageIndex = page - 1;

        Pageable pageable = PageRequest.of(pageIndex, size);

        Specification<Player> specification = Specification.where(null);

        if (name != null && !name.isEmpty()) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
        }

        Page<Player> playerPage = playerRepository.findAll(specification, pageable);
        List<Player> players = playerPage.getContent();

        if (players.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        ApiResponse<List<Player>> response = new ApiResponse<>(
                "success",
                "Users retrieved successfully",
                players,
                Map.of(
                        "currentPage", page,
                        "totalPages", playerPage.getTotalPages(),
                        "totalItems", playerPage.getTotalElements()
                )
        );

        return ResponseEntity.ok(response);
    }


}