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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;
import java.util.UUID;

@SecurityRequirement(name = "bearerAuth")
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


    @Operation(summary = "Get active order", description = "Gets the active(un-paid) order of the user", tags = {"order"})
    @ApiResponses(value = {@ApiResponse(
            description = "successful operation",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = OrderDTO.class)
            ))})
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public OrderDTO getActiveOrder(@AuthenticationPrincipal Jwt jwt) {
        return orderService.getActiveOrder(jwt);
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
    public void createOrder(@AuthenticationPrincipal Jwt jwt, @RequestBody List<CreateOrderDTO> order) {
        orderService.createOrder(jwt, order);
    }

    @Operation(summary = "Update order", description = "Update order.", tags = {"order"})
    @ApiResponses(value = {
            @ApiResponse(
                    description = "successful operation",
                    responseCode = "200"
            )
    })
    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void updateOrder(@AuthenticationPrincipal Jwt jwt, @RequestBody List<CreateOrderDTO> order) {
        orderService.updateOrder(jwt, order);
    }

    @Operation(summary = "Checkout for the order", description = "Brings the order to checkout where it will be paid. " +
            "Using this endpoint changes the order status from un-paid to paid.", tags = {"order"})
    @ApiResponses(value = {
            @ApiResponse(
                    description = "successful operation",
                    responseCode = "200"
            )
    })
    @PatchMapping(path = "/checkout")
    @ResponseStatus(value = HttpStatus.OK)
    public void checkoutOrder(@AuthenticationPrincipal Jwt jwt) {
        orderService.checkoutOrder(jwt);
    }
}