package com.shreejee.Inventory.dto.response;

import com.shreejee.Inventory.entity.Article;
import com.shreejee.Inventory.entity.SalesOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigDecimal;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class SalesArticlePriceRs {

        private long id;
        private long articleId;
        private BigDecimal price;
}
