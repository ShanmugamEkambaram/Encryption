package com.xerago.vault.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.xerago.vault.dto.BatchInput;
import com.xerago.vault.dto.BatchResult;
import com.xerago.vault.dto.SecretsKey;
import com.xerago.vault.dto.ValutResponseDTO;
import com.xerago.vault.dto.VaultRequestDTO;
import com.xerago.vault.entity.VaultData;
import com.xerago.vault.respository.VaultDataRepository;
import com.xerago.vault.service.VaultEncryptionService;

@Service
public class VaultEncryptionServiceImpl implements VaultEncryptionService {

	@Value("${vault.uri}")
	private String vaulturl;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	VaultDataRepository vaultDataRepository;

	@Override
	public String KeyCreation(SecretsKey secretsKey, String vaulttoken) {

		HttpEntity<SecretsKey> requestEntity = new HttpEntity<SecretsKey>(secretsKey, getHttpHeaders(vaulttoken));
		ResponseEntity<String> responseEntity = restTemplate.exchange(vaulturl + "/v1/transit/keys/my-key2",
				HttpMethod.POST, requestEntity, String.class);
		return responseEntity.getBody();
	}

	@Override
	public ValutResponseDTO EncryptedMessageData(VaultRequestDTO vaultRequestDTO) {
		List<BatchInput> batchInputs = vaultRequestDTO.getBatch_input();
		for (BatchInput batchInput : batchInputs) {
			String simpleBase64 = Base64.getEncoder()
					.encodeToString(batchInput.getPlaintext().getBytes(StandardCharsets.UTF_8));
			String simpleContext = Base64.getEncoder()
					.encodeToString(batchInput.getContext().getBytes(StandardCharsets.UTF_8));
			batchInput.setPlaintext(simpleBase64);
			batchInput.setContext(simpleContext);

		}
		HttpEntity<?> httpEntity = new HttpEntity<Object>(vaultRequestDTO, getHttpHeaders(vaultRequestDTO.getToken()));
		ResponseEntity<ValutResponseDTO> responseEntity = restTemplate.exchange(vaulturl + "/v1/cvm/encrypt/pii",
				HttpMethod.POST, httpEntity, ValutResponseDTO.class);
		vaultDataRepository.save(convertVaultData(batchInputs, responseEntity.getBody().getData().getBatch_results()));
		return responseEntity.getBody();
	}

	private List<VaultData> convertVaultData(List<BatchInput> batchInputs, List<BatchResult> batch_results) {
		List<VaultData> vaultDatas = new ArrayList<>();
		for (int i = 0; i < batchInputs.size(); i++) {
			BatchInput batchInput = batchInputs.get(i);
			VaultData vaultData = new VaultData();
			vaultData.setId((long)1);
			vaultData.setContext(batchInput.getContext());
			vaultData.setPlaintext(batchInput.getPlaintext());
			vaultData.setCiphertext(batch_results.get(i).getCiphertext());
			vaultDatas.add(vaultData);
		}
		return vaultDatas;
	}

	@Override
	public ValutResponseDTO DecryptedMessageData(VaultRequestDTO vaultRequestDTO) {

		HttpEntity<?> httpEntity = new HttpEntity<Object>(vaultRequestDTO, getHttpHeaders(vaultRequestDTO.getToken()));
		ResponseEntity<ValutResponseDTO> responseEntity = restTemplate.exchange(vaulturl + "/v1/cvm/decrypt/pii",
				HttpMethod.POST, httpEntity, ValutResponseDTO.class);
		return responseEntity.getBody();
	}

	private HttpHeaders getHttpHeaders(String vaulttoken) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", "application/json");
		headers.add("X-Vault-Token", vaulttoken);
		return headers;
	}

}
