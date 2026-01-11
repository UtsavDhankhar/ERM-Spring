package com.shreejee.Inventory.dto.response;
import com.shreejee.Inventory.enums.ArticleStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ArticleRs {

    private Long id;
    private Long brandId;
    private String code;
    private String name;
    private String description;
    private String category;
    private ArticleStatus status;
}
