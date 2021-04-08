package com.user.account.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private long accountNumber;

    private long accountBalance;

    private String name;

    public void incrementAccountBalance( long amount) {
        accountBalance = accountBalance + amount;
    }


}
