package com.shreejee.Inventory.controller;

import com.shreejee.Inventory.dto.request.SalesOrderRq;
import com.shreejee.Inventory.dto.response.SalesOrderRs;
import com.shreejee.Inventory.service.SalesOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sales-orders")
public class SalesOrderController {

    private final SalesOrderService salesOrderService;

    public SalesOrderController(SalesOrderService salesOrderService) {
        this.salesOrderService = salesOrderService;
    }

    @PostMapping
    public ResponseEntity<SalesOrderRs> createSalesOrder(@RequestBody SalesOrderRq salesOrderRq) {
        SalesOrderRs salesOrderRs = salesOrderService.save(salesOrderRq);
        return ResponseEntity
                .created(URI.create("/api/sales-orders/" + salesOrderRs.getId()))
                .body(salesOrderRs);
    }

    @GetMapping("/{brandId}")
    public List<SalesOrderRs> listByBrand(@PathVariable Long brandId) {
        return salesOrderService.listByBrand(brandId);

    }

    @GetMapping
    public ResponseEntity<SalesOrderRs> getByOrderNo(@RequestParam(required = true) String orderNo) {
        SalesOrderRs salesOrderRs = salesOrderService.getByOrderNo(orderNo);
        return new ResponseEntity<>(salesOrderRs, HttpStatus.OK);
    }
}
