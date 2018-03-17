package de.devdanmu.wahlblockbackend.controller;

import de.devdanmu.wahlblockbackend.jpa.Election;
import de.devdanmu.wahlblockbackend.jpa.VoterHash;
import de.devdanmu.wahlblockbackend.service.ElectionInfoService;
import de.devdanmu.wahlblockbackend.service.VoterLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author danmu
 * created on 2018-03-10
 */
@RestController
public class WahlblockController {

    private final VoterLoginService voterLoginService;
    private ElectionInfoService electionInfoService;

    @Autowired
    public WahlblockController(final VoterLoginService voterLoginService, final ElectionInfoService electionInfoService) {
        this.voterLoginService = voterLoginService;
        this.electionInfoService = electionInfoService;
    }

    @GetMapping("/api/v1/voter/login")
    public VoterHash getLoginPermission(@RequestParam(value = "idcardnumber") final String idCardNumber, @RequestParam(value = "publickey") final String publicKey, @RequestParam(value = "voterkey") final Integer voterKey) throws Exception {
        // todo remove parameters and read them from body
        return voterLoginService.startVoterLogin(idCardNumber, voterKey, publicKey);
    }

    @GetMapping("/api/v1/election")
    public Election getElectionInfo() throws Exception {
        return electionInfoService.getElection();

    }
}
