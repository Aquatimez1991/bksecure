package com.bksecure.bksecure.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class DashboardStats {
    private Long totalOrders;
    private BigDecimal totalIncome;
    private Long totalClients;
    private Long activeServices;
}