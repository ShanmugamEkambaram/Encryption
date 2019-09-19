package com.xerago.vault.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.xerago.vault.dto.SecretsKey;
import com.xerago.vault.dto.ValutResponseDTO;
import com.xerago.vault.dto.VaultRequestDTO;
import com.xerago.vault.service.VaultEncryptionService;

@RestController
public class VaultController {

	@Value("${vault.uri}")
	private String vaulturl;

	@Autowired
	private VaultEncryptionService vaultEncryptService;

	@PostMapping("/vaultsecret")
	public String KeyCreation(@RequestBody SecretsKey secretsKey, @RequestHeader(value = "token") String vaulttoken) {
		String responseEntity = vaultEncryptService.KeyCreation(secretsKey, vaulttoken);
		return responseEntity;
	}

	@PostMapping("/vaultsecret/message")
	public ValutResponseDTO EncryptedMessage(@RequestBody VaultRequestDTO vaultdtoreq) {
		ValutResponseDTO responseEntity = vaultEncryptService.EncryptedMessageData(vaultdtoreq);
		System.out.println(responseEntity);
		return responseEntity;
	}

	@PostMapping("/vaultsecret/decryptmessage")
	public ValutResponseDTO DecryptedMessage(@RequestBody VaultRequestDTO vaultdtoreq) {
		ValutResponseDTO responseEntity = vaultEncryptService.DecryptedMessageData(vaultdtoreq);
		return responseEntity;
	}

}
