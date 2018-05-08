package de.devdanmu.wahlblockbackend.controller;

import de.devdanmu.wahlblockbackend.data.entity.Election;
import de.devdanmu.wahlblockbackend.data.HashWrapper;
import de.devdanmu.wahlblockbackend.data.VoterLogin;
import de.devdanmu.wahlblockbackend.data.entity.VoterHash;
import de.devdanmu.wahlblockbackend.service.ElectionInfoService;
import de.devdanmu.wahlblockbackend.service.HashValidationService;
import de.devdanmu.wahlblockbackend.service.VoterLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author danmu
 * created on 2018-03-10
 */
@CrossOrigin
@RestController
public class WahlblockController {

    private final VoterLoginService voterLoginService;
    private final ElectionInfoService electionInfoService;
    private final HashValidationService hashValidationService;

    @Autowired
    public WahlblockController(final VoterLoginService voterLoginService, final ElectionInfoService electionInfoService, final HashValidationService hashValidationService) {
        this.voterLoginService = voterLoginService;
        this.electionInfoService = electionInfoService;
        this.hashValidationService = hashValidationService;
    }

    @PostMapping("/api/v1/voter/login")
    public VoterHash setLoginAndGetPermission(@RequestBody VoterLogin voterLogin) throws Exception {
        return voterLoginService.startVoterLogin(voterLogin);
    }

    @GetMapping("/api/v1/election/{electionId}")
    public Election getElectionInfo(@PathVariable int electionId) throws Exception {
        return electionInfoService.getElection(electionId);
    }

    @PostMapping("/api/v1/validation")
    public List<String> getHashValidation(@RequestBody HashWrapper hashWrapper) {
        return hashValidationService.validateHashList(hashWrapper.getHashList());
    }
}
