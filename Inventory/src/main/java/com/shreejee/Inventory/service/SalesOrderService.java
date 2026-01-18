package com.shreejee.Inventory.service;

import com.shreejee.Inventory.dto.request.SalesOrderRq;
import com.shreejee.Inventory.dto.response.SalesArticlePriceRs;
import com.shreejee.Inventory.dto.response.SalesOrderLineRs;
import com.shreejee.Inventory.dto.response.SalesOrderRs;
import com.shreejee.Inventory.entity.Article;
import com.shreejee.Inventory.entity.ArticleColor;
import com.shreejee.Inventory.entity.SalesArticlePrice;
import com.shreejee.Inventory.entity.SalesOrder;
import com.shreejee.Inventory.entity.SalesOrderLine;
import com.shreejee.Inventory.enums.SalesOrderStatus;
import com.shreejee.Inventory.repo.BrandRepo;
import com.shreejee.Inventory.repo.SalesOrderRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SalesOrderService {

    private final SalesOrderRepo salesOrderRepo;
    private final BrandRepo brandRepo; // to validate brandId exists

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public SalesOrderRs save(SalesOrderRq salesOrderRq) {

        if (!brandRepo.existsById(salesOrderRq.brandId())) {
            throw new IllegalArgumentException("Brand not found: " + salesOrderRq.brandId());
        }

        String orderNo = (salesOrderRq.orderNo() == null || salesOrderRq.orderNo().isBlank())
                ? generateOrderNo()
                : salesOrderRq.orderNo();

        final SalesOrder salesOrder = SalesOrder.builder()
                .brandId(salesOrderRq.brandId())
                .orderNo(orderNo)
                .orderDate(salesOrderRq.orderDate() != null ? salesOrderRq.orderDate() : LocalDate.now())
                .status(SalesOrderStatus.RECEIVED)
                .remarks(salesOrderRq.remarks())
                .build();

        List<SalesOrderLine> salesOrderLines = salesOrderRq.salesOrderLineList().stream()
                .map(l -> SalesOrderLine.builder()
                        .salesOrder(salesOrder)
                        .article(entityManager.getReference(Article.class, l.articleId()))
                        .articleColor(entityManager.getReference(ArticleColor.class, l.articleColorId()))
                        .size(l.size())
                        .quantityOrdered(l.quantityOrdered())
                        .targetDispatchDate(l.targetDispatchDate())
                        .build()
                )
                .toList();


        List<SalesArticlePrice> salesArticlePrices = salesOrderRq.salesArticlePriceList().stream()
                .map(salePrice -> SalesArticlePrice.builder()
                        .salesOrder(salesOrder)
                        .article(entityManager.getReference(Article.class, salePrice.articleId()))
                        .price(salePrice.price()).build())
                .toList();

        salesOrder.setSalesOrderLines(salesOrderLines);
        salesOrder.setSalesArticlePrices(salesArticlePrices);
        SalesOrder salesOrderSaved = salesOrderRepo.save(salesOrder);

        return SalesOrderRs.builder()
                .id(salesOrderSaved.getId())
                .orderNo(salesOrderSaved.getOrderNo())
                .build();
    }

    public SalesOrderRs getById(Long id) {
        SalesOrder salesOrder = salesOrderRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sales order not found: " + id));
        return SalesOrderRs.builder()
                .id(salesOrder.getId())
                .orderNo(salesOrder.getOrderNo())
                .status(salesOrder.getStatus())
                .brandId(salesOrder.getBrandId())
                .salesOrderLineRs(generateSalesOrderLines(salesOrder.getSalesOrderLines()))
                .build();
    }

    public SalesOrderRs getByOrderNo(String orderNo) {
        SalesOrder salesOrder = salesOrderRepo.findByOrderNo(orderNo)
                .orElseThrow(() -> new IllegalArgumentException("Sales order not found: " + orderNo));
        return SalesOrderRs.builder()
                .id(salesOrder.getId())
                .orderNo(salesOrder.getOrderNo())
                .orderDate(salesOrder.getOrderDate())
                .remarks(salesOrder.getRemarks())
                .status(salesOrder.getStatus())
                .brandId(salesOrder.getBrandId())
                .salesOrderLineRs(generateSalesOrderLines(salesOrder.getSalesOrderLines()))
                .salesArticlePriceRs(generateSalesArticlePrice(salesOrder.getSalesArticlePrices()))
                .build();
    }

    public List<SalesOrderRs> listByBrand(Long brandId) {
        List<SalesOrderRs> salesOrderRs = salesOrderRepo.findByBrandId(brandId).stream()
                .map(salesOrder -> SalesOrderRs.builder()
                        .id(salesOrder.getId())
                        .orderNo(salesOrder.getOrderNo())
                        .status(salesOrder.getStatus())
                        .brandId(salesOrder.getBrandId())
                        .salesOrderLineRs(generateSalesOrderLines(salesOrder.getSalesOrderLines()))
                        .salesArticlePriceRs(generateSalesArticlePrice(salesOrder.getSalesArticlePrices()))
                        .build())
                .toList();

        return salesOrderRs;
    }

    private String generateOrderNo() {
        // very basic; you can later change to something like "SO-2025-000123"
        return "SO-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private List<SalesOrderLineRs> generateSalesOrderLines(List<SalesOrderLine> salesOrderLines) {

        return salesOrderLines.stream()
                .map(salesOrderLine -> SalesOrderLineRs.builder()
                        .id(salesOrderLine.getId())
                        .size(salesOrderLine.getSize())
                        .targetDispatchDate(salesOrderLine.getTargetDispatchDate())
                        .articleId(salesOrderLine.getArticle().getId())
                        .articleColorId(salesOrderLine.getArticleColor().getId())
                        .articleName(salesOrderLine.getArticle().getName())
                        .articleColor(salesOrderLine.getArticleColor().getColor())
                        .quantityOrdered(salesOrderLine.getQuantityOrdered())
                        .build())
                .toList();
    }

    private List<SalesArticlePriceRs> generateSalesArticlePrice(List<SalesArticlePrice> saleArticlePrices) {

        return saleArticlePrices.stream()
                .map(saleArticlePrice -> SalesArticlePriceRs.builder()
                        .id(saleArticlePrice.getId())
                        .price(saleArticlePrice.getPrice())
                        .articleId(saleArticlePrice.getArticle().getId())
                        .build())
                .toList();
    }
}

