package de.devdanmu.wahlblockbackend.controller;

import de.devdanmu.wahlblockbackend.data.HashWrapper;
import de.devdanmu.wahlblockbackend.data.VoterLogin;
import de.devdanmu.wahlblockbackend.data.entity.Election;
import de.devdanmu.wahlblockbackend.data.entity.VoterHash;
import de.devdanmu.wahlblockbackend.service.ElectionInfoService;
import de.devdanmu.wahlblockbackend.service.HashValidationService;
import de.devdanmu.wahlblockbackend.service.VoterLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
    public ResponseEntity setLoginAndGetPermission(@Valid @RequestBody final VoterLogin voterLogin, final BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors()); // todo create fancy consumer friendly error object
        } else {
            VoterHash voterHash = voterLoginService.startVoterLogin(voterLogin);
            return ResponseEntity.ok().body(voterHash);
        }
    }

    @GetMapping("/api/v1/election/{electionId}")
    public Election getElectionInfo(@PathVariable final Integer electionId) throws Exception {
        return electionInfoService.getElection(electionId);
    }

    @PostMapping("/api/v1/validation")
    public List<String> getHashValidation(@RequestBody final HashWrapper hashWrapper) {
        return hashValidationService.validateHashList(hashWrapper.getHashList());
    }
}
