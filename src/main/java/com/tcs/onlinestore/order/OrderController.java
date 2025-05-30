package com.tcs.onlinestore.order;

import com.tcs.onlinestore.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(path = "api/order")
@Tag(name = "order", description = "the order API")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Get order", description = "Get a specific order for user", tags = {"order"})
    @ApiResponses(value = {@ApiResponse(
            description = "successful operation",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = OrderDTO.class)
            ))})
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public OrderDTO getOrder(UUID userId) {
        return orderService.getOrder(userId);
    }

    @Operation(summary = "Create order", description = "Create order.", tags = {"order"})
    @ApiResponses(value = {
            @ApiResponse(
                    description = "successful operation",
                    responseCode = "201"
            ),
            @ApiResponse( // added one error response so it is shown as schema in swagger
                    description = "Unauthorized",
                    responseCode = "401",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createOrder(@RequestBody List<CreateOrderDTO> order) {
        orderService.createOrder(order);
    }

}