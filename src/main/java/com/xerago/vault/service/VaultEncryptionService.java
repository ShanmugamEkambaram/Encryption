package com.xerago.vault.service;

import com.xerago.vault.dto.SecretsKey;
import com.xerago.vault.dto.ValutResponseDTO;
import com.xerago.vault.dto.VaultRequestDTO;

public interface VaultEncryptionService {
	public String KeyCreation(SecretsKey secretsKey,String vaultoken);
	public ValutResponseDTO EncryptedMessageData(VaultRequestDTO vaultdtoReq);
	public ValutResponseDTO DecryptedMessageData(VaultRequestDTO vaultdtoreq);

}
