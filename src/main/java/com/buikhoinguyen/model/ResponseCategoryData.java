package com.buikhoinguyen.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCategoryData {
    public long cateId;
    public String cateName;
    public boolean cateStatus;
}
