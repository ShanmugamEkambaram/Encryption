package com.xerago.vault.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xerago.vault.entity.VaultData;

public interface VaultDataRepository extends JpaRepository<VaultData, Long> {

}
