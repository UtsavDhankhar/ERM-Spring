package com.shreejee.Inventory.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ArticleColorRs {

    private Long id;
    private Long articleId;
    private String color;
    private String code;
    private boolean isPrimary;
}
