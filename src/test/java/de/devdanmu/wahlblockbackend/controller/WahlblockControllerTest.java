package de.devdanmu.wahlblockbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.devdanmu.wahlblockbackend.data.HashWrapper;
import de.devdanmu.wahlblockbackend.data.VoterLogin;
import de.devdanmu.wahlblockbackend.data.entity.Election;
import de.devdanmu.wahlblockbackend.data.entity.VoterHash;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author danmu
 * created on 2018-05-08
 */
@SuppressWarnings("unchecked")
@RunWith(SpringRunner.class)
@WebMvcTest(WahlblockController.class)
public class WahlblockControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WahlblockController wahlblockController;

    @Test
    public void setLoginAndGetPermission() throws Exception {
        // Given
        String hash1 = "ABC";

        VoterLogin voterLogin = new VoterLogin();
        voterLogin.setIdCardNumber("2TN7N91VT");
        voterLogin.setVoterKey("04043935");
        voterLogin.setPublicKey("XXX");

        VoterHash voterHash = new VoterHash();
        voterHash.setHash(hash1);

        ObjectMapper mapper = new ObjectMapper();
        String voterLoginJSON = mapper.writeValueAsString(voterLogin);

        BindingResult result = new BeanPropertyBindingResult(voterLogin, "voterLogin");
        ResponseEntity<VoterHash> responseEntity = new ResponseEntity(voterHash, HttpStatus.OK);

        // When
        given(wahlblockController.setLoginAndGetPermission(voterLogin, result)).willReturn(responseEntity);

        // Then
        mvc.perform(post("/api/v1/voter/login")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(String.valueOf(StandardCharsets.UTF_8))
                .content(voterLoginJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$.hash", is(hash1)));

    }

    @Test
    public void getElectionInfo() throws Exception {
        // Given
        Election election = new Election();
        election.setTitle("Sind sie für ein Verbot von Haustierprofilen auf Facebook?");
        Integer electionId = 1;

        // When
        given(wahlblockController.getElectionInfo(electionId)).willReturn(election);

        // Then
        mvc.perform(get("/api/v1/election/{electionId}", electionId)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(String.valueOf(StandardCharsets.UTF_8)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(election.getTitle())));
    }

    @Test
    public void getHashValidation() throws Exception {
        // Given
        String hash1 = "ABC";
        String hash2 = "DEF";

        HashWrapper hashWrapper = new HashWrapper();
        ArrayList<String> hashList = new ArrayList<>();
        hashList.add(hash1);
        hashList.add(hash2);
        hashWrapper.setHashList(hashList);

        ObjectMapper mapper = new ObjectMapper();
        String hasListJson = mapper.writeValueAsString(hashWrapper);

        List<String> listReturn = new ArrayList<>();
        listReturn.add(hash2);

        // When
        given(wahlblockController.getHashValidation(hashWrapper)).willReturn(listReturn);

        // Then
        mvc.perform(post("/api/v1/validation")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(String.valueOf(StandardCharsets.UTF_8))
                .content(hasListJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.contains(hash2)));
    }
}
