package ru.netology.databasemanager;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentEntity {
    String id;
    String amount;
    String created;
    String status;
    String transaction_id;
}
